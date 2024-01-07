package com.toto.proiect_is.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.toto.proiect_is.R;
import com.toto.proiect_is.func.Expense;
import com.toto.proiect_is.func.ExpenseObserver;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements ExpenseObserver {

    // Firebase Database
    private FirebaseDatabase db;
    private DatabaseReference databaseReference;

    private List<ExpenseObserver> observers = new ArrayList<>();

    // Views
    private EditText dataEditText, sumaEditText, categorieEditText, descriereEditText;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        // Initialize Firebase Database reference
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Expenses");

        // Initialize Views
        categorieEditText = findViewById(R.id.Categorie);
        dataEditText = findViewById(R.id.Data);
        sumaEditText = findViewById(R.id.Suma);
        descriereEditText = findViewById(R.id.Descriere);
        Button addButton = findViewById(R.id.Addbutton);

        floatingActionButton = findViewById(R.id.back);

        // Set onClickListener for the "Add" button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("AddActivity", "Add button clicked");
                addExpenseToDatabase();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    public void onExpenseAddedSuccessfully() {
        for (ExpenseObserver observer : observers) {
            observer.onExpenseAddedSuccessfully();
        }
    }

    public void onExpenseAddFailed() {
        for (ExpenseObserver observer : observers) {
            observer.onExpenseAddFailed();
        }
    }



    // Method to add expense to Firebase Realtime Database
    private void addExpenseToDatabase() {
        String category = categorieEditText.getText().toString();
        String date = dataEditText.getText().toString();
        String sum = sumaEditText.getText().toString();
        String description = descriereEditText.getText().toString();

        if (date.isEmpty() || sum.isEmpty() || category.isEmpty() || description.isEmpty()) {
            Toast.makeText(AddActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        Expense expense = new Expense(category, date, Double.parseDouble(sum), description);

        // Add the expense to the database using the generated key
        databaseReference.push().setValue(expense).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddActivity.this, "Expense added successfully", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(AddActivity.this, "Failed to add expense", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to clear input fields
    private void clearFields() {
        dataEditText.setText("");
        sumaEditText.setText("");
        categorieEditText.setText("");
        descriereEditText.setText("");
    }
}
