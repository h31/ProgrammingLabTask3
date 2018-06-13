package app.popov.gohookah.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.popov.gohookah.R;

public class LoadingItemsAdapter extends RecyclerView.Adapter<HookahsAdapterForRecyclerView.ViewHolder>{
   public ArrayList<String> fakeHookahs;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    @NonNull
    @Override
    public HookahsAdapterForRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hookahsadapterforrecyclerview, parent , false);
        HookahsAdapterForRecyclerView.ViewHolder hv = new HookahsAdapterForRecyclerView.ViewHolder(v);
        return hv;
    }

    @Override
    public void onBindViewHolder(@NonNull HookahsAdapterForRecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return fakeHookahs.size();
    }
}
