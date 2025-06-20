package org.launchcode.bugs_and_the_bees.service;

import lombok.AllArgsConstructor;
import org.launchcode.bugs_and_the_bees.data.ProjectRepository;
import org.launchcode.bugs_and_the_bees.data.UserRepository;
import org.launchcode.bugs_and_the_bees.models.Project;
import org.launchcode.bugs_and_the_bees.models.User;
import org.launchcode.bugs_and_the_bees.models.dto.ProjectDTO;
import org.launchcode.bugs_and_the_bees.models.dto.UpdateProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    private final UserRepository userRepository;

    public void createProject(ProjectDTO projectDTO) {
        Project saveProject = new Project();
        Optional<User> optionalUser = userRepository.findById(projectDTO.getUserId());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User does not exist");
        }

        User user = optionalUser.get();
        saveProject.setName(projectDTO.getName());
        saveProject.setTotalSkeins(projectDTO.getTotalSkeins());
        saveProject.setUsedSkeins(projectDTO.getUsedSkeins());
        saveProject.setOccasion(projectDTO.getOccasion());
        saveProject.setTargetDate(projectDTO.getTargetDate());
        saveProject.setPhotoUrl(projectDTO.getPhotoUrl());
        saveProject.setUser(user);
        projectRepository.save(saveProject);
    }

//    public Iterable<Project> getAllProjects(){
//        return projectRepository.findAll();
//    }

    public Iterable<Project> getProjectsByUserId(int userId) {
        return projectRepository.findByUserId(userId);
    }

    public Project getProjectById(int projectId) {
        return projectRepository.findById(projectId);
    }

    public void deleteByProjectId(Integer id) {
        projectRepository.deleteById(id);
    }

    public void updateProject(UpdateProjectDTO updatedProjectDTO, Integer id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(updatedProjectDTO.getUserId());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User does not exist");
        }

        if (optionalProject.isEmpty()) {
            throw new IllegalArgumentException("Project does not exist");
        }
        User user = optionalUser.get();
        Project dbProject = optionalProject.get();
        dbProject.setName(updatedProjectDTO.getName());
        dbProject.setOccasion(updatedProjectDTO.getOccasion());
        dbProject.setPhotoUrl(updatedProjectDTO.getPhotoUrl());
        dbProject.setUsedSkeins(updatedProjectDTO.getUsedSkeins());
        dbProject.setTotalSkeins(updatedProjectDTO.getTotalSkeins());
        dbProject.setTargetDate(updatedProjectDTO.getTargetDate());
        projectRepository.save(dbProject);
    }
}
