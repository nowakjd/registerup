package pl.sii.registerup.subscription.persistance;

import java.util.Optional;

public interface SubscriptionRepository {
    SubscriptionEntity save(SubscriptionEntity subscriptionEntity);
    Optional<SubscriptionEntity> findByEmail(String email);
}
