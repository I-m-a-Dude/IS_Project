package com.toto.proiect_is.data;

import android.app.Activity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterData {

    private FirebaseAuth mAuth;

    public RegisterData() {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    public void registerUser(Activity activity, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        // Registration success
                        FirebaseUser user = mAuth.getCurrentUser();
                        // You can perform additional actions here, such as updating UI or saving user data
                        Toast.makeText(activity, "Registration successful.", Toast.LENGTH_SHORT).show();
                    } else {
                        // If registration fails, display a message to the user.
                        Toast.makeText(activity, "Registration failed. " + task.getException(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
