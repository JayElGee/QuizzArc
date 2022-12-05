package com.tlz.quizzarc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_Up_Page extends AppCompatActivity {

    EditText email;
    EditText password;
    Button signUp;
    ProgressBar progressBar;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        email = findViewById(R.id.etSignUpEmail);
        password = findViewById(R.id.etSignUpPassword);
        signUp = findViewById(R.id.buttonSignUp);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signUp.setClickable(false);

                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                signUpFirebase(userEmail, userPassword);
            }
        });
    }

    public void signUpFirebase(String userEmail, String userPassword) {
        progressBar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Sign_Up_Page.this, "Your account has been created."
                                    , Toast.LENGTH_LONG).show();
                            finish();
                            progressBar.setVisibility(View.INVISIBLE);
                        } else {
                            Toast.makeText(Sign_Up_Page.this, "Something went wrong"
                                    , Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}