package com.planetofheroes.amarioforester.poh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class Hero_Duncan extends AppCompatActivity {
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_duncan);
        backButton = (Button) findViewById(R.id.backBtn4);
    }

    public void backBtnClick(View v){
        Intent intent = new Intent(Hero_Duncan.this, HeroesActivity.class);
        startActivity(intent);
    }
}
