package com.example.malwina.edulandia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SubMenuActivity extends AppCompatActivity {

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

            }
        });
    }
}
