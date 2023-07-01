package com.sun.hope.jpa.controllers;

import com.sun.hope.jpa.entity.Project;
import com.sun.hope.jpa.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    ProjectService projectService;
    Logger logger = LoggerFactory.getLogger(ProjectController.class);
    
    @Autowired ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    //Create, Read, Update, Delete
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project){
        logger.info("/project Post \"create\"");
        // saving project
        return ResponseEntity.ok(projectService.create(project));
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll(){
        logger.info("/project Get \"getAll\"");
        return ResponseEntity.ok(projectService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable Long id){
        logger.info("/project/{id} Get \"getById\"");
        Project projectById = projectService.getById(id);
        if(projectById == null){
            //404
            return ResponseEntity.notFound().build();
        }
        else{
            //200
            return ResponseEntity.ok(projectById);
        }
    }

    @PutMapping
    public ResponseEntity<Project> updateById(@RequestBody Project projectInput){
        logger.info("/project Put \"updateById\"");
        if(projectService.existsById(projectInput.getId())){
            return ResponseEntity.ok(projectService.createOrUpdate(projectInput));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteById(@PathVariable Long id){
        logger.info("/project/{id} Delete \"deleteById\"");
        Project projectById = projectService.getById(id);
        if(projectById == null){
            // if id not exists in database, then 404
            return ResponseEntity.notFound().build();
        }else{
            // if id exists in database, then 200
            // delete
            projectService.deleteById(id);
            return ResponseEntity.ok(projectById);
        }
    }
}