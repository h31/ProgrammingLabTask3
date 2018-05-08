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
    }
}
