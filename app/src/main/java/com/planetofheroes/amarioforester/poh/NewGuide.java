package com.planetofheroes.amarioforester.poh;
//Masha

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
public class NewGuide extends AppCompatActivity {

    ImageView selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_guide);

        selectedImage = (ImageView) findViewById(R.id.selectedImage);
        Intent intent = getIntent();
        selectedImage.setImageResource(intent.getIntExtra("image", 0));
    }
}