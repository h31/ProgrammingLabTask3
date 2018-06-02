package app.popov.gohookah.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import app.popov.gohookah.R;
import app.popov.gohookah.logic.Hookah;
import app.popov.gohookah.logic.database.Firebase;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class HookahsAdapter extends BaseAdapter {

    Context context;
    LayoutInflater lInflater;
    ArrayList<Hookah> hookahArrayList;

    public HookahsAdapter(Context context, ArrayList<Hookah> hookahs) {
        this.context = context;
        this.hookahArrayList = hookahs;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return hookahArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return hookahArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    Hookah getHookah(int position) {
        return ((Hookah) getItem(position));
    }

    public View getView(int position, View converView, ViewGroup parent) {
        Hookah hookah = getHookah(position);
        View view = converView;
        if (view == null) {
            view = lInflater.inflate(R.layout.hookahs, parent, false);
        }
        ((TextView) view.findViewById(R.id.hookahClubName)).setText(hookah.getName());
        ((MaterialRatingBar) view.findViewById(R.id.rateIndicator)).setRating(2.4F);

        return view;
    }
}
