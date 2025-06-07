package org.launchcode.bugs_and_the_bees.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.launchcode.bugs_and_the_bees.models.User;

@Getter
@Setter
public class UserDTO {

    private String userDTOName;

    private int userDTOId;

    public UserDTO(User user) {
        this.userDTOName = user.getUsername();
        this.userDTOId = user.getId();
    }
}
