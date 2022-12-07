package com.tlz.quizzarc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Page extends AppCompatActivity {

    EditText email;
    EditText password;
    Button signIn;
    SignInButton signInGoogle;
    TextView signUp;
    TextView forgotPassword;
    ProgressBar progressBarSignIn;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email = findViewById(R.id.etLoginEmail);
        password = findViewById(R.id.etLoginPassword);
        signIn = findViewById(R.id.buttonLoginSignIn);
        signInGoogle = findViewById(R.id.signInButton);
        signUp = findViewById(R.id.tvLoginSignUp);
        forgotPassword = findViewById(R.id.tvLoginForgetPassword);
        progressBarSignIn = findViewById(R.id.progressBarSignIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                signInFirebase(userEmail, userPassword);
            }
        });

        signInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login_Page.this, Sign_Up_Page.class);
                startActivity(intent);

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login_Page.this, Forgot_Password.class);
                startActivity(intent);

            }
        });
    }

    public void signInFirebase(String userEmail, String userPassword) {
        progressBarSignIn.setVisibility(View.VISIBLE);
        signIn.setClickable(false);

        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(Login_Page.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            progressBarSignIn.setVisibility(View.INVISIBLE);
                            Toast.makeText(Login_Page.this, "You have successfully signed in", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login_Page.this, "There was an issue with your sign in attempt", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            Intent intent = new Intent(Login_Page.this, MainActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(Login_Page.this, "Resuming Session", Toast.LENGTH_SHORT).show();
        }
    }
}