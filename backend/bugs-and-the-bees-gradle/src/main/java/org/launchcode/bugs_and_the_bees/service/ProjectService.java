package org.launchcode.bugs_and_the_bees.service;

import org.launchcode.bugs_and_the_bees.data.ProjectRepository;
import org.launchcode.bugs_and_the_bees.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }
}
