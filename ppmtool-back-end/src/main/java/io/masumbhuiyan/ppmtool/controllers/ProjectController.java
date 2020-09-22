package io.masumbhuiyan.ppmtool.controllers;

import io.masumbhuiyan.ppmtool.models.Project;
import io.masumbhuiyan.ppmtool.services.ProjectService;
import io.masumbhuiyan.ppmtool.validators.ProjectValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectValidation projectValidation;

    @PostMapping("")
    public ResponseEntity<?> createOrUpdateProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        ResponseEntity<?> projectValidationErrors = projectValidation.getProjectValidationErrors(bindingResult);
        if ( projectValidationErrors != null ) {
            return projectValidationErrors;
        }
        Project project1 = projectService.createOrUpdateProject(project);
        return new ResponseEntity<>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> findProjectByIdentifier(@PathVariable String projectIdentifier) {
        Project project = projectService.findProjectByIdentifier(projectIdentifier);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> findAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectIdentifier}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectIdentifier) {
        projectService.deleteProjectByIdentifier(projectIdentifier);
        return new ResponseEntity<String>("Project with " + projectIdentifier + " successfully deleted", HttpStatus.OK);
    }
}
