package com.tlz.quizzarc;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.tlz.quizzarc.databinding.ActivityForgotPasswordBinding;

public class Forgot_Password extends AppCompatActivity {

    EditText email;
    Button button;
    ProgressBar progressBar;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.etPasswordEmail);
        button = findViewById(R.id.buttonPasswordSubmit);
        progressBar = findViewById(R.id.progressBarForgotPassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                resetPassword(userEmail);
            }
        });
    }

    public void resetPassword(String userEmail) {
        progressBar.setVisibility(View.VISIBLE);

        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(Forgot_Password.this, "A password reset link was sent to your email address.", Toast.LENGTH_LONG).show();
                    button.setClickable(false);
                    progressBar.setVisibility(View.INVISIBLE);

                    finish();
                } else {
                    Toast.makeText(Forgot_Password.this, "There was an issue. Please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}