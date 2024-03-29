package pl.sii.registerup.subscription.service.command;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import pl.sii.registerup.subscription.persistence.SubscriptionEntity;
import pl.sii.registerup.subscription.persistence.SubscriptionRepository;
import pl.sii.registerup.subscription.service.mapper.SubscriptionInputMapper;
import pl.sii.registerup.subscription.service.mapper.SubscriptionOutputMapper;
import pl.sii.registerup.subscription.service.model.SubscriptionInput;
import pl.sii.registerup.subscription.service.model.SubscriptionOutput;


@Service
public class SubscriptionCommandService {
    private final SubscriptionRepository subscriptionRepository;
    private final EmailValidator emailValidator;
    private final SubscriptionInputMapper subscriptionInputMapper;
    private final SubscriptionOutputMapper subscriptionOutputMapper;

    public SubscriptionCommandService(SubscriptionRepository subscriptionRepository,
                                      EmailValidator emailValidator,
                                      SubscriptionInputMapper subscriptionInputMapper,
                                      SubscriptionOutputMapper subscriptionOutputMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.emailValidator = emailValidator;
        this.subscriptionInputMapper = subscriptionInputMapper;
        this.subscriptionOutputMapper = subscriptionOutputMapper;
    }

    public SubscriptionOutput createSubscription(SubscriptionInput subscriptionInput) {
        emailValidator.validate(subscriptionInput);
        SubscriptionEntity subscriptionEntity = subscriptionInputMapper.map(subscriptionInput);

        try {
            SubscriptionEntity newSubscription = subscriptionRepository.save(subscriptionEntity);
            return subscriptionOutputMapper.map(newSubscription);
        } catch (DuplicateKeyException e) {
            SubscriptionCreationException exception = new SubscriptionCreationException();
            exception.addError(subscriptionInput.getEmail() + " is already in database");
            throw exception;
        }
    }
}
