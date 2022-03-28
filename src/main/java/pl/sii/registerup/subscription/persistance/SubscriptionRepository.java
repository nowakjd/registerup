package pl.sii.registerup.subscription.persistance;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * This is a repository.
 */
public interface SubscriptionRepository extends MongoRepository<SubscriptionEntity, String> {
    SubscriptionEntity save(SubscriptionEntity subscriptionEntity);

    Optional<SubscriptionEntity> findByEmail(String email);
}
