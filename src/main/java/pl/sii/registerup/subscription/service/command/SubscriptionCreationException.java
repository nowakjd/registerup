package pl.sii.registerup.subscription.service.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class SubscriptionCreationException extends RuntimeException {
    private final Collection<String> errors = new HashSet<>();

    void addError(String error) {
        errors.add(error);
    }

    boolean hasErrors() {
        return !errors.isEmpty();
    }

    Collection<String> getErrors() {
        return Collections.unmodifiableCollection(errors);
    }
}
