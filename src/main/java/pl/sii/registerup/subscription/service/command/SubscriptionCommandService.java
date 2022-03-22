package pl.sii.registerup.subscription.service.command;

import pl.sii.registerup.subscription.persistance.SubscriptionRepository;
import pl.sii.registerup.subscription.service.model.Subscription;

public class SubscriptionCommandService {
    private SubscriptionRepository subscriptionRepository;
    private EmailValidator emailValidator;

    public SubscriptionCommandService(SubscriptionRepository subscriptionRepository, EmailValidator emailValidator) {
        this.subscriptionRepository = subscriptionRepository;
        this.emailValidator = emailValidator;
    }

    Subscription createSubscription(Subscription subscription){
        return null;
    }
}
