package com.example.solution_25.model;

public enum Category {
    HOUSEHOLD("Domowe"), GROCERY("Zakupy"), SCHOOL("Szkoła"),
    ADMINISTRATION("Sprawy urzędowe"), OTHER("Inne");

    private String polishName;

    Category(String polishName) {
        this.polishName = polishName;
    }

    public String getPolishName() {
        return polishName;
    }
}