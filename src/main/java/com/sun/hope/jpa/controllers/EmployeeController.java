package com.sun.hope.jpa.controllers;

import com.sun.hope.jpa.entity.Employee;
import com.sun.hope.jpa.entity.SignUp;
import com.sun.hope.jpa.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    //Create, Read, Update, Delete
    @PostMapping("/new")
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        logger.info("/employee/new Post \"create\"");
        // saving employee
        return ResponseEntity.ok(employeeService.create(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){
        logger.info("/employee Get \"getAll\"");
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id){
        logger.info("/employee/{id} Get \"getById\"");
        Employee employeeById = employeeService.getById(id);
        if(employeeById == null){
            //404
            return ResponseEntity.notFound().build();
        }
        else{
            //200
            return ResponseEntity.ok(employeeById);
        }
    }

    @PutMapping
    public ResponseEntity<Employee> updateById(@RequestBody Employee employeeInput){
        logger.info("/employee Put \"UpdateById\"");
        if(employeeService.existsById(employeeInput.getId())){
            return ResponseEntity.ok(employeeService.createOrUpdate(employeeInput));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteById(@PathVariable Long id){
        logger.info("/employee/{id} Delete \"deleteById\"");
        Employee employeeById = employeeService.getById(id);
        if(employeeById == null){
            // if id not exists in database, then 404
            return ResponseEntity.notFound().build();
        }else{
            // if id exists in database, then 200
            // delete
            employeeService.deleteById(id);
            return ResponseEntity.ok(employeeById);
        }
    }

    @PatchMapping("/updateSignUp/{id}")
    public ResponseEntity<Employee> updateSignUp(
            @PathVariable("id") Long EmployeeId,
            @RequestBody SignUp signUp
    ){
        logger.info("employee/updateSignUp/{id} Patch \"updateSignUp\"");
        Employee UpdatedEmployee = employeeService.updateSignUp(EmployeeId, signUp);
        if(UpdatedEmployee == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(UpdatedEmployee);
        }
    }

    // for one-directional (now bi-directional) Employee--Lot
//    @DeleteMapping("/lot/{id}")
//    public ResponseEntity<Employee> deleteLots(@PathVariable("id") Long employeeId){
//        logger.info("employee/lot/{id} Delete \"deleteLots\"");
//        Employee employeeById = employeeRepository.findById(employeeId).orElse(null);
//        if(employeeById == null){
//            //404
//            return ResponseEntity.notFound().build();
//        } else {
//            //200
//            List<Lot> lots = employeeById.getLots();
//            employeeById.setLots(null);
//            lotRepository.deleteAll(lots);
//
//            return ResponseEntity.ok(employeeRepository.save(employeeById));
//        }
//    }
}