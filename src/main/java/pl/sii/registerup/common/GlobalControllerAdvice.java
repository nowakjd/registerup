package pl.sii.registerup.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sii.registerup.subscription.service.command.SubscriptionCreationException;

import java.util.Collection;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(SubscriptionCreationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    Collection<String> subscriptionCreation(SubscriptionCreationException ex) {
        return ex.getErrors();
    }

}
