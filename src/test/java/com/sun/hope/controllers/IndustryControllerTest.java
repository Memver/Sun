package com.sun.hope.controllers;

import com.sun.hope.models.CreateIndustry;
import com.sun.hope.models.Er;
import com.sun.hope.models.IndAddPer;
import com.sun.hope.repositories.IndustryRepository;
import com.sun.hope.repositories.IndustryRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class IndustryControllerTest {

    private final IndustryController industryController = new IndustryController();
    private final IndustryRepository industryRepository = new IndustryRepositoryImpl();
    private static final String BAD_REQUEST = "bad request";
    private static final String NOT_FOUND = "not found";

    @Test
    void createIndustry() throws SQLException {
        Er er = new Er();
        er.setErrorMessage(BAD_REQUEST);

        // Fields must not be empty
        CreateIndustry createIndustry2 = new CreateIndustry("Magadan", " ", 20, "3", "Elizaveta", "Novikova",
                88003323232L,"buy", 432 , "talking");
        CreateIndustry createIndustry3 = new CreateIndustry("Magadan", "", 20, "3", "Elizaveta", "Novikova",
                88003323232L,"sell", 432 , "talking");
        CreateIndustry createIndustry4 = new CreateIndustry("Magadan", " ", 20, "3", "Elizaveta", "Novikova",
                88003323232L,"sell", 432 , "talking");
        // Fields must be > 0
        CreateIndustry createIndustry6 = new CreateIndustry("Magadan", "Lenina", -30, "3", "Elizaveta", "Novikova",
                88003323232L,"buy", 432 , "talking");
        CreateIndustry createIndustry8 = new CreateIndustry("Magadan", "Lenina", -20, "3", "Elizaveta", "Novikova",
                88003323232L,"buy", 432 , "talking");
        // Field target must be sell or buy
        CreateIndustry createIndustry9 = new CreateIndustry("Magadan", "Lenina", 20, "3", "Elizaveta", "Novikova",
                88003323232L,"jaba", 432 , "talking");

        // Right requests
        CreateIndustry createIndustry = new CreateIndustry("", "", 0, "", "Elizaveta", "Novikova",
                88003323232L,"buy", 0 , "");
        CreateIndustry createIndustry10 = new CreateIndustry("Magadan", "Lenina", 20, "3", "Elizaveta", "Novikova",
                88003323232L,"buy", 432 , "talking");
        CreateIndustry createIndustry5 = new CreateIndustry("Magadan", "Lenina", 20, "3", "Elizaveta", "Novikova",
                88003323232L,"sell", 432 , "talking");
        CreateIndustry createIndustry11 = new CreateIndustry("Magadan", "Lenina", 20, "", "Elizaveta", "Novikova",
                88003323232L,"sell", 432 , "talking");

        Assertions.assertEquals(er, industryController.createIndustry(createIndustry2).getBody());
        Assertions.assertEquals(er, industryController.createIndustry(createIndustry3).getBody());
        Assertions.assertEquals(er, industryController.createIndustry(createIndustry4).getBody());
        Assertions.assertEquals(er, industryController.createIndustry(createIndustry6).getBody());
        Assertions.assertEquals(er, industryController.createIndustry(createIndustry8).getBody());
        Assertions.assertEquals(er, industryController.createIndustry(createIndustry9).getBody());

        Assertions.assertNotEquals(er, industryController.createIndustry(createIndustry).getBody());
        Assertions.assertNotEquals(er, industryController.createIndustry(createIndustry10).getBody());
        Assertions.assertNotEquals(er, industryController.createIndustry(createIndustry5).getBody());
        Assertions.assertNotEquals(er, industryController.createIndustry(createIndustry11).getBody());

        // Проверка на возврат той же площадки, что и в запросе
        IndAddPer industryActual = (IndAddPer) industryController.createIndustry(createIndustry).getBody();
        Assertions.assertEquals(createIndustry.getTarget(), industryActual.getTarget());
    }

    @Test
    void getIndustryById() throws SQLException {
        // Проверка на 404
        Er er = new Er(NOT_FOUND);
        Er erByMethod = (Er)industryController.getIndustryById(100000).getBody();
        Assertions.assertEquals(er.getErrorMessage(), erByMethod.getErrorMessage());
    }

    @Test
    void deleteById() throws SQLException {
        // Проверка на 404
        Er er = new Er(NOT_FOUND);
        Er erByMethod = (Er)industryController.deleteById(1000).getBody();
        Assertions.assertEquals(er.getErrorMessage(), erByMethod.getErrorMessage());
    }
}