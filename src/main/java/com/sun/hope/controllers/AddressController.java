package com.sun.hope.controllers;

import com.sun.hope.models.Address;
import com.sun.hope.repositories.AddressRepository;
import com.sun.hope.repositories.AddressRepositoryImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class AddressController {

    private final AddressRepository addressRepository = new AddressRepositoryImpl();

    @GetMapping("/address")
    public List<Address> getAllAddress() throws SQLException {
        return addressRepository.findAll();
    }
}
