package com.sun.hope.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lot")
public class Lot {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "number")
    private String number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employeeId;

    public Lot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}
