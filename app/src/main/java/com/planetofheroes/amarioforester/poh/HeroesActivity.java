package com.planetofheroes.amarioforester.poh;

import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class HeroesActivity extends MainActivity {

    private ListView listView;
    private HeroAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);

        listView = (ListView) findViewById(R.id.heroes_list);
        ArrayList<Hero> heroesList = new ArrayList<>();
        heroesList.add(new Hero(R.drawable.beardlayer));
        heroesList.add(new Hero(R.drawable.bubbleslayer));
        heroesList.add(new Hero(R.drawable.duncanlayer));
        heroesList.add(new Hero(R.drawable.sofialayer));
        heroesList.add(new Hero(R.drawable.timmylayer));

        mAdapter = new HeroAdapter(this, heroesList);
        listView.setAdapter(mAdapter);
    }
}
