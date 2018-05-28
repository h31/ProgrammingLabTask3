package com.planetofheroes.amarioforester.poh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SparksActivity extends MainActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sparks);

        EditText editText = (EditText) findViewById(R.id.type_spark);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_NEXT) {
                    String inputString = textView.getText().toString();
                    if (Integer.parseInt(inputString) < 1 || Integer.parseInt(inputString) > 30) {
                        Toast.makeText(getApplicationContext(), "This lvl doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                    if (Integer.parseInt(inputString) > 0 &&Integer.parseInt(inputString) < 6) {
                        Intent intent = new Intent(SparksActivity.this, Sparks_1_5.class);
                        startActivity(intent);
                    }
                    if (Integer.parseInt(inputString) > 5 && Integer.parseInt(inputString) < 11) {
                        Intent intent = new Intent(SparksActivity.this, Sparks_6_10.class);
                        startActivity(intent);
                    }
                    if (Integer.parseInt(inputString) > 10 && Integer.parseInt(inputString) < 16) {
                        Intent intent = new Intent(SparksActivity.this, Sparks_11_15.class);
                        startActivity(intent);
                    }
                    if (Integer.parseInt(inputString) > 15 && Integer.parseInt(inputString) < 21) {
                        Intent intent = new Intent(SparksActivity.this, Sparks_16_20.class);
                        startActivity(intent);
                    }
                    if (Integer.parseInt(inputString) > 20 && Integer.parseInt(inputString) < 26) {
                        Intent intent = new Intent(SparksActivity.this, Sparks_21_25.class);
                        startActivity(intent);
                    }
                    if (Integer.parseInt(inputString) > 25 && Integer.parseInt(inputString) < 31) {
                        Intent intent = new Intent(SparksActivity.this, Sparks_26_30.class);
                        startActivity(intent);
                    }
                }
                return handled;
            }
        });

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(SparksActivity.this, button);
                popupMenu.getMenuInflater().inflate(R.menu.sparks_lvl, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.common:
                                Intent myintent = new Intent(getBaseContext(), Hero_BlueBeard.class);
                                startActivityForResult(myintent, 0);
                                break;
                            case R.id.rare:
                                Intent myintent1 = new Intent(getBaseContext(), Explore_Archon.class);
                                startActivityForResult(myintent1, 1);
                                break;
                            case R.id.epic:
                                Intent myintent2 = new Intent(getBaseContext(), Hero_BlueBeard.class);
                                startActivityForResult(myintent2, 2);
                                break;
                            default:
                                Intent myintent3 = new Intent(getBaseContext(), Hero_Iffir.class);
                                startActivityForResult(myintent3, 3);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override                              //masha - back button - to the main act
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(SparksActivity.this, PoHBaseActivity.class);
            startActivity(intent);
            this.finish();
        }
        return false;
    }
    public void BtnClick(View v){
        startActivity(new Intent(this, PopUp_screen.class));
    }

}
