package com.toto.proiect_is.func;

public class Income {
    private String income;

    // Required default constructor for Firebase
    public Income() {
    }

    public Income( String income) {

        this.income = income;
    }

    // Getter and setter methods...


    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }
}
