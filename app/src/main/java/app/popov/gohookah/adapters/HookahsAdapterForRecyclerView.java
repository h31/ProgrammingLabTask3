package app.popov.gohookah.adapters;

import android.graphics.drawable.Drawable;
import android.location.Location;
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
import app.popov.gohookah.logic.database.FirebaseHookah;
import app.popov.gohookah.logic.storage.Storage;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class HookahsAdapterForRecyclerView extends RecyclerView.Adapter<HookahsAdapterForRecyclerView.ViewHolder> {
    public ArrayList<FirebaseHookah> hookahs;
    public Location current = null;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView hookahClubName;
        CardView cardView;
        MaterialRatingBar materialRatingBar;
        public TextView distance;
        ImageView generalImage;
        public ImageView geoIcon;
        public TextView metroDescription;
        public ImageView metroIcon;
        public TextView ad;
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
            ad = (TextView) itemView.findViewById(R.id.adMark);
        }

        public void setGeneralImage(Drawable generalImage) {
            this.generalImage.setImageDrawable(generalImage);
        }
    }


    public HookahsAdapterForRecyclerView(ArrayList<FirebaseHookah> hookahs, Double latitude, Double longitude) {
        this.hookahs = hookahs;
        if (latitude + longitude != 0){
            current = new Location("passive");
            current.setLongitude(longitude);
            current.setLatitude(latitude);
        }
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
            FirebaseHookah h = hookahs.get(i);
        if (h.getImages().size() != 0) {
            Storage.getImage(h, viewHolder.generalImage);
        } else {
            viewHolder.setGeneralImage(null);
        }
        if (current != null) {
            Location hookahLocation = new Location("passive");
            hookahLocation.setLatitude(h.getLatitude());
            hookahLocation.setLongitude(h.getLongitude());
            viewHolder.distance.setText(getKilometers(current.distanceTo(hookahLocation)));
        } else {
            viewHolder.distance.setVisibility(View.INVISIBLE);
            viewHolder.geoIcon.setVisibility(View.INVISIBLE);
        }
            viewHolder.hookahClubName.setText(h.getClubName());
            viewHolder.materialRatingBar.setRating(4F);
            viewHolder.metroDescription.setText(h.getMetro() != null ? h.getMetro() : "");
            if (viewHolder.metroDescription.getText() == "") {
                viewHolder.metroIcon.setVisibility(View.INVISIBLE);
            }
       viewHolder.ad.setVisibility(View.INVISIBLE);

        }

    public String getKilometers(Float distance){
        Integer km = Math.round(distance / 1000);
        return new StringBuilder().append("~").append(km).append("km ").append("от Вас").toString();
    }
}
