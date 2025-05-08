package org.launchcode.bugs_and_the_bees.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private int id;

}