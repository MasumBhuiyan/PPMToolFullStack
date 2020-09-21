package io.masumbhuiyan.ppmtool.controllers;

import io.masumbhuiyan.ppmtool.models.Project;
import io.masumbhuiyan.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<Project> create(@RequestBody Project project) {
        Project project1 = projectService.save(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }
}
