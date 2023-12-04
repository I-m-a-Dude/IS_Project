package com.toto.proiect_is.screen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.toto.proiect_is.R;
import com.toto.proiect_is.screen.HomeActivity;

public class LogInActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox rememberMeCheckBox;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.Username);
        passwordEditText = findViewById(R.id.Password_Repeat);
        rememberMeCheckBox = findViewById(R.id.RememberMe);

        preferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);

        // Populate fields if "Remember Me" is checked
        if (preferences.getBoolean("rememberMe", false)) {
            usernameEditText.setText(preferences.getString("username", ""));
            passwordEditText.setText(preferences.getString("password", ""));
            rememberMeCheckBox.setChecked(true);
        }

        Button loginButton = findViewById(R.id.Log_In);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check login credentials (implement your login logic here)
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // Assuming successful login (you need to implement your own logic here)
                if (isLoginSuccessful(enteredUsername, enteredPassword)) {
                    // Save credentials if "Remember Me" is checked
                    if (rememberMeCheckBox.isChecked()) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("username", enteredUsername);
                        editor.putString("password", enteredPassword);
                        editor.putBoolean("rememberMe", true);
                        editor.apply();
                    } else {
                        // Clear saved credentials if "Remember Me" is unchecked
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("username");
                        editor.remove("password");
                        editor.remove("rememberMe");
                        editor.apply();
                    }

                    // Redirect to home page
                    Intent intent = new Intent(LogInActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Close the login activity to prevent going back
                } else {
                    // Handle unsuccessful login
                }
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

    // Your own logic for checking login credentials
    private boolean isLoginSuccessful(String username, String password) {
        // Implement your login logic here
        // Compare username and password with your authentication mechanism
        // Return true if login is successful, false otherwise
        return true; // Replace with your own logic
    }
}
