package com.planetofheroes.amarioforester.poh;

import android.appwidget.AppWidgetProviderInfo;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.YuvImage;
import android.media.ExifInterface;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class PoHBaseActivity extends MainActivity {
    ImageView imageButton;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.introimage};
    private static final Integer[] XMEN2= {R.drawable.icons,R.drawable.icons};
    private ArrayList<Integer> XMENArray = new ArrayList<>();
    private ArrayList<Integer> XMENArray2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po_hbase);
        init();

        imageButton = (ImageView) findViewById(R.id.maps);
    }

    public void backBtnClick(View v){
        Intent intent = new Intent(PoHBaseActivity.this, MapExplorePage.class);
        startActivity(intent);
    }

    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        for(int i=0;i<XMEN2.length;i++)
            XMENArray2.add(XMEN2[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(PoHBaseActivity.this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        mPager = (ViewPager) findViewById(R.id.bestpicks);
        mPager.setAdapter(new MyAdapter(PoHBaseActivity.this,XMENArray2));
        indicator = (CircleIndicator) findViewById(R.id.bestpicks2);
        indicator.setViewPager(mPager);


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Handler handler2 = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        final Runnable Update2 = new Runnable() {
            public void run() {
                if (currentPage == XMEN2.length) {
                    currentPage = 1;
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
        }, 5000000, 50000000);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit?").setIcon(R.drawable.dun);
        alertDialogBuilder.setMessage("").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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
