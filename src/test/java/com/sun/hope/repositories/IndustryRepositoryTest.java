package com.sun.hope.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

class IndustryRepositoryTest {
    private final IndustryRepository industryRepository = new IndustryRepositoryImpl();

    @Test
    @DisplayName("conditionAdd")
    public void addCondition() {
        String sql = "SELECT foo.id as id, addressId, personId, city," +
                " street, home, building, firstName, lastName,\n" +
                "    phoneNumber, target, area, aim FROM\n" +
                "(SELECT industry.id as id, addressId, personId, target,area, aim, city, street, home, building FROM \n" +
                " \tindustry INNER JOIN address ON industry.addressId = address.id) AS \n" +
                "foo INNER JOIN person ON foo.personId = person.id\n" +
                "WHERE";
        String key1 = "target";
        String value1 = "";

        String sqlAfter = industryRepository.addCondition(sql, key1, value1);
        Assertions.assertEquals(sql, sqlAfter);
        Assertions.assertSame(sql, sqlAfter);
        Assertions.assertTimeout(Duration.ofSeconds(1), () -> {
            System.out.println("check TimeOut");
        });
        if(!sql.equals(sqlAfter)){
            Assertions.fail();
        }
    }
}