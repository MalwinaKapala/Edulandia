package com.example.malwina.edulandia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameMenuActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView menuTextView;
    private ImageView gameMenuPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_menu_activity);

        gameMenuPicture = findViewById(R.id.GameMenuPicture);
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "FOO", Toast.LENGTH_LONG).show();
    }
}
