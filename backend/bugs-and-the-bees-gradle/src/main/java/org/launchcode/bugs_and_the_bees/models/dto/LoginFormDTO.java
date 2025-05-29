package org.launchcode.bugs_and_the_bees.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFormDTO {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 30, message = "Password mst be between 5 and 30 characters")
    private String password;

}
