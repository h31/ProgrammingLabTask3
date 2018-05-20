package com.planetofheroes.amarioforester.poh;

import android.os.Bundle;

public class MainActivity extends Left_menu {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("PoHBase");
        }
    }
}
