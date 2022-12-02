package com.tlz.quizzarc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_Page extends AppCompatActivity {

    EditText email;
    EditText password;
    Button signIn;
    Button signInGoogle;
    TextView signUp;
    TextView forgotPassword;

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

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

            }
        });
    }
}