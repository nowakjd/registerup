package pl.sii.registerup.subscription.service.mapper;

import org.springframework.stereotype.Component;
import pl.sii.registerup.subscription.persistence.SubscriptionEntity;
import pl.sii.registerup.subscription.service.model.SubscriptionInput;

@Component
public class SubscriptionInputMapper {

    public SubscriptionEntity map(SubscriptionInput subscriptionInput) {
        SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
        subscriptionEntity.setEmail(subscriptionInput.getEmail());
        return subscriptionEntity;
    }
}
