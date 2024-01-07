package com.toto.proiect_is.screen;

import static java.util.Date.parse;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.Comparator;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toto.proiect_is.R;
import com.toto.proiect_is.func.Expense;

import java.util.ArrayList;
import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewExpenses extends AppCompatActivity {

    // Firebase Database
    private DatabaseReference expensesReference;

    // Views
    private ListView expensesListView;
    private List<Expense> expenseList;

    private Button low_sum, big_sum, early_date, latest_date, category;

    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_expense);

        // Initialize Firebase Database reference
        expensesReference = FirebaseDatabase.getInstance().getReference().child("Expenses");

        // Initialize Views
        expensesListView = findViewById(R.id.lista);

        floatingActionButton = findViewById(R.id.back);

        // Initialize Expense List
        expenseList = new ArrayList<>();

        // Set up a ValueEventListener to fetch expenses from Firebase
        expensesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                expenseList.clear(); // Clear existing data to avoid duplicates

                // Iterate through each expense in the dataSnapshot
                for (DataSnapshot expenseSnapshot : dataSnapshot.getChildren()) {
                    Expense expense = expenseSnapshot.getValue(Expense.class);
                    if (expense != null) {
                        expenseList.add(expense);
                    }
                }

                // Update the ListView with the new data
                updateListView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors if any
            }
        });

        low_sum = findViewById(R.id.low_sum);
        big_sum = findViewById(R.id.big_sum);
        early_date = findViewById(R.id.early_date);
        latest_date = findViewById(R.id.latest_date);
        category = findViewById(R.id.category);

        low_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortExpensesByLow();
            }
        });

        big_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortExpensesByHigh();
            }
        });

        early_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortExpensesByEarliestDate();
            }
        });

        latest_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortExpensesByLatestDate();
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortExpensesByCategory();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void updateListView() {
        // Create an ArrayAdapter to display expenses in the ListView
        ArrayAdapter<Expense> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, expenseList);

        // Set the adapter to the ListView
        expensesListView.setAdapter(adapter);
    }



    private void sortExpensesByLow() {
        Collections.sort(expenseList, new Comparator<Expense>() {
            @Override
            public int compare(Expense expense1, Expense expense2) {
                return Double.compare(expense1.getSum(), expense2.getSum());
            }
        });

        updateListView();
    }

    private void sortExpensesByHigh() {
        Collections.sort(expenseList, new Comparator<Expense>() {
            @Override
            public int compare(Expense expense1, Expense expense2) {
                return Double.compare(expense2.getSum(), expense1.getSum());
            }
        });

        updateListView();
    }

    private void sortExpensesByEarliestDate() {
        Collections.sort(expenseList, new Comparator<Expense>() {
            @Override
            public int compare(Expense expense1, Expense expense2) {
                return compareDates(expense1.getDate(), expense2.getDate());
            }
        });

        updateListView();
    }

    private void sortExpensesByLatestDate() {
        Collections.sort(expenseList, new Comparator<Expense>() {
            @Override
            public int compare(Expense expense1, Expense expense2) {
                return compareDates(expense2.getDate(), expense1.getDate());
            }
        });

        updateListView();
    }

    private int compareDates(String date1, String date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date parsedDate1 = dateFormat.parse(date1);
            Date parsedDate2 = dateFormat.parse(date2);

            // Compare the parsed dates
            return parsedDate1.compareTo(parsedDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0; // Default to no comparison if parsing fails
    }



    private void sortExpensesByCategory() {
        Collections.sort(expenseList, new Comparator<Expense>() {
            @Override
            public int compare(Expense expense1, Expense expense2) {
                return expense1.getCategory().compareToIgnoreCase(expense2.getCategory());
            }
        });

        updateListView();
    }


}
