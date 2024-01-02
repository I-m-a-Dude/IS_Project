package com.toto.proiect_is.func;

public class Income {
    private String month;
    private String amount;

    // Required default constructor for Firebase
    public Income() {
    }

    public Income( String month, String amount) {

        this.month = month;
        this.amount = amount;
    }

    // Getter and setter methods...



    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
