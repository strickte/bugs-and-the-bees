package org.launchcode.bugs_and_the_bees.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterFormDTO extends LoginFormDTO {

    private String confirmPassword;

}
