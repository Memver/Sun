package com.sun.hope.models;

public class Industry {
    private int id;
    private int addressId;
    private int personId;
    private String target;
    private int area;
    private String aim;

    public Industry() {
    }

    public Industry(int id, int addressId, int personId, String target, int area, String aim) {
        this.id = id;
        this.addressId = addressId;
        this.personId = personId;
        this.target = target;
        this.area = area;
        this.aim = aim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }
}
