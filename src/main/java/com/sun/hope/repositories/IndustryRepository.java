package com.sun.hope.repositories;

import com.sun.hope.models.Address;
import com.sun.hope.models.IndAddPer;
import com.sun.hope.models.Industry;

import java.sql.SQLException;
import java.util.List;

public interface IndustryRepository {
    List<Industry> findAll() throws SQLException;

    // Возвращает industry, если найдет industry по id.
    // return null, if industry not found
    Industry findById(int id) throws SQLException;

    List<IndAddPer> findByParams(Industry industry, Address address) throws SQLException;

    String addCondition(String sql, String key, String value);

    // Возвращает > 0, если площадка найдена.
    // return -1, if industry not found
    int findIdByName(Industry industry) throws SQLException;

    Industry add(Industry industry) throws SQLException;

    Industry deleteById(int id) throws SQLException;

    IndAddPer toIAP(int id) throws SQLException;
}
