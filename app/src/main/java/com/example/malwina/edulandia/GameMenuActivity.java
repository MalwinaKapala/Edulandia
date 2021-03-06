package com.example.malwina.edulandia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameMenuActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView menuTextView;
    private ImageView gameMenuPicture;
    Animation pulse;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_menu_activity);

        gameMenuPicture = findViewById(R.id.GameMenuPicture);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        gameMenuPicture.startAnimation(pulse);
        gameMenuPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("game menu picture: ", "clicked");

                Intent myIntent = new Intent(GameMenuActivity.this, SubMenuActivity.class);
                startActivity(myIntent);
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("back button", "clicked");

                onBackPressed();
            }
        });
    }
}
