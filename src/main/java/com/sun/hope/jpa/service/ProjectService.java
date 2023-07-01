package com.sun.hope.jpa.service;

import com.sun.hope.jpa.entity.Project;
import com.sun.hope.jpa.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    ProjectRepository projectRepository;
    
    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    //Create, Read, Update, Delete
    public Project create(Project project){
        project.setId(null);
        return projectRepository.save(project);
    }

    public Project createOrUpdate(Project project){
        return projectRepository.save(project);
    }

    public List<Project> getAll(){
        return projectRepository.findAll();
    }

    // return null, if id not exists in database
    // return Project, if id exists in database
    public Project getById(Long id){
        return projectRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        projectRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return projectRepository.existsById(id);
    }
}
