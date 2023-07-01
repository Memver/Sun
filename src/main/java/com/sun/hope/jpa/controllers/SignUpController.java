package com.sun.hope.jpa.controllers;

import com.sun.hope.jpa.entity.SignUp;
import com.sun.hope.jpa.service.SignUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signUp")
public class SignUpController {
    private final SignUpService signUpService;
    Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @Autowired
    public SignUpController(SignUpService signUpService){
        this.signUpService = signUpService;
    }

    // get
    @GetMapping
    public List<SignUp> getAll(){
        logger.info("/signUp Get \"getAll\"");
        return signUpService.getAll();
    }

    @GetMapping("/name")
    public SignUp getSignUpByName(@RequestParam String name){
        logger.info("/signUp/name Get \"getSignUpByName\"");
        return signUpService.getByName(name);
    }

    @GetMapping("/{id}")
    public SignUp getById(@PathVariable Long id){
        logger.info("/signUp/{id} Get \"getById\"");
        return signUpService.getById(id);
    }

    // create
    @PostMapping
    public SignUp create(@RequestBody SignUp signUp){
        logger.info("/signUp Post \"create\"");
        return signUpService.create(signUp);
    }

    @PutMapping
    public ResponseEntity<SignUp> put(@RequestBody SignUp signUp){
        logger.info("/signUp Put \"put\"");
        // IF NOT EXISTS, then 404
        if(signUpService.getById(signUp.getId()) == null){
            return ResponseEntity.notFound().build();
        }else{
            // IF EXISTS, then 200
            return ResponseEntity.ok(signUpService.createOrUpdate(signUp));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SignUp> deleteById(@PathVariable Long id){
        logger.info("/signUp/{id} Delete \"deleteById\"");
        //delete
        SignUp signUpDeleted = signUpService.deleteById(id);

        // check response by signUpService.deleteById()
        if(signUpDeleted == null){
            // IF NOT EXISTS, then 404
            return ResponseEntity.notFound().build();
        }else{
            //IF EXISTS, then 200
            return ResponseEntity.ok(signUpDeleted);
        }
    }

    @PatchMapping("/name")
    public ResponseEntity<SignUp> patchNameOfSignUp(@RequestBody SignUp signUpInput){
        logger.info("/signUp/name Patch \"patchNameOfSignUp\"");
        SignUp signUpById = signUpService.getById(signUpInput.getId());

        if(signUpById == null){
            // IF NOT EXISTS in database, then 404
            return ResponseEntity.notFound().build();
        }else{
            //IF EXISTS in database, then 200
            signUpById.setName(signUpInput.getName());
            SignUp signUpNew = signUpService.createOrUpdate(signUpById);

            return ResponseEntity.ok(signUpNew);
        }
    }
}
