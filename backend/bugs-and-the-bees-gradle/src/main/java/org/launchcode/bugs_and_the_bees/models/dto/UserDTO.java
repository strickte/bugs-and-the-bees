package org.launchcode.bugs_and_the_bees.models.dto;

import lombok.Getter;
import lombok.Setter;
import org.launchcode.bugs_and_the_bees.models.User;

@Getter
@Setter
public class UserDTO {

    private String userDTOName;

    private int userDTId;

    public UserDTO(User user) {
        this.userDTOName = user.getUsername();
        this.userDTId = user.getId();
    }
}
