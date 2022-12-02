package com.tlz.quizzarc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class Sign_Up_Page extends AppCompatActivity {

    EditText email;
    EditText password;
    Button signUp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        email = findViewById(R.id.etSignUpEmail);
        password = findViewById(R.id.etSignUpPassword);
        signUp = findViewById(R.id.buttonSignUp);
        progressBar = findViewById(R.id.progressBar);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}