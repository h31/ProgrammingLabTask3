package com.planetofheroes.amarioforester.poh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class Hero_Iffir extends AppCompatActivity {
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_iffir);
        backButton = (Button) findViewById(R.id.backBtn5);
    }

    public void backBtnClick(View v){
        Intent intent = new Intent(Hero_Iffir.this, HeroesActivity.class);
        startActivity(intent);
    }
}
