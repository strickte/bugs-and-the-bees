package org.launchcode.bugs_and_the_bees.data;

import org.launchcode.bugs_and_the_bees.models.Project;
import org.launchcode.bugs_and_the_bees.models.dto.ProjectDTO;
import org.launchcode.bugs_and_the_bees.models.dto.UpdateProjectDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {
    List<Project> findByUserId(int userId);
    Project findById(int id);
//Project updateProject(UpdateProjectDTO updateProjectDTO, int id);
}
