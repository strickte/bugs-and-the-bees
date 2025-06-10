package org.launchcode.bugs_and_the_bees.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProjectDTO {

    @NotBlank
    @Size(min = 3, max = 75, message = "Name must be between 3 and 75 characters")
    private String name;

    @NotNull(message = "Total skeins is required")
    @PositiveOrZero(message = "Total skeins must be positive or zero")
    private Integer totalSkeins;

    @NotNull(message = "Used skeins is required")
    @PositiveOrZero(message = "Used skeins must be positive or zero")
    private Integer usedSkeins;

    @NotBlank(message = "Occasion is required")
    @Size(min = 2, max = 50, message = "Occasion must be between 2 and 50 characters")
    private String occasion;

    @NotNull(message = "Completion goal is required")
    private LocalDate targetDate;

    private String photoUrl;

    private Integer userId;

}
