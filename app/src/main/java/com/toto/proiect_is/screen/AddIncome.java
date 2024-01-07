package com.toto.proiect_is.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.toto.proiect_is.R;
import com.toto.proiect_is.func.Income;

public class AddIncome extends AppCompatActivity {

    private EditText editTextAmount;
    private Button addButton;

    private TextView textview2;

    private FloatingActionButton floatingActionButton;

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("incomes");

        // Initialize UI elements
        editTextAmount = findViewById(R.id.editTextAmount);
        addButton = findViewById(R.id.Addbutton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIncomeToFirebase();
            }
        });

        floatingActionButton = findViewById(R.id.back);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void addIncomeToFirebase() {
        String amount = editTextAmount.getText().toString().trim();

        if (!amount.isEmpty()) {
            // Create a unique key for the new income
            String incomeId = databaseReference.push().getKey();

            // Create a new Income object
            Income income = new Income(amount);

            // Add the income to the database
            databaseReference.child(incomeId).setValue(income).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(AddIncome.this, "Income added successfully", Toast.LENGTH_SHORT).show();
                    // Optionally, you can add additional actions after successful deletion
                    // Clear the input fields
                    editTextAmount.setText("");


                } else {
                    Toast.makeText(AddIncome.this, "Failed to add income", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Handle the case where the expense ID is empty
            Toast.makeText(AddIncome.this, "Please enter an Income", Toast.LENGTH_SHORT).show();
        }

        }

    }
