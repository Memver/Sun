package com.sun.hope.jpa.service;

import com.sun.hope.jpa.entity.SignUp;
import com.sun.hope.jpa.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SignUpService {
    private final SignUpRepository signUpRepository;

    @Autowired
    public SignUpService(SignUpRepository signUpRepository){
        this.signUpRepository = signUpRepository;
    }

    public SignUp create(SignUp signUp){
        signUp.setId(null);
        return signUpRepository.save(signUp);
    }

    public SignUp getByName(String name){
        return signUpRepository.findByName(name);
    }

    public List<SignUp> getAll(){
        return signUpRepository.findAll();
    }

    public SignUp getById(Long id){
        Optional<SignUp> signUpOptional = signUpRepository.findById(id);
        return signUpOptional.orElse(null);
    }

    public SignUp createOrUpdate(SignUp signUp){
        return signUpRepository.save(signUp);
    }

    // return SignUp, if data exists by id
    // return null, if data does not exists by id
    public SignUp deleteById(Long id){
        if(signUpRepository.existsById(id)){
            SignUp signUp = signUpRepository.findById(id).get();
            signUpRepository.deleteById(id);
            return signUp;
        }else{
            return null;
        }
    }
}
