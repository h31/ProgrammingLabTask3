package app.popov.gohookah.adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.CardView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.io.InputStream;
import java.util.ArrayList;

import app.popov.gohookah.MainActivity;
import app.popov.gohookah.R;
import app.popov.gohookah.logic.Hookah;
import app.popov.gohookah.logic.database.Firebase;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class HookahsAdapterForRecyclerView extends RecyclerView.Adapter<HookahsAdapterForRecyclerView.ViewHolder> {
    public ArrayList<Hookah> hookahs;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView hookahClubName;
        CardView cardView;
        MaterialRatingBar materialRatingBar;
        public TextView distance;
        ImageView generalImage;
        public ImageView geoIcon;
        public TextView metroDescription;
        public ImageView metroIcon;
        public ViewHolder(View itemView){
            super(itemView);
            metroDescription = (TextView) itemView.findViewById(R.id.metroDescription);
            geoIcon = (ImageView) itemView.findViewById(R.id.geoIcon);
            cardView = ((CardView) itemView.findViewById(R.id.cardHookah));
            hookahClubName = (TextView) itemView.findViewById(R.id.hookahClubName);
            materialRatingBar = (MaterialRatingBar) itemView.findViewById(R.id.rateIndicator);
            distance = (TextView) itemView.findViewById(R.id.distanceDescription);
            generalImage = (ImageView) itemView.findViewById(R.id.imageInCard);
            metroIcon = (ImageView) itemView.findViewById(R.id.metroIcon);
        }

        public void setGeneralImage(Drawable generalImage) {
            this.generalImage.setImageDrawable(generalImage);
        }
    }

    public int getPositionFromHookahID(String id){
        int position = 0;
        for(Hookah hookah : hookahs){
            if (hookah.getId().equals(id)) {
                return position;
            }
            position++;
        }
        return -1;
    }


    public Hookah getHookahFromAdapter(int position){
        return hookahs.get(position);
    }
    public HookahsAdapterForRecyclerView(ArrayList<Hookah> hookahs){
        this.hookahs = hookahs;
    }

    @Override
    public int getItemCount(){
        return hookahs.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public HookahsAdapterForRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hookahsadapterforrecyclerview, parent , false);
      ViewHolder hv = new ViewHolder(v);
      return hv;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Hookah h = hookahs.get(i);
        viewHolder.hookahClubName.setText(h.getName());
        viewHolder.materialRatingBar.setRating(4F);
        viewHolder.metroDescription.setText(h.getMetro() != null ? h.getMetro() : "");
        if (viewHolder.metroDescription.getText() == ""){
            viewHolder.metroIcon.setVisibility(View.INVISIBLE);
        }
        if (h.getImagesNames().size() != 0) {
           viewHolder.generalImage.setImageDrawable(Firebase.getImage(h, h.getImagesNames().get(0)));
        }
        h.setDistance(viewHolder);
    }

    public void update(ArrayList<Hookah> hookahArrayList){
        hookahs.clear();
        System.out.println(hookahArrayList);
        hookahs.addAll(hookahArrayList);
        notifyDataSetChanged();
    }

    public void removeItem(int position, RecyclerView rv){
        rv.removeViewAt(position);
        hookahs.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, hookahs.size());
    }

    public void addHookah(Hookah hookah, RecyclerView rv){
        hookahs.add(hookah);
        notifyDataSetChanged();
    }


}
