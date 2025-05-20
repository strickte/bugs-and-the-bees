package org.launchcode.bugs_and_the_bees.controllers;

import org.launchcode.bugs_and_the_bees.data.ProjectRepository;
import org.launchcode.bugs_and_the_bees.models.Project;
import org.launchcode.bugs_and_the_bees.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/projects")
public class ProjectController {

@Autowired
private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public Project createProject(@RequestBody Project project){
        return projectService.createProject(project);
    }
}
