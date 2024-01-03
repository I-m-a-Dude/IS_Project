package com.toto.proiect_is.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.toto.proiect_is.R;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.*;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button expenseButton = findViewById(R.id.expense);
        Button addButton = findViewById(R.id.add);
        Button addIncomeButton = findViewById(R.id.income);
        Button deleteButton = findViewById(R.id.delete);

        TextView textview2 = findViewById(R.id.textView2);

        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle logout logic
                // For simplicity, let's assume you want to go back to the login screen
                Intent intent = new Intent(HomeActivity.this, ViewExpenses.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Add button click
                // You can start a new activity or perform any other action as needed
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        addIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Add button click
                // You can start a new activity or perform any other action as needed
                Intent intent = new Intent(HomeActivity.this, AddIncome.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Delete button click
                // You can start a new activity or perform any other action as needed
                Intent intent = new Intent(HomeActivity.this, DelActivity.class);
                startActivity(intent);
            }
        });


    }
}
