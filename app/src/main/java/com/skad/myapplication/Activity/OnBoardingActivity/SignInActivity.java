package com.skad.myapplication.Activity.OnBoardingActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.skad.myapplication.Activity.HomeActivity.MainActivity;
import com.skad.myapplication.R;


public class SignInActivity extends AppCompatActivity {
    TextView signInBtn;
    FirebaseAuth firebaseAuth;
    EditText emailEditSignin, passEditSignin;
    ProgressBar progressBarSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        // Firebase Auth instance
        firebaseAuth = FirebaseAuth.getInstance();

        // Get references to views
        emailEditSignin = findViewById(R.id.emailEditSignIn);
        passEditSignin = findViewById(R.id.passEditSignIn);
        signInBtn = findViewById(R.id.signinBtn);
        progressBarSignIn = findViewById(R.id.progressBarSignIn);

        signInBtn.setOnClickListener(view -> loginUser());
    }

    private void loginUser() {
        String emailSignin, passSignin;
        emailSignin = emailEditSignin.getText().toString();
        passSignin = passEditSignin.getText().toString();

        if (TextUtils.isEmpty(emailSignin)) {
            Toast.makeText(this, "Your Email id is Wrong", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passSignin)) {
            Toast.makeText(this, "Your Password is Wrong", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show ProgressBar during login
        progressBarSignIn.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(emailSignin, passSignin)
                .addOnCompleteListener(task -> {
                    progressBarSignIn.setVisibility(View.GONE); // Hide ProgressBar

                    if (task.isSuccessful()) {
                        Toast.makeText(SignInActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    } else {
                        // Log the error to get more information
                        String error = task.getException() != null ? task.getException().getMessage() : "Unknown error";
                        Toast.makeText(SignInActivity.this, "Login Failed: " + error, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}