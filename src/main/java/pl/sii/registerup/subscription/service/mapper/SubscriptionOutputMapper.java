package pl.sii.registerup.subscription.service.mapper;

import org.springframework.stereotype.Component;
import pl.sii.registerup.subscription.persistence.SubscriptionEntity;
import pl.sii.registerup.subscription.service.model.SubscriptionOutput;

@Component
public class SubscriptionOutputMapper {
    public SubscriptionOutput map(SubscriptionEntity subscriptionEntity) {
        return new SubscriptionOutput(subscriptionEntity.getId(), subscriptionEntity.getEmail());
    }
}
