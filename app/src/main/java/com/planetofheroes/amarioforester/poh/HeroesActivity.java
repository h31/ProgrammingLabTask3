package com.planetofheroes.amarioforester.poh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
        heroesList.add(new Hero(R.drawable.blue));
        heroesList.add(new Hero(R.drawable.bubb));
        heroesList.add(new Hero(R.drawable.dun));
        heroesList.add(new Hero(R.drawable.can));
        heroesList.add(new Hero(R.drawable.sky));
        heroesList.add(new Hero(R.drawable.timm));
        heroesList.add(new Hero(R.drawable.iff));
        heroesList.add(new Hero(R.drawable.blue));
        heroesList.add(new Hero(R.drawable.bubb));
        heroesList.add(new Hero(R.drawable.can));
        heroesList.add(new Hero(R.drawable.sky));


        mAdapter = new HeroAdapter(this, heroesList);
        listView.setAdapter(mAdapter);

        // Clickable list buttons on heroes // Masha + Dany

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    Intent myintent = new Intent(view.getContext(), BlueBeard.class);
                    startActivityForResult(myintent, 0);
                }
                if (position == 1) {
                    Intent myintent = new Intent(view.getContext(), Bubbles.class);
                    startActivityForResult(myintent, 1);
                }
                if (position == 2) {
                    Intent myintent = new Intent(view.getContext(), ArtsActivity.class);
                    startActivityForResult(myintent, 2);
                }
                if (position == 3) {
                    Intent myintent = new Intent(view.getContext(), ArtsActivity.class);
                    startActivityForResult(myintent, 3);
                }
                if (position == 4) {
                    Intent myintent = new Intent(view.getContext(), ArtsActivity.class);
                    startActivityForResult(myintent, 4);
                }
                if (position == 5) {
                    Intent myintent = new Intent(view.getContext(), ArtsActivity.class);
                    startActivityForResult(myintent, 5);
                }
                if (position == 6) {
                    Intent myintent = new Intent(view.getContext(), ArtsActivity.class);
                    startActivityForResult(myintent, 6);
                }
            }
        });
    }

}
