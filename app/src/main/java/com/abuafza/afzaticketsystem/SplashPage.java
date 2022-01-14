package com.abuafza.afzaticketsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashPage extends AppCompatActivity {
    TextView myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        Handler handler = new Handler();
        Intent intentRedirect = new Intent(getApplicationContext(), MainActivity.class);
        myText = findViewById(R.id.splash);
        //create animation function
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.myanimation);
        myText.startAnimation(animation);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intentRedirect);
            }
        },3000);
    }
}