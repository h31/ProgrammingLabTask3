package app.popov.gohookah.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.popov.gohookah.HookahPage;
import app.popov.gohookah.R;
import app.popov.gohookah.adapters.HookahsAdapterForRecyclerView;
import app.popov.gohookah.listeners.GeneralHookahsClickListener;
import app.popov.gohookah.logic.MyCallback;
import app.popov.gohookah.logic.database.Firebase;
import app.popov.gohookah.logic.database.FirebaseHookah;

public class RecyclerViewFragment extends Fragment {
    public RecyclerViewFragment() {
    }

    TextView description;
    SwipeRefreshLayout swipeLayout;
    ImageView emojiDontKnow;
    TextView weDontKnowText;

    RecyclerView hookahList;
    String locale;
    Context context;
    Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bundle = getArguments();
        context = getContext();
        locale = bundle.get("locale") != null ? bundle.get("locale").toString() : "";
        AdapterSetterCallback callback = new AdapterSetterCallback();
        Firebase.getHookahList(callback, context, "Тольятти");


        View rootView = inflater.inflate(R.layout.hookah_list_fragment, container, false);
        FloatingActionButton upButton = (FloatingActionButton) rootView.findViewById(R.id.upButton);
        weDontKnowText = (TextView) rootView.findViewById(R.id.wearedontknow);
        emojiDontKnow = (ImageView) rootView.findViewById(R.id.emoji);
        hookahList = (RecyclerView) rootView.findViewById(R.id.recyclerViewHookahs);
        hookahList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        hookahList.setLayoutManager(llm);
        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        swipeLayout.setColorSchemeResources(R.color.purple, R.color.colorPrimary, R.color.yellow_rating_bar);
        swipeLayout.setOnRefreshListener(() -> {
            Firebase.getHookahList(callback, context, "Тольятти");
        });


        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hookahList.smoothScrollToPosition(0);
            }
        });
        hookahList.addOnItemTouchListener(new GeneralHookahsClickListener(context, hookahList, new GeneralHookahsClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(context, HookahPage.class).putExtra("position", position).putExtra("fromMap", false));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        return rootView;
    }

    /*
        public void setHookahListAdapter(ArrayList<FirebaseHookah> hookahs) {
            if (hookahs.size() != 0) {
                emojiDontKnow.setVisibility(View.INVISIBLE);
                weDontKnowText.setVisibility(View.INVISIBLE);
                adapter = new HookahsAdapterForRecyclerView(hookahs);
                hookahList.setAdapter(adapter);
                hookahList.setVisibility(View.VISIBLE);
                hookahList.setClickable(true);
            } else {
                hookahList.setVisibility(View.INVISIBLE);
                emojiDontKnow.setVisibility(View.VISIBLE);
                weDontKnowText.setText(locale == null ? "Мы не знаем, где вы находитесь" : "Мы не знаем ни одного кальянного клуба в городе " + locale);
                weDontKnowText.setVisibility(View.VISIBLE);
            }
            swipeLayout.setRefreshing(false);
        }
    */
    public void startLocaleChange(String pressedCity) {
        locale = pressedCity;
        swipeLayout.setRefreshing(true);
        //Firebase.getHookahList(adapter, context, pressedCity);
    }

    public class AdapterSetterCallback implements MyCallback {
        @Override
        public void call() {
            HookahsAdapterForRecyclerView adapter = new HookahsAdapterForRecyclerView(new ArrayList<>(Firebase.getHookahs().values()), bundle.getDouble("latitude"), bundle.getDouble("longitude"));
            hookahList.setAdapter(adapter);
        }
    }
}
