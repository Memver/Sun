package com.sun.hope.controllers;

import com.sun.hope.models.Address;
import com.sun.hope.repositories.AddressRepository;
import com.sun.hope.repositories.AddressRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class AddressController {

    private final AddressRepository addressRepository = new AddressRepositoryImpl();
    Logger logger = LoggerFactory.getLogger(AddressController.class);

    @GetMapping("/address")
    public List<Address> getAllAddress() throws SQLException {
        logger.info("run GET /address");
        return addressRepository.findAll();
    }
}
