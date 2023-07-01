package com.sun.hope.jpa.repository;

import com.sun.hope.jpa.entity.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepository extends JpaRepository<SignUp, Long> {
    SignUp findByName(String name);
}
