package org.launchcode.bugs_and_the_bees.controllers;

import jakarta.validation.Valid;
import org.launchcode.bugs_and_the_bees.data.ProjectRepository;
import org.launchcode.bugs_and_the_bees.models.Project;
import org.launchcode.bugs_and_the_bees.models.dto.ProjectDTO;
import org.launchcode.bugs_and_the_bees.models.dto.UpdateProjectDTO;
import org.launchcode.bugs_and_the_bees.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value ="http://localhost:5173")
@RestController
@RequestMapping("/projects")
public class ProjectController {

@Autowired
private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createProject(@RequestBody @Valid ProjectDTO projectDTO, Errors errors){

        //Validation errors from @Valid using lambda expression
        if (errors.hasErrors()) {
            Map<String, String> validationErrors = new HashMap<>();
            errors.getFieldErrors().forEach(error -> validationErrors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validationErrors);
        }

        projectService.createProject(projectDTO);
        return ResponseEntity.ok(Map.of("message", "Project successfully created"));
    }

//    @GetMapping("/user-landing")
//    public Iterable<Project> getAllUserProjects(){
//        return projectService.getAllProjects();
//    }

    @GetMapping("/user-landing")
    public ResponseEntity<Iterable<Project>> getProjectsByUserId(@RequestParam int userId) {
        Iterable<Project> projects = projectService.getProjectsByUserId(userId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/update")
    public ResponseEntity<Project> getProjectById(@RequestParam int projectId) {
        Project project = projectService.getProjectById(projectId);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteProject(@PathVariable Integer id) {
        projectService.deleteByProjectId(id);
        return ResponseEntity.ok(Map.of("message", "Project successfully deleted"));

    }

    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, String>> updateProject (@PathVariable Integer id, @Valid @RequestBody UpdateProjectDTO updatedProjectDTO, Errors errors) {
        //Validation errors from @Valid using lambda expression
        if (errors.hasErrors()) {
            Map<String, String> validationErrors = new HashMap<>();
            errors.getFieldErrors().forEach(error -> validationErrors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validationErrors);
        }

        projectService.updateProject(updatedProjectDTO, id);
        return ResponseEntity.ok(Map.of("message", "Project successfully updated"));
    }
}
