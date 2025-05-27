package hw2.BDDataClasses;

import java.math.BigDecimal;

public class Teacher {
    private int id;
    private String name;
    private BigDecimal salary;
    private String surname;

    public Teacher(String name, BigDecimal salary, String surname) {
        this.name = name;
        this.salary = salary;
        this.surname = surname;
    }

    public Teacher(int id, String name, BigDecimal salary, String surname) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
