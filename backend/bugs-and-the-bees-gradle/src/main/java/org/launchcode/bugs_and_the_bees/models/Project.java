package org.launchcode.bugs_and_the_bees.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Project extends AbstractEntity {

    private int totalSkeins;

    private int usedSkeins;

    //occasion i.e. Christmas, new baby, etc.
    private String occasion;

    //target completion date
    private LocalDate targetDate;

    //photo url from Unsplash derived from occasion
    private String photoUrl;
}

