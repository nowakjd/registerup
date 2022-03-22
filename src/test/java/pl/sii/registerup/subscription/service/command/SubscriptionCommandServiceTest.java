package pl.sii.registerup.subscription.service.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import pl.sii.registerup.subscription.persistance.SubscriptionRepository;
import pl.sii.registerup.subscription.service.model.Subscription;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SubscriptionCommandServiceTest {
    private SubscriptionCommandService underTest;
    private SubscriptionRepository repository;
    private EmailValidator emailValidator;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(SubscriptionRepository.class);
        when(repository.save(any())).thenAnswer(a -> a.getArgument(0));
        emailValidator = new EmailValidator();
        underTest = new SubscriptionCommandService(repository, emailValidator);
    }

    @Test
    @DisplayName("Should create subscription when correct email is given")
    void happyPath() {
        //given
        Subscription correctSubscription = getValidSubscription();
        when(repository.findByEmail(any())).thenReturn(Optional.empty());
        //when
        Subscription result = underTest.createSubscription(correctSubscription);
        //then
        assertThat(result.getEmail().equals(correctSubscription.getEmail()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"bla@bla ", "bla.com", "bla.@bla.pl", "@.com", "blabla@@.com",
            "bla.@''@.com", "bla-bla@bla..com", "--@.com", "a\"b(c)d,e:f;g<h>i[j\\k]l@example.com",
            "this\\ still\\\"not\\\\allowed@example.com", "just\"not\"right@example.com",
            "blabla@bla.com_"})
    @DisplayName("Should throw exception when incorrect email is given) ")
    void wrongEmail(String email) {
        //given
        Subscription subscription = getSubscriptionWithEmail(email);
        when(repository.findByEmail(any())).thenReturn(Optional.empty());
        //when
        Executable lambdaUnderTest = ()-> underTest.createSubscription(subscription);
        //then
        SubscriptionCreationException exception = assertThrows(SubscriptionCreationException.class, lambdaUnderTest);
        assertThat(exception.hasErrors()).isTrue();
        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors()).anyMatch(s-> s.equals(email + "  is not a valid email"));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {" ", "    ", "\t"})
    @DisplayName("Should throw exception when email is empty")
    void firstNameEmpty(String email) {
        // given
        Subscription subscription = getSubscriptionWithEmail(email);
        when(repository.save(any())).thenAnswer(a -> a.getArgument(0));
        when(repository.findByEmail(any())).thenReturn(Optional.empty());
        //when
        Executable lambdaUnderTest = ()-> underTest.createSubscription(subscription);
        // then
        SubscriptionCreationException exception = assertThrows(SubscriptionCreationException.class, lambdaUnderTest);
        assertThat(exception.getErrors())
                .hasSize(1)
                .allMatch(e -> e.contains("Email is required"));
    }

    @Test
    @DisplayName("Should throw exception when email is already in database")
    void emailAlreadyInDatabase(){
        //given
        Subscription correctSubscription = getValidSubscription();
        when(repository.findByEmail(any())).thenAnswer(a -> a.getArgument(0));
        //when
        Executable lambdaUnderTest = ()-> underTest.createSubscription(correctSubscription);
        // then
        SubscriptionCreationException exception = assertThrows(SubscriptionCreationException.class, lambdaUnderTest);
        assertThat(exception.getErrors())
                .hasSize(1)
                .allMatch(e -> e.contains(correctSubscription.getEmail() + " is already in database"));

    }

    private Subscription getValidSubscription() {
        return new Subscription("email@email.com");
    }

    private Subscription getSubscriptionWithEmail(String email) {
        return new Subscription(email);
    }


}
