package pl.sii.registerup.subscription.service.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sii.registerup.subscription.service.model.SubscriptionInput;
import pl.sii.registerup.subscription.service.model.SubscriptionOutput;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommandServiceITTest {

    @Autowired
    SubscriptionCommandService service;

    @DisplayName("Saving Object to database")
    @Test
    void happyPath() {
        // given
        SubscriptionInput input = new SubscriptionInput("john.doe@sii.pl");

        // when
        SubscriptionOutput output = service.createSubscription(input);

        // then
        assertThat(output.getEmail()).isEqualTo("john.doe@sii.pl");
        assertThat(output.getId()).isNotNull();
    }

}
