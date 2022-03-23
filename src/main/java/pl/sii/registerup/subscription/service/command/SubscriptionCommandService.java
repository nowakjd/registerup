package pl.sii.registerup.subscription.service.command;

import org.springframework.stereotype.Service;
import pl.sii.registerup.subscription.persistance.SubscriptionEntity;
import pl.sii.registerup.subscription.persistance.SubscriptionRepository;
import pl.sii.registerup.subscription.service.mapper.SubscriptionInputMapper;
import pl.sii.registerup.subscription.service.mapper.SubscriptionOutputMapper;
import pl.sii.registerup.subscription.service.model.SubscriptionInput;
import pl.sii.registerup.subscription.service.model.SubscriptionOutput;

import javax.transaction.Transactional;

@Transactional
@Service
public class SubscriptionCommandService {
    private SubscriptionRepository subscriptionRepository;
    private EmailValidator emailValidator;
    private SubscriptionInputMapper subscriptionInputMapper;
    private SubscriptionOutputMapper subscriptionOutputMapper;

    public SubscriptionCommandService(SubscriptionRepository subscriptionRepository,
                                      EmailValidator emailValidator,
                                      SubscriptionInputMapper subscriptionInputMapper,
                                      SubscriptionOutputMapper subscriptionOutputMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.emailValidator = emailValidator;
        this.subscriptionInputMapper = subscriptionInputMapper;
        this.subscriptionOutputMapper = subscriptionOutputMapper;
    }

    public SubscriptionOutput createSubscription(SubscriptionInput subscriptionInput){
        emailValidator.validate(subscriptionInput);
        SubscriptionEntity subscriptionEntity = subscriptionInputMapper.map(subscriptionInput);

        SubscriptionEntity newSubscription = subscriptionRepository.save(subscriptionEntity);
        return subscriptionOutputMapper.map(newSubscription);
    }
}
