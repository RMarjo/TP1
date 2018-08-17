package com.example.mrenaud.mymarvelapp.model;

public class Creator {
    private String name;
    private String role;

    public Creator() {
    }

    public Creator(String name, String role) {
        this.name = name;
        this.role = role;
    }

    // SET
    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //GET
    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
