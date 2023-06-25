package com.sun.hope.repositories;

import com.sun.hope.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository{

    public static final String url = "jdbc:postgresql://localhost:5433/postgres";
    public static final String user = "postgres";
    public static final String password = "1";

    @Override
    public List<Person> findAll() throws SQLException {
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM person");
        ResultSet rs = prepStat.executeQuery();
        ArrayList<Person> list = new ArrayList<>();
        while (rs.next()){
            list.add(new Person(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getLong(4)));
        }
        connection.close();
        return list;
    }

    @Override
    public boolean hasInRep(Person person) throws SQLException
    {
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        // Проверка на то, что человека еще нет в БД
        PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM person WHERE\n" +
                "firstName = ?\n" +
                "and lastName = ?\n" +
                "and phoneNumber = ?");
        prepStat.setString(1, person.getFirstName());
        prepStat.setString(2, person.getLastName());
        prepStat.setLong(3, person.getPhoneNumber());
        ResultSet rs = prepStat.executeQuery();

        // Если нет следующего элемента, то человека нет в БД
        if(!rs.next()){
            connection.close();
            return false;
        }

        // иначе есть в БД
        connection.close();
        return true;
    }

    @Override
    public Person add(Person person) throws SQLException{
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        // если человек уже есть в БД, то он и возвращается
        if(hasInRep(person)){
            int id = findIdByName(person);
            return findById(id);
        }

        // Добавление нового человека в БД
        PreparedStatement prepStat = connection.prepareStatement("INSERT INTO person (firstName, lastName, phoneNumber)\n" +
                "VALUES (?, ?, ?)");
        prepStat.setString(1, person.getFirstName());
        prepStat.setString(2, person.getLastName());
        prepStat.setLong(3, person.getPhoneNumber());

        prepStat.execute();

        // Получение id человека
        int newId = findIdByName(person);

        // Получение человека по id
        connection.close();
        return findById(newId);
    }


    @Override
    public int findIdByName( Person person) throws SQLException{
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        // Поиск id человека по имени
        PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM person WHERE\n" +
                "firstName = ?\n" +
                "and lastName = ?\n" +
                "and phoneNumber = ?");
        prepStat.setString(1, person.getFirstName());
        prepStat.setString(2, person.getLastName());
        prepStat.setLong(3, person.getPhoneNumber());
        ResultSet rs = prepStat.executeQuery();
        rs.next();

        connection.close();
        return rs.getInt(1);
    }

    @Override
    public Person findById(int id) throws SQLException{
        // Подключение к БД
        Connection connection = DriverManager.getConnection(url, user, password);

        // Поиск человека по id
        PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM person WHERE id = ?");
        prepStat.setInt(1, id);
        ResultSet rs = prepStat.executeQuery();
        if(!rs.next()){
            connection.close();
            return null;
        }

        connection.close();
        return new Person(rs.getInt(1), rs.getString(2),
                rs.getString(3), rs.getLong(4));
    }
}
