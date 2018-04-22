package com.planetofheroes.amarioforester.poh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    ViewPager viewPager;
    int images[] = {R.drawable.frag, R.drawable.frag2, R.drawable.frag, R.drawable.frag2};
    MyCustomPagerAdapter myCustomPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager)findViewById(R.id.viewPager);

        myCustomPagerAdapter = new MyCustomPagerAdapter(MainActivity.this, images);
        viewPager.setAdapter(myCustomPagerAdapter);

        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nvDrawer);
        android.support.v4.app.FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.flcontent, new PoHBase());
        tx.commit();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("PoHBase");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuItem) {
        android.support.v4.app.Fragment myFragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.pohbase:
                fragmentClass = PoHBase.class;
                break;
            case R.id.heroes:
                fragmentClass = Heroes.class;
                break;
            case R.id.sparks:
                fragmentClass = Sparks.class;
                break;
            case R.id.guides:
                fragmentClass = Guides.class;
                break;
            case R.id.calculator:
                fragmentClass = Calculator.class;
                break;
            case R.id.tournaments:
                fragmentClass = Tournaments.class;
                break;
            case R.id.arts:
                fragmentClass = Arts.class;
                break;
            case R.id.about:
                fragmentClass = About_us.class;
                break;
                default:
                    fragmentClass = Heroes.class;
        }
        try {
            myFragment = (android.support.v4.app.Fragment) fragmentClass.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent, myFragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }
}
