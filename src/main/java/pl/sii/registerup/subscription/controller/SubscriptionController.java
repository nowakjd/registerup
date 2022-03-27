package pl.sii.registerup.subscription.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sii.registerup.subscription.service.command.SubscriptionCommandService;
import pl.sii.registerup.subscription.service.model.SubscriptionInput;
import pl.sii.registerup.subscription.service.model.SubscriptionOutput;

@RestController
@RequestMapping("/api/v1")
public class SubscriptionController {
    final SubscriptionCommandService subscriptionCommandService;


    public SubscriptionController(SubscriptionCommandService subscriptionCommandService) {
        this.subscriptionCommandService = subscriptionCommandService;
    }

    @PostMapping("/subscriber")
    ResponseEntity<SubscriptionOutput> createSubscription(@RequestBody SubscriptionInput subscriptionInput) {
        return new ResponseEntity<>(subscriptionCommandService.createSubscription(subscriptionInput), HttpStatus.OK);
    }
}
