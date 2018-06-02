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
        public ViewHolder(View itemView){
            super(itemView);
            geoIcon = (ImageView) itemView.findViewById(R.id.geoIcon);
            cardView = ((CardView) itemView.findViewById(R.id.cardHookah));
            hookahClubName = (TextView) itemView.findViewById(R.id.hookahClubName);
            materialRatingBar = (MaterialRatingBar) itemView.findViewById(R.id.rateIndicator);
            distance = (TextView) itemView.findViewById(R.id.distanceDescription);
            generalImage = (ImageView) itemView.findViewById(R.id.imageInCard);
        }

        public void setGeneralImage(Drawable generalImage) {
            this.generalImage.setImageDrawable(generalImage);
        }
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
    public static void asyncSetImages(ViewHolder viewHolder, InputStream inputStream, String id){

        try {
            viewHolder.generalImage.setImageDrawable(Drawable.createFromStream(inputStream, id));
        } catch (NullPointerException ex){
            ex.getMessage();
        }
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Hookah h = hookahs.get(i);
        viewHolder.hookahClubName.setText(h.getName());
        viewHolder.materialRatingBar.setRating(4);
        h.setDistance(viewHolder);

       Firebase.downloadMainImage(viewHolder, h.getImagesNames().get(0), h.getId());
    }


}
