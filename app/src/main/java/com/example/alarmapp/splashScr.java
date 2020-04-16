package com.example.alarmapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class splashScr extends AppCompatActivity {
    ImageView splash;


    private static int SPLASH_TIMEOUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splash = (ImageView) findViewById(R.id.splashscr);
        setContentView(R.layout.activity_splash_scr);

        fade();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startSecondActivity();
            }
        },SPLASH_TIMEOUT);
    }
    public void fade(){
        ImageView image = (ImageView)findViewById(R.id.splashscr);
        ImageView image1  = (ImageView)findViewById(R.id.splashclock);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade);
        image.startAnimation(animation1);
        image1.startAnimation(animation1);


    }
//    public void zoom(){
//        ImageView image = (ImageView)findViewById(R.id.clockscr);
//        Animation animation2 =
//                AnimationUtils.loadAnimation(getApplicationContext(),
//                        R.anim.zoom);
//        image.startAnimation(animation2);
//    }
    public void startSecondActivity(){
        Intent homeIntent = new Intent(splashScr.this, MainActivity.class);

//        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(splashScr.this, findViewById(R.id.splashclock), "clockTransition");
        startActivity(homeIntent);
        finish();


    }

}

