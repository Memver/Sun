package com.sun.hope.controllers;

import com.sun.hope.repositories.PersonRepository;
import com.sun.hope.repositories.PersonRepositoryImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class PersonController {

    private final PersonRepository personRepository = new PersonRepositoryImpl();

    @GetMapping("/person")
    public ResponseEntity<?> personMain() throws SQLException {
        return ResponseEntity
                .status(200)
                .body(personRepository.findAll());
    }
}
