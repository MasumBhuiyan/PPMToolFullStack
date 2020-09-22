package io.masumbhuiyan.ppmtool.services;

import io.masumbhuiyan.ppmtool.execptions.ProjectIdentifierException;
import io.masumbhuiyan.ppmtool.models.Project;
import io.masumbhuiyan.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project createOrUpdateProject(Project project) {
        try {
            return projectRepository.save(project);
        } catch (Exception exception) {
            throw new ProjectIdentifierException("Project identifier " + project.getProjectIdentifier() + " already exists.");
        }
    }

    public Project findProjectByIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
        if( project == null ) {
            throw new ProjectIdentifierException("Project identifier " + projectIdentifier + " does not exists.");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
        if (project == null) {
            throw new ProjectIdentifierException("Can not delete project with identifier " + projectIdentifier + ", because it does not exist.");
        }
        projectRepository.delete(project);
    }
}
