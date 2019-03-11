package com.example.malwina.edulandia;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView headTextView;
    private ImageView playButton;
    private ImageView settingsButton;
    Animation pulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (!getIntent().getBooleanExtra("restart", false)) {
//            ((EdulandiaApplication) getApplication()).startMusic();
//
//        }
        if (!((EdulandiaApplication)getApplication()).isMusicOff()) {

            ((EdulandiaApplication)getApplication()).startMusic();
        }

        playButton = findViewById(R.id.playButton);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse_infinite);
        playButton.startAnimation(pulse);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("play button: ", "clicked");

                Intent myIntent = new Intent(MainActivity.this, GameMenuActivity.class);
                startActivity(myIntent);
            }
        });
        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("settings button: ", "clicked");
                Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(myIntent);
            }
        });
        Log.d("is on create shows", "Yes");
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            ((EdulandiaApplication)getApplication()).pauseMusic();

        }
    }
}
