package com.tomo.mcauthentication.domain.users;

import com.tomo.mcauthentication.ddd.domain.ConcurrencySafeEntity;
import com.tomo.mcauthentication.domain.users.rules.UserEmailMustBeUnique;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "mcuser")
@Getter
@NoArgsConstructor
public class User extends ConcurrencySafeEntity {

    public enum AuthProvider {
        EMAIL,
        FACEBOOK,
        GOOGLE
    }

    @EmbeddedId
    UserId userId;
    String firstName;
    String lastName;
    String email;
    AuthProvider provider;

    public User(
            UserId anId,
            String aFirstName,
            String aLastName,
            String anEmail,
            AuthProvider aProvider,
            UserRepository userRespository) {
        this.checkRule(new UserEmailMustBeUnique(userRespository, anEmail));
        this.userId = anId;
        this.firstName = aFirstName;
        this.lastName = aLastName;
        this.email = anEmail;
        this.provider = aProvider;
    }


    public void updateDetails(String aFirstName, String aLastName) {
        this.setFirstName(aFirstName);
        this.setLastName(aLastName);
    }

    public void setLastName(String aLastName) {
        assertArgumentNotEmpty(lastName, "Last name cannot be empty.");
        this.lastName = aLastName;
    }

    public void setFirstName(String aFirstName) {
        assertArgumentNotEmpty(aFirstName, "First name cannot be empty.");
        this.firstName = aFirstName;
    }
}
