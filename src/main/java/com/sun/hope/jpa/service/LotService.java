package com.sun.hope.jpa.service;

import com.sun.hope.jpa.entity.Lot;
import com.sun.hope.jpa.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotService {
    LotRepository lotRepository;

    @Autowired
    LotService(LotRepository lotRepository){
        this.lotRepository = lotRepository;
    }

    public List<Lot> getAll(){
        return lotRepository.findAll();
    }
}
