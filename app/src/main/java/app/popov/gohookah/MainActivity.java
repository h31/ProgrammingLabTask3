package app.popov.gohookah;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.popov.gohookah.adapters.HookahsAdapterForRecyclerView;
import app.popov.gohookah.adapters.ViewPagerAdapter;
import app.popov.gohookah.fragments.GoogleMapFragment;
import app.popov.gohookah.fragments.MapViewFragment;
import app.popov.gohookah.fragments.RatingAndContactsFragment;
import app.popov.gohookah.fragments.RecyclerViewFragment;
import app.popov.gohookah.fragments.ReviewsFragment;
import app.popov.gohookah.listeners.GeneralHookahsClickListener;
import app.popov.gohookah.logic.Hookah;
import app.popov.gohookah.logic.database.Firebase;
import app.popov.gohookah.logic.database.FirebaseHookah;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static AssetManager asset;
    static Context context;

    static Location current;

    RecyclerViewFragment recyclerViewFragment;
    Bundle bundle;
    GoogleMapFragment mapViewFragment;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();

        Intent intentWithCoordinates = getIntent();
        current = new Location("passive");
        Double latitude = intentWithCoordinates.getDoubleExtra("latitude", 0.0);
        Double longitude = intentWithCoordinates.getDoubleExtra("longitude", 0.0);
        current.setLatitude(latitude);
        current.setLongitude(longitude);
        recyclerViewFragment = new RecyclerViewFragment();
        bundle = new Bundle();
        if (intentWithCoordinates.getStringExtra("locale") != null) {
            bundle.putString("locale", intentWithCoordinates.getStringExtra("locale"));
        } else {
            bundle.putString("locale", null);
        }
        bundle.putDouble("latitude", latitude);
        bundle.putDouble("longitude", longitude);
        recyclerViewFragment.setArguments(bundle);
        mapViewFragment = new GoogleMapFragment();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_view_list_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_map_black_24dp);


        ImageButton cityButton = (ImageButton) findViewById(R.id.citybutton);
        cityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, SearchCityActivity.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        asset = getAssets();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(recyclerViewFragment, "");
        adapter.addFragment(mapViewFragment, "");
        viewPager.setAdapter(adapter);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add_hookah) {
            Intent intent = new Intent(this, AddHookahActivity.class);
            startActivity(intent);
        } else if (id == R.id.show_hookah_list) {
        } else if (id == R.id.loginitem){
            Intent intent = new Intent(context, SignInActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}

