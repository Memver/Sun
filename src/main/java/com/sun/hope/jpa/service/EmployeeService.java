package com.sun.hope.jpa.service;

import com.sun.hope.jpa.entity.Employee;
import com.sun.hope.jpa.entity.SignUp;
import com.sun.hope.jpa.repository.EmployeeRepository;
import com.sun.hope.jpa.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final SignUpRepository signUpRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, SignUpRepository signUpRepository){
        this.employeeRepository = employeeRepository;
        this.signUpRepository = signUpRepository;
    }

    public Employee findByLastName(String lastName){
        return employeeRepository.findByLastName(lastName);
    }

    //Create, Read, Update, Delete
    public Employee create(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee createOrUpdate(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    // return null, if id not exists in database
    // return Employee, if id exists in database
    public Employee getById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        employeeRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return employeeRepository.existsById(id);
    }

    public Employee updateSignUp(Long employeeId, SignUp signUp){
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if(employee == null){
            return null;
        }else{
            // cascadeType.All, @OneToOne
            // to delete old signUp, we need
            // 1) remember signUpId
            Long idSignUp = employee.getSignUp().getId();
            // 2) delete relation between employee and signUp
            employee.setSignUp(null);
            // 3) delete signUp
            signUpRepository.deleteById(idSignUp);

            employee.setSignUp(signUp);
            return employeeRepository.save(employee);
        }
    }
}
