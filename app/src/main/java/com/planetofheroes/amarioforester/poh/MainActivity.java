package com.planetofheroes.amarioforester.poh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppBaseActivity {

    ViewPager viewPager;
    int images[] = {R.drawable.frag, R.drawable.frag2, R.drawable.frag, R.drawable.frag2};
    MyCustomPagerAdapter myCustomPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("PoHBase");
        }
    }
}
