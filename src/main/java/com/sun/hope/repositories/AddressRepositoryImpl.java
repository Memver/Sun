package com.sun.hope.repositories;

import com.sun.hope.models.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.sun.hope.repositories.PersonRepositoryImpl.*;


public class AddressRepositoryImpl implements AddressRepository{

    @Override
    public Address add(Address address) throws SQLException{
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        // если адрес уже есть в БД, то он и возвращается
        // В БД id адреса > 0
        int id = findIdByName(address);
        if(id  > 0){
            connection.close();
            return findById(id);
        }

        // Добавление нового человека в БД
        PreparedStatement prepStat = connection.prepareStatement("INSERT INTO address (city, street, home, building)\n" +
                "VALUES (?, ?, ?, ?)");
        prepStat.setString(1, address.getCity());
        prepStat.setString(2, address.getStreet());
        prepStat.setInt(3, address.getHome());
        prepStat.setString(4, address.getBuilding());
        prepStat.execute();

        // Получение id человека
        int newId = findIdByName(address);

        // Получение человека по id
        connection.close();
        return findById(newId);
    }


    @Override
    public int findIdByName(Address address) throws SQLException{
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        // Возвращаем строку в БД по входящим параметрам
        PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM address WHERE \n" +
                "city = ? \n" +
                "and street = ?\n" +
                "and home = ?\n" +
                "and building = ?");
        prepStat.setString(1, address.getCity());
        prepStat.setString(2, address.getStreet());
        prepStat.setInt(3, address.getHome());
        prepStat.setString(4, address.getBuilding());
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
    public Address findById(int id) throws SQLException{
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        // Поиск человека по id
        PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM address WHERE id = ?");
        prepStat.setInt(1, id);
        ResultSet rs = prepStat.executeQuery();
        if(!rs.next()){
            connection.close();
            return null;
        }

        connection.close();
        return new Address(rs.getInt(1), rs.getString(2), rs.getString(3)
                ,rs.getInt(4), rs.getString(5));
    }

    @Override
    public List<Address> findAll() throws SQLException {
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM address");
        ResultSet rs = prepStat.executeQuery();
        ArrayList<Address> list = new ArrayList<>();
        while (rs.next()){
            list.add(new Address(rs.getInt(1), rs.getString(2), rs.getString(3)
                    ,rs.getInt(4), rs.getString(5)));
        }
        connection.close();
        return list;
    }
}