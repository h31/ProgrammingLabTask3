package com.planetofheroes.amarioforester.poh;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HeroesActivity extends MainActivity {

    private ListView listView;
    private HeroAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);

        // List on Heroes page // Masha

        listView = (ListView) findViewById(R.id.heroes_list);
        ArrayList<Hero> heroesList = new ArrayList<>();
        heroesList.add(new Hero(R.drawable.skyyy));
        heroesList.add(new Hero(R.drawable.skyyy));
        heroesList.add(new Hero(R.drawable.skyyy));
        heroesList.add(new Hero(R.drawable.skyyy));
        heroesList.add(new Hero(R.drawable.skyyy));
        heroesList.add(new Hero(R.drawable.skyyy));
        heroesList.add(new Hero(R.drawable.skyyy));
        heroesList.add(new Hero(R.drawable.skyyy));
        heroesList.add(new Hero(R.drawable.skyyy));
        heroesList.add(new Hero(R.drawable.skyyy));
        heroesList.add(new Hero(R.drawable.skyyy));


        mAdapter = new HeroAdapter(this, heroesList);
        listView.setAdapter(mAdapter);

        // Clickable list buttons on heroes // Masha + Dany

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    Intent myintent = new Intent(view.getContext(), Hero_BlueBeard.class);
                    startActivityForResult(myintent, 0);
                }
                if (position == 1) {
                    Intent myintent = new Intent(view.getContext(), Hero_Bubbles.class);
                    startActivityForResult(myintent, 1);
                }
                if (position == 2) {
                    Intent myintent = new Intent(view.getContext(), Hero_Duncan.class);
                    startActivityForResult(myintent, 2);
                }
                if (position == 3) {
                    Intent myintent = new Intent(view.getContext(), Hero_Candy.class);
                    startActivityForResult(myintent, 3);
                }
                if (position == 4) {
                    Intent myintent = new Intent(view.getContext(), Hero_Skylee.class);
                    startActivityForResult(myintent, 4);
                }
                if (position == 5) {
                    Intent myintent = new Intent(view.getContext(), Hero_Timmy.class);
                    startActivityForResult(myintent, 5);
                }
                if (position == 6) {
                    Intent myintent = new Intent(view.getContext(), Hero_Iffir.class);
                    startActivityForResult(myintent, 6);
                }
            }
        });
    }

    @Override                              //masha - back button - to the main act
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(HeroesActivity.this, PoHBaseActivity.class);
            startActivity(intent);
            this.finish();
        }
        return false;
    }
}
