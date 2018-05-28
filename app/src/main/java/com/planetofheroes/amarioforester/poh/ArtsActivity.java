package com.planetofheroes.amarioforester.poh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.GridView;

public class ArtsActivity extends Left_menu {

    GridView simpleGrid;//Masha - GridView
    int logos[] = {R.drawable.background, R.drawable.introimage, R.drawable.exploremaps,
            R.drawable.square, R.drawable.square, R.drawable.square, R.drawable.square
            , R.drawable.square, R.drawable.square, R.drawable.square};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arts);

        simpleGrid = (GridView) findViewById(R.id.guidesGridView2);

        Grid_adapter customAdapter = new Grid_adapter(getApplicationContext(), logos);
        simpleGrid.setAdapter(customAdapter);

        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ArtsActivity.this, Grid_newPage.class);
                intent.putExtra("image", logos[position]);
                startActivity(intent);
            }
        });
    }

    @Override                              //masha - back button - to the main act
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ArtsActivity.this, PoHBaseActivity.class);
            startActivity(intent);
            this.finish();
        }
        return false;
    }
}
