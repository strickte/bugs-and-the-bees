package org.launchcode.bugs_and_the_bees.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Project extends AbstractEntity {

    @PositiveOrZero
    private int totalSkeins;

    @PositiveOrZero
    private int usedSkeins;

    //occasion i.e. Christmas, new baby, etc.
    @NotBlank(message = "Occasion is required")
    @Size(min = 2, max = 50, message = "Occasion must be between 2 and 50 characters")
    private String occasion;

    //target completion date
    @NotNull(message = "Completion goal is required")
    private LocalDate targetDate;

    //photo url from Unsplash derived from occasion
    @NotNull
    private String photoUrl;

    @ManyToOne
    @NotNull
    private User user;
}

