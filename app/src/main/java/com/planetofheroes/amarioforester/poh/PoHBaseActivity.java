package com.planetofheroes.amarioforester.poh;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class PoHBaseActivity extends MainActivity {
    ImageView imageButton;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] slideImages= {R.drawable.introimage};
    private static final Integer[] slideImages2= {R.drawable.icons,R.drawable.icons};
    private ArrayList<Integer> slideArray = new ArrayList<>();
    private ArrayList<Integer> slideArray2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po_hbase);
        init();

        imageButton = (ImageView) findViewById(R.id.maps);
    }

    public void backBtnClick(View v){
        Intent intent = new Intent(PoHBaseActivity.this, MapExplorePage_MainScreen.class);
        startActivity(intent);
    }

    private void init() {
        for(int i=0;i<slideImages.length;i++)
            slideArray.add(slideImages[i]);

        for(int i=0;i<slideImages2.length;i++)
            slideArray2.add(slideImages2[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new Slide_adapter(PoHBaseActivity.this,slideArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        mPager = (ViewPager) findViewById(R.id.bestpicks);
        mPager.setAdapter(new Slide_adapter(PoHBaseActivity.this,slideArray2));
        indicator = (CircleIndicator) findViewById(R.id.bestpicks2);
        indicator.setViewPager(mPager);


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Handler handler2 = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == slideImages.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        final Runnable Update2 = new Runnable() {
            public void run() {
                if (currentPage == slideImages2.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 0, 5000);

        Timer swipeTimer2 = new Timer();
        swipeTimer2.schedule(new TimerTask() {
            @Override
            public void run() {
                handler2.post(Update2);
            }
        }, 5000, 5000);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Are you sure you want to exit?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
