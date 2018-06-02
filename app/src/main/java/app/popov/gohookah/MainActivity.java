package app.popov.gohookah;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import app.popov.gohookah.adapters.HookahsAdapterForRecyclerView;
import app.popov.gohookah.listeners.GeneralHookahsClickListener;
import app.popov.gohookah.logic.Hookah;
import app.popov.gohookah.listeners.MyLocationListener;
import app.popov.gohookah.logic.database.Firebase;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static RecyclerView hookahList;
    static HookahsAdapterForRecyclerView adapter;
    private static final String[] INITIAL_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
    };
    static AssetManager asset;
    static Context context;
    static TextView description;
    static MyLocationListener myLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myLocationListener = new MyLocationListener();
        context = getApplicationContext();
        ActivityCompat.requestPermissions(this, INITIAL_PERMS, 1337);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myLocationListener.SetUpLocationListener(context);
        description = (TextView) findViewById(R.id.mainActivityDescription);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        hookahList = (RecyclerView) findViewById(R.id.recyclerViewHookahs);
        hookahList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        hookahList.setLayoutManager(llm);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddHookahActivity.class);
            startActivity(intent);
        });
        hookahList.addOnItemTouchListener(new GeneralHookahsClickListener(context, hookahList, new GeneralHookahsClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println(view.getId());
                System.out.println(position);
                startActivity(new Intent(context, HookahPage.class).putExtra("position", position));

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        myLocationListener.SetUpLocationListener(context);
        Firebase.getHookahsFromFireBase(myLocationListener.getCurrent());
        asset = getAssets();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add_hookah) {
            Intent intent = new Intent(this, AddHookahActivity.class);
            startActivity(intent);
        } else if (id == R.id.show_hookah_list) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void setHookahListAdapter(ArrayList<Hookah> hookahs) {
        if (hookahs.size() == 0) {
            try {
                Location current = myLocationListener.current;
                String city = new Geocoder(context).getFromLocation(current.getLatitude(), current.getLongitude(),1).get(0).getLocality();
                description.setText("Нам очень жаль, но мы не знаем ни одного кальянного клуба в городе " + city);
            } catch (IOException ex) {
                ex.getMessage();
            }
        } else {
            description.setVisibility(View.INVISIBLE);
            hookahList.setAdapter(new HookahsAdapterForRecyclerView(hookahs));
        }
    }


}

