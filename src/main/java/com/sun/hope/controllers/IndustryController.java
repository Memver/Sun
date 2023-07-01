package com.sun.hope.controllers;

import com.sun.hope.models.*;
import com.sun.hope.repositories.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class IndustryController {

    private final PersonRepository personRepository = new PersonRepositoryImpl();
    private final AddressRepository addressRepository = new AddressRepositoryImpl();
    private final IndustryRepository industryRepository = new IndustryRepositoryImpl();
    private static final String BAD_REQUEST = "bad request";
    private static final String NOT_FOUND = "not found";
    private static final Logger logger = LoggerFactory.getLogger(IndustryController.class);

    @PostMapping("/industry")
    public ResponseEntity<?> createIndustry(@RequestBody CreateIndustry createIndustry) throws SQLException
    {
        logger.info("run POST /industry");
        Er er = new Er();
        int f = 0;
        //400
        // проверка, если target = buy, то пропускает другие проверки
        if(
                !er.isEmpty(createIndustry.getFirstName()) && !er.isEmpty(createIndustry.getLastName())  &&
                !er.lessThanZero(createIndustry.getPhoneNumber()) && createIndustry.getHome() >= 0 &&
                !createIndustry.getCity().contains(" ") && !createIndustry.getStreet().contains(" ") &&
                !createIndustry.getBuilding().contains(" ") && createIndustry.getArea() >= 0 &&
                !createIndustry.getAim().contains(" ") && createIndustry.getTarget().equals("buy")
        )
        {f = 1;}

        // проверка, если не идеальный buy
        if(f == 0 &&(er.isEmpty(createIndustry.getCity()) || er.isEmpty(createIndustry.getStreet()) ||
                createIndustry.getBuilding().contains(" ") || er.isEmpty(createIndustry.getFirstName()) ||
                er.isEmpty(createIndustry.getLastName())|| er.isEmpty(createIndustry.getTarget())  ||
                er.isEmpty(createIndustry.getAim()) ||
                er.lessThanZero(createIndustry.getHome()) || er.lessThanZero(createIndustry.getPhoneNumber()) ||
                er.lessThanZero(createIndustry.getArea()) ||
                !er.targetCondition(createIndustry.getTarget()))
        ){
            logger.error("error POST /industry 400");
            er.setErrorMessage(BAD_REQUEST);
            return ResponseEntity
                    .status(400)
                    .body(er);
        }

        // 200
        // создаем адрес
        Address address = addressRepository.add(new Address(createIndustry.getCity(), createIndustry.getStreet(),
                createIndustry.getHome(), createIndustry.getBuilding()));
        // создаем человека
        Person person = personRepository.add(new Person(createIndustry.getFirstName(), createIndustry.getLastName(),
                createIndustry.getPhoneNumber()));

        // создаем площадку
        Industry industry = new Industry(0, address.getId(), person.getId(),
                createIndustry.getTarget(), createIndustry.getArea(), createIndustry.getAim());
        Industry resCreate = industryRepository.add(industry);

        return ResponseEntity
                .status(200)
                .body(industryRepository.toIAP(resCreate.getId()));
    }

    @GetMapping("/industry")
    public ResponseEntity<?> getSellBuyIndustries(@RequestParam(required = false, defaultValue = "") String target,
                                                               @RequestParam(required = false, defaultValue = "0") int area,
                                                               @RequestParam(required = false, defaultValue = "") String aim,
                                                               @RequestParam(required = false, defaultValue = "") String city,
                                                               @RequestParam(required = false, defaultValue = "") String street,
                                                               @RequestParam(required = false, defaultValue = "0") int home,
                                                               @RequestParam(required = false, defaultValue = "") String building)throws SQLException{
        logger.info("run GET /industry");
        return ResponseEntity
                .status(200)
                .body(industryRepository.findByParams(new Industry(0,0,0,target, area, aim), new Address(0, city, street, home, building)));
    }

    @GetMapping("/industry/{id}")
    public ResponseEntity<?> getIndustryById(@PathVariable int id)throws SQLException{
        logger.info("run GET /industry/{id}");
        Industry industry = industryRepository.findById(id);
        // Площадка по этому id не была найдена
        //404
        if(industry == null){
            logger.error("error GET /industry/{id} 404");
            return ResponseEntity
                    .status(404)
                    .body(new Er(NOT_FOUND));
        }
        // 200
        return ResponseEntity
                .status(200)
                .body(industryRepository.toIAP(industry.getId()));
    }

    @DeleteMapping("/industry/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id)throws SQLException{
        logger.info("run DELETE /industry/{id}");
        Industry industry = industryRepository.findById(id);
        // Площадка по этому id не была найдена
        //404
        if(industry == null){
            logger.error("error DELETE /industry/{id} 404");
            return ResponseEntity
                    .status(404)
                    .body(new Er(NOT_FOUND));
        }

        // сохраняем результат
        IndAddPer result = industryRepository.toIAP(industry.getId());

        // удаляем
        industryRepository.deleteById(id);

        // 200
        return ResponseEntity
                .status(200)
                .body(result);
    }
}