package com.sun.hope.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Blog {

    @Id
    private int id;
    private String state;

}
