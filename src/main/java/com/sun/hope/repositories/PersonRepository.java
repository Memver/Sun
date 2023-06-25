package com.sun.hope.repositories;

import com.sun.hope.models.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonRepository{

    List<Person> findAll() throws SQLException;
    Person add(Person person) throws SQLException;

    int findIdByName(Person person) throws SQLException;

    Person findById(int id) throws SQLException;

    boolean hasInRep(Person person) throws SQLException;
}
