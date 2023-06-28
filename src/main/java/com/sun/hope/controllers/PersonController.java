package com.sun.hope.controllers;

import com.sun.hope.repositories.PersonRepository;
import com.sun.hope.repositories.PersonRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class PersonController {

    private final PersonRepository personRepository = new PersonRepositoryImpl();
    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @GetMapping("/person")
    public ResponseEntity<?> personMain() throws SQLException {
        logger.info("run GET /person");
        return ResponseEntity
                .status(200)
                .body(personRepository.findAll());
    }
}
