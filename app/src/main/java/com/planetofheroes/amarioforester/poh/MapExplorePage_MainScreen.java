package com.planetofheroes.amarioforester.poh;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class MapExplorePage_MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_explore_page);

        ImageView gliphs = (ImageView) findViewById(R.id.gliphs);
        gliphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapExplorePage_MainScreen.this, Explore_Gliphs.class));
            }
        });
        ImageView archon = (ImageView) findViewById(R.id.archon);
        archon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapExplorePage_MainScreen.this, Explore_Archon.class));
            }
        });
        ImageView queen = (ImageView) findViewById(R.id.queen);
        queen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapExplorePage_MainScreen.this, Explore_Queen.class));
            }
        });
        ImageView towers = (ImageView) findViewById(R.id.towers);
        towers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapExplorePage_MainScreen.this, Explore_Towers.class));
            }
        });
        ImageView monsters = (ImageView) findViewById(R.id.monsters);
        monsters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapExplorePage_MainScreen.this, Explore_Monsters.class));
            }
        });
    }
    public void backBtnClick(View v){
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }, 0);
    }

}
