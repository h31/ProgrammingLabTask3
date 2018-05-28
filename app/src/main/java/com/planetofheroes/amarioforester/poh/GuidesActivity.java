package com.planetofheroes.amarioforester.poh;
//Masha
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class GuidesActivity extends Left_menu {
    GridView simpleGrid;//Masha - GridView
    int logos[] = {R.drawable.skysqr, R.drawable.duncsqr, R.drawable.candsqr,
            R.drawable.bluesq, R.drawable.bubbsqr, R.drawable.candsqr, R.drawable.duncsqr
            , R.drawable.bubbsqr, R.drawable.bluesq, R.drawable.skysqr};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guides);

        simpleGrid = (GridView) findViewById(R.id.guidesGridView);

        Grid_adapter customAdapter = new Grid_adapter(getApplicationContext(), logos);
        simpleGrid.setAdapter(customAdapter);

        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GuidesActivity.this, Grid_newPage.class);
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


