package com.example.malwina.edulandia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SubMenuActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView subMenuTextView;
    private ImageView firstGameIconHorse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_menu_activity);

        subMenuTextView = findViewById(R.id.subMenuTextView);
        firstGameIconHorse = findViewById(R.id.firstGameIconHorse);

        firstGameIconHorse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("horse picture: ", "clicked");

                Intent myIntent = new Intent(SubMenuActivity.this, FirstGameActivity.class);
                startActivity(myIntent);

            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("back button", "clicked");

                Intent myIntent = new Intent (SubMenuActivity.this, GameMenuActivity.class);
                startActivity(myIntent);
            }
        });
    }

}
