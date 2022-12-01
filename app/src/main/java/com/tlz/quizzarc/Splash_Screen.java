package com.tlz.quizzarc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity {


    public ImageView image;
    public TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        image = findViewById(R.id.ivSplash);
        title = findViewById(R.id.tvSplash);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.spalsh_screen_anim);
        title. startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this, Login_Page.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}