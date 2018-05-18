package com.planetofheroes.amarioforester.poh;
//Masha
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class GuidesActivity extends MainActivity {
    GridView simpleGrid;//Masha - GridView
    int logos[] = {R.drawable.square, R.drawable.square, R.drawable.square,
            R.drawable.square, R.drawable.square, R.drawable.square, R.drawable.square
            , R.drawable.square, R.drawable.square, R.drawable.square};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guides);

        simpleGrid = (GridView) findViewById(R.id.guidesGridView);

        ImageAdapter customAdapter = new ImageAdapter(getApplicationContext(), logos);
        simpleGrid.setAdapter(customAdapter);

        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GuidesActivity.this, NewGuide.class);
                intent.putExtra("image", logos[position]);
                startActivity(intent);
            }
        });
    }

    @Override                              //masha - back button - to the main act
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(GuidesActivity.this, PoHBaseActivity.class);
            startActivity(intent);
            this.finish();
        }
        return false;
    }
}


