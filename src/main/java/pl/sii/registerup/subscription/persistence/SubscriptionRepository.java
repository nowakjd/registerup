package pl.sii.registerup.subscription.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This is a repository.
 */
public interface SubscriptionRepository extends MongoRepository<SubscriptionEntity, String> {

}
