package org.launchcode.bugs_and_the_bees.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponseDTO {

    private String message;

    private UserDTO userDTO;
}
