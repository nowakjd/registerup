package pl.sii.registerup.subscription.service.model;

public class Subscription {
    private String email;

    public Subscription(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
