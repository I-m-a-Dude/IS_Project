package com.toto.proiect_is.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.toto.proiect_is.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.toto.proiect_is.func.ExpenseDeleteObserver;

import java.util.ArrayList;
import java.util.List;

public class DelActivity extends AppCompatActivity implements ExpenseDeleteObserver {

    // Firebase Database
    private DatabaseReference databaseReference;

    private List<ExpenseDeleteObserver> observers = new ArrayList<>();

    // Views
    private TextView addTxt;
    private EditText expenseIdEditText;
    private Button deleteExpenseButton;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_expense);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Expenses");

        // Initialize Views
        addTxt = findViewById(R.id.Add_Txt);
        expenseIdEditText = findViewById(R.id.text_ID);
        deleteExpenseButton = findViewById(R.id.button);
        floatingActionButton = findViewById(R.id.floatingActionButton2);

        // Set onClickListener for Delete Expense Button
        deleteExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteExpenseFromDatabase();
                clearscreen();
            }
        });

        // Set onClickListener for FloatingActionButton
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Return to the home screen
                onBackPressed();
            }
        });
    }

    public void onExpenseDeletedSuccessfully() {
        for (ExpenseDeleteObserver observer : observers) {
            observer.onExpenseDeletedSuccessfully();
        }
    }

    public void onExpenseDeleteFailed() {
        for (ExpenseDeleteObserver observer : observers) {
            observer.onExpenseDeleteFailed();
        }
    }

    // Method to delete expense from Firebase Realtime Database
    private void deleteExpenseFromDatabase() {

        String expenseId = expenseIdEditText.getText().toString();

        // Check if expenseId is not empty
        if (!expenseId.isEmpty()) {
            // Remove the expense from the database using the given ID
            databaseReference.child(expenseId).removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(DelActivity.this, "Expense deleted successfully", Toast.LENGTH_SHORT).show();
                    // Optionally, you can add additional actions after successful deletion
                } else {
                    Toast.makeText(DelActivity.this, "Failed to delete expense", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Handle the case where the expense ID is empty
            Toast.makeText(DelActivity.this, "Please enter an expense ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearscreen() {
        expenseIdEditText.setText("");
    }
}
