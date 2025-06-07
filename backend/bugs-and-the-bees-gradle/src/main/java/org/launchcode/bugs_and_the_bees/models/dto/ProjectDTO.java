package org.launchcode.bugs_and_the_bees.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private String name;

    private int totalSkeins;

    private int usedSkeins;

    private String occasion;

    private LocalDate targetDate;

    private String photoUrl;

    private int userId;

}
