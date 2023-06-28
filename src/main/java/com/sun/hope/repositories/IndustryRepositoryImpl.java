package com.sun.hope.repositories;

import com.sun.hope.models.Address;
import com.sun.hope.models.IndAddPer;
import com.sun.hope.models.Industry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.hope.repositories.PersonRepositoryImpl.*;

@Repository
public class IndustryRepositoryImpl implements IndustryRepository{

    Logger logger = LoggerFactory.getLogger(IndustryRepositoryImpl.class);

    @Override
    public List<Industry> findAll() throws SQLException {
        logger.info("run industryRepository findAll()");
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM industry");
        ResultSet rs = prepStat.executeQuery();
        ArrayList<Industry> list = new ArrayList<>();
        while (rs.next()){
            list.add(new Industry(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                    rs.getString(4), rs.getInt(5), rs.getString(6)));
        }
        connection.close();
        return list;
    }

    @Override
    public Industry findById(int id) throws SQLException{
        logger.info("run industryRepository findById()");
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        // Поиск человека по id
        PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM industry WHERE id = ?");
        prepStat.setInt(1, id);
        ResultSet rs = prepStat.executeQuery();
        if(!rs.next()){
            connection.close();
            return null;
        }

        connection.close();
        return new Industry(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                rs.getString(4), rs.getInt(5), rs.getString(6));
    }

    @Override
    public List<IndAddPer> findByParams(Industry industry, Address address) throws SQLException {
        logger.info("run industryRepository findByParams()");
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "SELECT foo.id as id, addressId, personId, city," +
                " street, home, building, firstName, lastName,\n" +
                "    phoneNumber, target, area, aim FROM\n" +
                "(SELECT industry.id as id, addressId, personId, target,area, aim, city, street, home, building FROM \n" +
                " \tindustry INNER JOIN address ON industry.addressId = address.id) AS \n" +
                "foo INNER JOIN person ON foo.personId = person.id\n" +
                "WHERE";

        //Добавляем в sql target
        if(industry.getTarget().equals("sell") || industry.getTarget().equals("buy")){
            if(sql.endsWith("WHERE")){
                sql += " UPPER(target) = '" + industry.getTarget().toUpperCase() + "'";
            }else{
                sql += " and UPPER(target) = '" + industry.getTarget().toUpperCase() + "'";
            }
        }
        //area add to sql
        if(industry.getArea() > 0){
            if(sql.endsWith("WHERE")){
                sql += " area < " + industry.getArea();
            }else{
                sql += " and area < " + industry.getArea();
            }
        }
        //home add to sql
        if(address.getHome() > 0){
            if(sql.endsWith("WHERE")){
                sql += " home = " + address.getHome();
            }else{
                sql += " and home = " + address.getHome();
            }
        }
        // Other params add to sql
        Map<String, String> states = new HashMap<>();
        states.put("aim", industry.getAim());
        states.put("city", address.getCity());
        states.put("street", address.getStreet());
        states.put("building", address.getBuilding());

        for(Map.Entry<String, String> item : states.entrySet()){
            sql = addCondition(sql, item.getKey(), item.getValue());
        }
        // Удаляем where(5) если нет дальнейших условий
        if(sql.endsWith("WHERE")){
            sql = sql.substring(0, sql.length() - 5);
        }

        PreparedStatement prepStat = connection.prepareStatement(sql);
        ResultSet rs = prepStat.executeQuery();

        ArrayList<IndAddPer> list = new ArrayList<>();
        while (rs.next()){
            list.add(new IndAddPer(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                    rs.getString(4), rs.getString(5), rs.getInt(6),
                    rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getLong(10), rs.getString(11), rs.getInt(12),
                    rs.getString(13)));
        }
        connection.close();
        return list;
    }

    @Override
    public String addCondition(String sql, String key, String value){
        logger.info("run industryRepository addCondition()");
        // если не пустая строка
        if(!value.equals("")){
            if(sql.endsWith("WHERE")){
                sql += " UPPER("+key+") = '"+value.toUpperCase()+"'";
            }else{
                sql += " and UPPER("+key+") = '"+value.toUpperCase()+"'";
            }
        }
        return sql;
    }

    @Override
    public int findIdByName(Industry industry) throws SQLException{
        logger.info("run industryRepository findIdByName()");
        // Поиск id человека по имени
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        // Возвращаем строку в БД по входящим параметрам
        PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM industry\n" +
                "WHERE addressId = ? and personId = ? and target = ?\n" +
                "and area = ? and aim = ?");
        prepStat.setInt(1, industry.getAddressId());
        prepStat.setInt(2, industry.getPersonId());
        prepStat.setString(3, industry.getTarget());
        prepStat.setInt(4, industry.getArea());
        prepStat.setString(5, industry.getAim());
        ResultSet rs = prepStat.executeQuery();

        // если не найдена площадка возвращает -1
        if(!rs.next()){
            connection.close();
            return -1;
        }

        connection.close();
        return rs.getInt(1);
    }

    @Override
    public Industry add(Industry industry) throws SQLException{
        logger.info("run industryRepository add()");
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        // если человек уже есть в БД, то он и возвращается
        // В БД id площадки > 0
        int id = findIdByName(industry);
        if(id  > 0){
            connection.close();
            return findById(id);
        }

        // Добавление нового человека в БД
        PreparedStatement prepStat = connection.prepareStatement("INSERT INTO industry (addressId, personId, target, area, aim)\n" +
                "VALUES (?, ?, ?, ?, ?)");
        prepStat.setInt(1, industry.getAddressId());
        prepStat.setInt(2, industry.getPersonId());
        prepStat.setString(3, industry.getTarget());
        prepStat.setInt(4, industry.getArea());
        prepStat.setString(5, industry.getAim());
        prepStat.execute();

        // Получение id человека
        int newId = findIdByName(industry);

        // Получение человека по id
        connection.close();
        return findById(newId);
    }

    @Override
    public Industry deleteById(int id) throws SQLException{
        logger.info("run industryRepository deleteById()");
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        // запоминание площадки в переменную
        Industry industry = findById(id);

        // удаление из БД
        PreparedStatement prepStat = connection.prepareStatement("DELETE FROM industry WHERE id = ?");
        prepStat.setInt(1, id);
        prepStat.execute();

        connection.close();
        return industry;
    }

    @Override
    public IndAddPer toIAP(int id) throws SQLException{
        logger.info("run industryRepository toIAP()");
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        // Поиск человека по id
        PreparedStatement prepStat = connection.prepareStatement("SELECT foo.id as id, addressId, personId, city," +
                " street, home, building, firstName, lastName,\n" +
                "    phoneNumber, target, area, aim FROM\n" +
                "(SELECT industry.id as id, addressId, personId, target,area, aim, city, street, home, building FROM \n" +
                " \tindustry INNER JOIN address ON industry.addressId = address.id) AS \n" +
                "foo INNER JOIN person ON foo.personId = person.id\n" +
                "WHERE foo.id = ?");
        prepStat.setInt(1, id);
        ResultSet rs = prepStat.executeQuery();
        rs.next();

        connection.close();
        return new IndAddPer(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                rs.getString(4), rs.getString(5), rs.getInt(6),
                rs.getString(7), rs.getString(8), rs.getString(9),
                rs.getLong(10), rs.getString(11), rs.getInt(12),
                rs.getString(13));
    }
}