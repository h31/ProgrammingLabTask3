package com.planetofheroes.amarioforester.poh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Grid_adapter extends BaseAdapter {

    Context context;
    int logos[];
    LayoutInflater inflter;

    public Grid_adapter(Context applicationContext, int[] logos) {
        this.context = applicationContext;
        this.logos = logos;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return logos.length;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.grid_set, null);
        ImageView icon = (ImageView) view.findViewById(R.id.guideImage);
        icon.setImageResource(logos[i]);
        return view;
    }
}