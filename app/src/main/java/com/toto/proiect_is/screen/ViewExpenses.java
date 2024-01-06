package com.toto.proiect_is.screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toto.proiect_is.R;
import com.toto.proiect_is.func.Expense;

import java.util.ArrayList;
import java.util.List;

public class ViewExpenses extends AppCompatActivity {

    // Firebase Database
    private DatabaseReference expensesReference;

    // Views
    private ListView expensesListView;
    private List<Expense> expenseList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_expense);

        // Initialize Firebase Database reference
        expensesReference = FirebaseDatabase.getInstance().getReference().child("Expenses");

        // Initialize Views
        expensesListView = findViewById(R.id.lista);



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
    }

    private void updateListView() {
        // Create an ArrayAdapter to display expenses in the ListView
        ArrayAdapter<Expense> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, expenseList);

        // Set the adapter to the ListView
        expensesListView.setAdapter(adapter);
    }




}
