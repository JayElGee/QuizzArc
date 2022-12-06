package com.tlz.quizzarc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView signOut;

        FirebaseAuth auth = FirebaseAuth.getInstance();

        signOut = findViewById(R.id.tvSignOut);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.signOut();
                Intent intent = new Intent(MainActivity.this, Login_Page.class);
                startActivity(intent);
                finish();

            }
        });



    }
}