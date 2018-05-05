package com.planetofheroes.amarioforester.poh;

import android.appwidget.AppWidgetProviderInfo;
import android.content.DialogInterface;
import android.graphics.YuvImage;
import android.media.ExifInterface;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PoHBaseActivity extends MainActivity {

    ViewPager viewPager;
    int images[] = {R.drawable.frag, R.drawable.frag2, R.drawable.frag, R.drawable.frag2};
    MyCustomPagerAdapter myCustomPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po_hbase);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        myCustomPagerAdapter = new MyCustomPagerAdapter(PoHBaseActivity.this, images);
        viewPager.setAdapter(myCustomPagerAdapter);
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit?").setIcon(R.drawable.frag);
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
