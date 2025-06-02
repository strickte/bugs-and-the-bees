package org.launchcode.bugs_and_the_bees.service;

import org.launchcode.bugs_and_the_bees.data.ProjectRepository;
import org.launchcode.bugs_and_the_bees.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void createProject(Project project) {
        projectRepository.save(project);
    }

    public Iterable<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteByProjectId(Integer id) {
        projectRepository.deleteById(id);
    }

    public void updateProject(Project updatedProject, Integer id) {
        Optional<Project> optionalProject = projectRepository.findById(id);

        if (optionalProject.isEmpty()) {
            throw new IllegalArgumentException("Project does not exist");
        }

        Project dbProject = optionalProject.get();
        dbProject.setName(updatedProject.getName());
        dbProject.setOccasion(updatedProject.getOccasion());
        dbProject.setPhotoUrl(updatedProject.getPhotoUrl());
        dbProject.setUsedSkeins(updatedProject.getUsedSkeins());
        dbProject.setUsedSkeins(updatedProject.getUsedSkeins());
        dbProject.setTotalSkeins(updatedProject.getTotalSkeins());
        projectRepository.save(dbProject);
    }
}
