package pl.sii.registerup.subscription.service.model;

public class SubscriptionInput {
    private String email;

    public SubscriptionInput(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
