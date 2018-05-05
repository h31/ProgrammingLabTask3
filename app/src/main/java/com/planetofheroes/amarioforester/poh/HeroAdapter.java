package com.planetofheroes.amarioforester.poh;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class HeroAdapter extends ArrayAdapter<Hero> {
    private Context mContext;
    private List<Hero> heroesList = new ArrayList<>();

    public HeroAdapter(@NonNull Context context, ArrayList<Hero> list) {
        super(context, 0 , list);
        mContext = context;
        heroesList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Hero currentHero = heroesList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_hero);
        image.setImageResource(currentHero.getmImageDrawable());

        return listItem;
    }
}
