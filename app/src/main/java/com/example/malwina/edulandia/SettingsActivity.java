package com.example.malwina.edulandia;

import android.app.Application;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private ImageView settingsMusicIcon;
    private ImageView settingsSoundsIcon;
    private ImageView backButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("back button: ", "clicked");

                onBackPressed();
            }
        });

        settingsMusicIcon = findViewById(R.id.settingsMusicIcon);
        settingsMusicIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("settings music icon", "clicked");

                ((EdulandiaApplication)getApplication()).toggleMusic();

                ((EdulandiaApplication)getApplication()).musicOff();

            }
        });

        settingsSoundsIcon = findViewById(R.id.settingsSoundsIcon);
        settingsSoundsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("settings sounds icon", "clicked");
            }
        });
    }
    }
