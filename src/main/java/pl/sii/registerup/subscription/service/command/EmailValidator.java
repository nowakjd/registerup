package pl.sii.registerup.subscription.service.command;

import org.springframework.stereotype.Component;
import pl.sii.registerup.subscription.service.model.SubscriptionInput;

import java.util.regex.Pattern;

@Component
public class EmailValidator {
    private static final Pattern EMAIL_REGEX = Pattern
            .compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]"
                            + "+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)"
                            + "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]"
                            + "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
                            + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
                            + "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
                            + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\"
                            + "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
                    Pattern.CASE_INSENSITIVE);

    boolean validate(SubscriptionInput subscriptionInput) {
        SubscriptionCreationException exception = new SubscriptionCreationException();

        if (isEmpty(subscriptionInput.getEmail())) {
            exception.addError("Email is required");
            throw exception;
        }

        if (isEmailInvalid(subscriptionInput.getEmail())) {
            exception.addError(subscriptionInput.getEmail() + "  is not a valid email");
            throw exception;
        }

        return true;
    }

    private boolean isEmailInvalid(String email) {
        return !EMAIL_REGEX.matcher(email).matches();
    }

    private boolean isEmpty(String string) {
        return (string == null || string.trim().isEmpty());
    }
}
