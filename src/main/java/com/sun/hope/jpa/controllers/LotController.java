package com.sun.hope.jpa.controllers;

import com.sun.hope.jpa.entity.Lot;
import com.sun.hope.jpa.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lot")
public class LotController {
    LotService lotService;

    @Autowired
    LotController(LotService lotService){
        this.lotService = lotService;
    }

    @GetMapping
    public ResponseEntity<List<Lot>> getAll(){
        return ResponseEntity.ok(lotService.getAll());
    }
}
