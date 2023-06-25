package com.sun.hope.controllers;

import com.sun.hope.models.Person;
import com.sun.hope.repositories.PersonRepository;
import com.sun.hope.repositories.PersonRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class BlogController {

    private final PersonRepository personRepository = new PersonRepositoryImpl();

    @GetMapping("/person/add")
    public String personAdd(Model model){

        return "person-add";
    }

    @PostMapping("/person/add")
    public String personPostAdd(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam Long phoneNumber,
                                Model model) throws SQLException {
        personRepository.add(new Person(firstName, lastName, phoneNumber));
        return "redirect:/person";
    }
}

