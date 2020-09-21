package io.masumbhuiyan.ppmtool.services;

import io.masumbhuiyan.ppmtool.models.Project;
import io.masumbhuiyan.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project save(Project project) {
        return projectRepository.save(project);
    }
}
