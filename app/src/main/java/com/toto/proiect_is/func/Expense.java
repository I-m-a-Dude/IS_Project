package com.toto.proiect_is.func;


import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;



public class Expense {
    private String category;
    private String date;
    private double sum;
    private String describe;

    // Default constructor (required for Firebase)
    public Expense() {
        // Default constructor required for calls to DataSnapshot.getValue(Expense.class)
    }

    // Parameterized constructor
    public Expense(String category, String date, double sum, String describe) {
        this.category = category;
        this.date = date;
        this.sum = sum;
        this.describe = describe;
    }

    // Getters and setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Category: " + category + "\nDate: " + date + "\nSum: " + sum + "\nDescription: " + describe;
    }
}
