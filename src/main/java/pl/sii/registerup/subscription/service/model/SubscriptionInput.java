package pl.sii.registerup.subscription.service.model;

public class SubscriptionInput {
    private final String email;

    public SubscriptionInput(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
