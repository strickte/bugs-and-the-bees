package org.launchcode.bugs_and_the_bees.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@Entity
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {

    @NotNull
    @Getter
    private String username;


    @NotNull
    @Getter
    private String password;

//    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//    @OneToMany(mappedBy = "user")
//    private final List<Project> projects = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
//        this.pwHash = encoder.encode(password);
        this.password = password;
    }

//    public boolean isMatchingPassword(String password) {
//        return encoder.matches(password, pwHash);
//    }

}
