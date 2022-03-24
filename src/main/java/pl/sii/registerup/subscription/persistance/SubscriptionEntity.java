package pl.sii.registerup.subscription.persistance;


import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SubscriptionEntity {

    private Long id;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
