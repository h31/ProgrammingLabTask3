package com.planetofheroes.amarioforester.poh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class PopUp_screen_gliphs extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_window2);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.densityDpi;
        int height = dm.densityDpi;

        getWindow().setLayout((int) (width*2), (int) (height*2));

        Button b = (Button) findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PopUp_screen_gliphs.this, PoHBaseActivity.class));
            }
        });


    }

}
