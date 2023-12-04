package com.toto.proiect_is.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.toto.proiect_is.R;
import com.toto.proiect_is.data.RegisterData;


public class RegisterActivity extends AppCompatActivity {

    private RegisterData registerData;

    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize RegisterData
        registerData = new RegisterData();

        // Initialize UI elements
        emailEditText = findViewById(R.id.Email);
        passwordEditText = findViewById(R.id.Password);

        Button registerButton = findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the email and password from the EditText fields
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate email and password (add your own validation logic here)

                // Call the registerUser method from RegisterData
                registerData.registerUser(RegisterActivity.this, email, password);
            }
        });

        Button signInButton = findViewById(R.id.SignInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the LoginActivity when the "Sign In" button is clicked
                Intent intent = new Intent(RegisterActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
    }
}
