package pl.sii.registerup.subscription.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionInput {

    private final String email;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SubscriptionInput(@JsonProperty("email") String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
