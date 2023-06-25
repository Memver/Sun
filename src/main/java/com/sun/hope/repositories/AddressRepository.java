package com.sun.hope.repositories;

import com.sun.hope.models.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressRepository {
    List<Address> findAll() throws SQLException;

    int findIdByName(Address address) throws SQLException;

    Address findById(int id) throws SQLException;

    Address add(Address address) throws SQLException;
}
