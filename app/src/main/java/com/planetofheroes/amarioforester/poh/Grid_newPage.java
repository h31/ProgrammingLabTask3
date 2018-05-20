package com.planetofheroes.amarioforester.poh;
//Masha

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
public class Grid_newPage extends AppCompatActivity {

    ImageView selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_newpage);

        selectedImage = (ImageView) findViewById(R.id.selectedImage);
        Intent intent = getIntent();
        selectedImage.setImageResource(intent.getIntExtra("image", 0));
    }

    public void backBtnClick(View v){
        this.finish();
    }
}