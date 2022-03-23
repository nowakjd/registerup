package pl.sii.registerup.subscription.service.model;

public class SubscriptionOutput {
    private Long id;
    private String email;

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
