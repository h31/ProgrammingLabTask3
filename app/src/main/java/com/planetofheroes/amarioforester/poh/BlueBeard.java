package com.planetofheroes.amarioforester.poh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BlueBeard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_bluebeard);

        View overlay = findViewById(R.id.blue);

        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("BlueBeard");
        }
    }
}
