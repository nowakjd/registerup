package pl.sii.registerup.subscription.service.model;

public class SubscriptionOutput {
    private final String id;
    private final String email;

    public SubscriptionOutput(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
