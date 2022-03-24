package pl.sii.registerup.subscription.service.model;

public class SubscriptionOutput {
    private final Long id;
    private final String email;

    public SubscriptionOutput(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
