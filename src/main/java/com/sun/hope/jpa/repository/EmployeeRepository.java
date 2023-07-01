package com.sun.hope.jpa.repository;

import com.sun.hope.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM EMPLOYEE WHERE FIRST_NAME=?1 AND LAST_NAME=?2", nativeQuery = true)
    Employee findByFirstNameAndLastName(String firstName, String lastName);

    Employee findByLastName(String lastName);
}