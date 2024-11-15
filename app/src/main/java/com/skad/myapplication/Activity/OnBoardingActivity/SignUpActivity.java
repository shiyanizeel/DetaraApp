package com.skad.myapplication.Activity.OnBoardingActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.skad.myapplication.Activity.HomeActivity.MainActivity;
import com.skad.myapplication.Models.FireBaseModel.SignUpUsers;
import com.skad.myapplication.R;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    TextView signupBtn,signUpSignIntextBtn;
    EditText firstnameSignUp, lastnameSignup, emailSignup, creatpassSignup;
    FirebaseAuth mAuth; // FirebaseAuth object
    FirebaseFirestore db;
    ImageView googleBtnSignUp;
    GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Views
        googleBtnSignUp = findViewById(R.id.googleBtnSignUP);
        progressBar = findViewById(R.id.progressBarSignUp);
        firstnameSignUp = findViewById(R.id.firstNameEditSignUp);
        lastnameSignup = findViewById(R.id.lastNameEditSignUp);
        emailSignup = findViewById(R.id.emailEditSignUp);
        creatpassSignup = findViewById(R.id.passEditSignUp);
        signupBtn = findViewById(R.id.signupBtn);
        signUpSignIntextBtn = findViewById(R.id.signUpSignIntextBtn);
        // Initialize Firebase and Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Google Sign-In options
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("481245246042-b9vgq41nce6t702odqak5klvd8a6dieh.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(SignUpActivity.this, googleSignInOptions);

        // In your Google sign-in button click listener
        googleBtnSignUp.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);  // Show the progress bar when sign-in starts
            googleBtnSignUp.setEnabled(false);  // Disable button to prevent multiple clicks

            Intent intent = googleSignInClient.getSignInIntent();
            startActivityForResult(intent, 100);
        });


        // Check if the user is already signed in
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        // Sign-Up Button Listener
        signUpSignIntextBtn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);  // Show progress bar
            signUpSignIntextBtn.setEnabled(false);  // Disable sign-up button to prevent multiple clicks
            startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
        });
        // Sign-Up Button Listener
        signupBtn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);  // Show progress bar
            signupBtn.setEnabled(false);  // Disable sign-up button to prevent multiple clicks
            createAccount();  // Trigger account creation process
        });
    }

    // Method to create an account
    private void createAccount() {
        String firstName = firstnameSignUp.getText().toString().trim();
        String lastName = lastnameSignup.getText().toString().trim();
        String email = emailSignup.getText().toString().trim();
        String password = creatpassSignup.getText().toString().trim();

        // Validation checks
        if (TextUtils.isEmpty(firstName)) {
            firstnameSignUp.setError("First Name is required");
            hideProgressBar();
            return;
        }
        if (!firstName.matches("^[A-Za-z]+$")) {
            firstnameSignUp.setError("First Name must contain only alphabetic characters");
            hideProgressBar();
            return;
        }
        if (TextUtils.isEmpty(lastName)) {
            lastnameSignup.setError("Last Name is required");
            hideProgressBar();
            return;
        }
        if (!lastName.matches("^[A-Za-z]+$")) {
            lastnameSignup.setError("Last Name must contain only alphabetic characters");
            hideProgressBar();
            return;
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.endsWith(".com")) {
            emailSignup.setError("Valid Email ending with .com is required");
            hideProgressBar();
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            creatpassSignup.setError("Password should be at least 6 characters");
            hideProgressBar();
            return;
        }

        // Check Firestore for existing user with the same first and last name
        Query userQuery = db.collection("users")
                .whereEqualTo("firstName", firstName)
                .whereEqualTo("lastName", lastName);

        userQuery.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (!task.getResult().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "User with this name already exists. Please use a different name.", Toast.LENGTH_SHORT).show();
                    hideProgressBar();
                } else {
                    registerWithFirebaseAuth(firstName, lastName, email, password);
                }
            } else {
                Toast.makeText(SignUpActivity.this, "Error checking existing users: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                hideProgressBar();
            }
        });
    }

    // Register user with Firebase Authentication
    private void registerWithFirebaseAuth(String firstName, String lastName, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        saveUserData(firstName, lastName);
                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            emailSignup.setError("This email is already registered. Please use a different email or log in.");
                        } else {
                            Toast.makeText(SignUpActivity.this, "Authentication Failed: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        hideProgressBar();
                    }
                });
    }

    // Save user data to Firestore
    private void saveUserData(String firstName, String lastName) {
        String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        SignUpUsers user = new SignUpUsers(firstName, lastName);
        db.collection("users").document(userId)
                .set(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        intent.putExtra("firstName", firstName);
                        intent.putExtra("lastName", lastName);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignUpActivity.this, "Error Saving User: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    hideProgressBar();
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            progressBar.setVisibility(View.VISIBLE);  // Show full-screen loader when sign-in starts
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (signInAccountTask.isSuccessful()) {
                try {
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    if (googleSignInAccount != null) {
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, task -> {
                            hideProgressBar();  // Hide loader once authentication is done
                            if (task.isSuccessful()) {
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                Toast.makeText(this, "Firebase authentication successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Authentication Failed: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                    hideProgressBar();  // Hide loader on exception
                }
            } else {
                hideProgressBar();  // Hide loader if Google sign-in fails
            }
        }
    }


    // Method to hide progress bar and re-enable buttons
    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        googleBtnSignUp.setEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}