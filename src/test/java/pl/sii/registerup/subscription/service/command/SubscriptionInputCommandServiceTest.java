package pl.sii.registerup.subscription.service.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.dao.DuplicateKeyException;
import pl.sii.registerup.subscription.persistence.SubscriptionRepository;
import pl.sii.registerup.subscription.service.mapper.SubscriptionInputMapper;
import pl.sii.registerup.subscription.service.mapper.SubscriptionOutputMapper;
import pl.sii.registerup.subscription.service.model.SubscriptionInput;
import pl.sii.registerup.subscription.service.model.SubscriptionOutput;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SubscriptionInputCommandServiceTest {
    private SubscriptionCommandService underTest;
    private SubscriptionRepository repository;
    private EmailValidator emailValidator;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(SubscriptionRepository.class);
        when(repository.save(any())).thenAnswer(a -> a.getArgument(0));
        emailValidator = new EmailValidator();
        underTest = new SubscriptionCommandService(
                repository,
                emailValidator,
                new SubscriptionInputMapper(),
                new SubscriptionOutputMapper()
        );
    }

    @Test
    @DisplayName("Should create subscription when correct email is given")
    void happyPath() {
        //given
        SubscriptionInput correctSubscriptionInput = getValidSubscription();
        when(repository.findByEmail(any())).thenReturn(Optional.empty());
        //when
        SubscriptionOutput result = underTest.createSubscription(correctSubscriptionInput);
        //then
        assertThat(result.getEmail()).isEqualTo(correctSubscriptionInput.getEmail());
    }

    @ParameterizedTest
    @ValueSource(strings = {"bla@bla ", "bla.com", "bla.@bla.pl", "@.com", "blabla@@.com",
            "bla.@''@.com", "bla-bla@bla..com", "--@.com", "a\"b(c)d,e:f;g<h>i[j\\k]l@example.com",
            "this\\ still\\\"not\\\\allowed@example.com", "just\"not\"right@example.com",
            "blabla@bla.com_"})
    @DisplayName("Should throw exception when incorrect email is given) ")
    void wrongEmail(String email) {
        //given
        SubscriptionInput subscriptionInput = getSubscriptionWithEmail(email);
        when(repository.findByEmail(any())).thenReturn(Optional.empty());
        //when
        Executable lambdaUnderTest = () -> underTest.createSubscription(subscriptionInput);
        //then
        SubscriptionCreationException exception = assertThrows(SubscriptionCreationException.class, lambdaUnderTest);
        assertThat(exception.hasErrors()).isTrue();
        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors()).anyMatch(s -> s.equals(email + "  is not a valid email"));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {" ", "    ", "\t"})
    @DisplayName("Should throw exception when email is empty")
    void firstNameEmpty(String email) {
        // given
        SubscriptionInput subscriptionInput = getSubscriptionWithEmail(email);
        when(repository.save(any())).thenAnswer(a -> a.getArgument(0));
        when(repository.findByEmail(any())).thenReturn(Optional.empty());
        //when
        Executable lambdaUnderTest = () -> underTest.createSubscription(subscriptionInput);
        // then
        SubscriptionCreationException exception = assertThrows(SubscriptionCreationException.class, lambdaUnderTest);
        assertThat(exception.getErrors())
                .hasSize(1)
                .allMatch(e -> e.contains("Email is required"));
    }

    @Test
    @DisplayName("Should throw exception when email is already in database")
    void emailAlreadyInDatabase() {
        //given
        SubscriptionInput correctSubscriptionInput = getValidSubscription();
        when(repository.save(any())).thenThrow(new DuplicateKeyException(""));

        //when
        Executable lambdaUnderTest = () -> underTest.createSubscription(correctSubscriptionInput);

        // then
        SubscriptionCreationException exception = assertThrows(SubscriptionCreationException.class, lambdaUnderTest);
        assertThat(exception.getErrors())
                .hasSize(1)
                .allMatch(e -> e.contains(correctSubscriptionInput.getEmail() + " is already in database"));

    }

    private SubscriptionInput getValidSubscription() {
        return new SubscriptionInput("email@email.com");
    }

    private SubscriptionInput getSubscriptionWithEmail(String email) {
        return new SubscriptionInput(email);
    }


}
