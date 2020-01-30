package com.example.project2activity.pojo;

public class FamilyNames {

    private String surname;
    private String name;
    private String middlename;

    public FamilyNames(String surname, String name, String middlename) {
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }
}
