package com.toto.proiect_is.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.toto.proiect_is.R;
import com.toto.proiect_is.screen.HomeActivity;

public class LogInActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox rememberMeCheckBox;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.Username);
        passwordEditText = findViewById(R.id.Password_Repeat);
        rememberMeCheckBox = findViewById(R.id.RememberMe);

        firebaseAuth = FirebaseAuth.getInstance();

        Button loginButton = findViewById(R.id.Log_In);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check login credentials against Firebase Authentication
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // Authenticate using Firebase
                firebaseAuth.signInWithEmailAndPassword(enteredUsername, enteredPassword)
                        .addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Authentication successful
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    // Save credentials or perform other actions as needed
                                    // ...

                                    // Redirect to home page
                                    Intent intent = new Intent(LogInActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish(); // Optional: Close the login activity
                                } else {
                                    // If sign in fails, display a message to the user.
                                    // Handle unsuccessful login
                                    unsuccessfulLogin();

                                    // Enable the login button after a delay (3 seconds)
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            loginButton.setEnabled(true);
                                        }
                                    }, 3000); // 3 seconds delay
                                }
                            }
                        });
            }
        });

        // Other code for your LogInActivity...

        Button registerButton = findViewById(R.id.Register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the RegisterActivity when the button is clicked
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to handle unsuccessful login
    private void unsuccessfulLogin() {
        // Display an error message to the user
        Toast.makeText(LogInActivity.this, "Login unsuccessful. Please check your credentials.", Toast.LENGTH_SHORT).show();

        // Clear the entered password
        passwordEditText.setText("");

        // Optionally, you can add more actions here based on your requirements
        // For example, you might disable the login button, show an error label, etc.
        // Disable the login button
        Button loginButton = findViewById(R.id.Log_In);
        loginButton.setEnabled(false);
    }
}
