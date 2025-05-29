package org.launchcode.bugs_and_the_bees.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterFormDTO extends LoginFormDTO {

    private String confirmPassword;

}
