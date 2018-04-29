package com.planetofheroes.amarioforester.poh;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;





public class Heroes extends Fragment{
    public Heroes() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_heroes, container, false);

        String[] heroesItems = {"Hero1", "Hero2", "Hero3", "Hero4", "Hero5", "Hero6"};

        ListView listView = (ListView) view.findViewById(R.id.listOfHeroes);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, heroesItems
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.WHITE);

                if (position % 2 == 1) {
                    view.setBackgroundColor(Color.BLUE);
                } else {
                    view.setBackgroundColor(Color.CYAN);
                }

                return view;
            }
        };

        listView.setAdapter(listViewAdapter);

        return view;
    }

}
