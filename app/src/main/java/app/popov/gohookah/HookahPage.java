package app.popov.gohookah;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Fragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import app.popov.gohookah.adapters.ViewPagerAdapter;
import app.popov.gohookah.fragments.RatingAndContactsFragment;
import app.popov.gohookah.fragments.ReviewsFragment;
import app.popov.gohookah.logic.Hookah;
import app.popov.gohookah.logic.database.Firebase;


public class HookahPage extends AppCompatActivity {
    static TextView textView;
    static ImageView imageView;
    static String position;
    static Hookah thisHookah;
    static TextView phone;
    static CollapsingToolbarLayout toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hookahpagewithcoordinator);
        Intent intent = getIntent();

        imageView = (ImageView) findViewById(R.id.main_backdrop);
        phone = (TextView) findViewById(R.id.phoneField);
        thisHookah = Firebase.getHookahForAdapter(intent.getIntExtra("position", -1));
        Firebase.getImagesForHookah(thisHookah);
        toolbar = (CollapsingToolbarLayout) findViewById(R.id.main_collapsing);

        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RatingAndContactsFragment(), "Информация");
        adapter.addFragment(new ReviewsFragment(), "Отзывы");
        viewPager.setAdapter(adapter);
    }


    public static void setImage(InputStream inputStream) {
        Drawable drawable;
        try {
            drawable = Drawable.createFromStream(inputStream, thisHookah.getId());
        } catch (NullPointerException ex){
            drawable = null;
        }
        imageView.setImageDrawable(drawable);
        setHeader(thisHookah.getName());
    }

    public static void setHeader(String header) {
        toolbar.setTitle(header);
    }

}
