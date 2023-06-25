package com.sun.hope.models;

public class Address {
    private int id;
    private String city;
    private String street;
    private int home;
    private String building;

    public Address() {
    }

    public Address(String city, String street, int home, String building) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.building = building;
    }

    public Address(int id, String city, String street, int home, String building) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.home = home;
        this.building = building;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
