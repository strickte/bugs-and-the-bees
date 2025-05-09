package org.launchcode.bugs_and_the_bees.data;

import org.launchcode.bugs_and_the_bees.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {
}
