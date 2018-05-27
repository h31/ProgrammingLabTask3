package com.planetofheroes.amarioforester.poh;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SparksActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sparks);

        EditText editText = (EditText) findViewById(R.id.hui);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_NEXT) {
                    String inputString = textView.getText().toString();
                   if (Integer.parseInt(inputString) < 6) {
                       Intent intent = new Intent(SparksActivity.this, SparksOneFive.class);
                       startActivity(intent);
                   } if (Integer.parseInt(inputString) > 5 && Integer.parseInt(inputString) < 11) {
                        Intent intent = new Intent(SparksActivity.this, SparksSixTen.class);
                        startActivity(intent);
                    } if (Integer.parseInt(inputString) > 10 && Integer.parseInt(inputString) < 16) {
                        Intent intent = new Intent(SparksActivity.this, SparksElevenFifteen.class);
                        startActivity(intent);
                    } if (Integer.parseInt(inputString) > 15 && Integer.parseInt(inputString) < 21) {
                        Intent intent = new Intent(SparksActivity.this, SparksSixteenTwenty.class);
                        startActivity(intent);
                    } if (Integer.parseInt(inputString) > 20 && Integer.parseInt(inputString) < 26) {
                        Intent intent = new Intent(SparksActivity.this, SparksTwentyoneFive.class);
                        startActivity(intent);
                    } if (Integer.parseInt(inputString) > 25 && Integer.parseInt(inputString) < 31) {
                        Intent intent = new Intent(SparksActivity.this, SparksTwentysixThirty.class);
                        startActivity(intent);
                    } if (Integer.parseInt(inputString) < 1 || Integer.parseInt(inputString) > 30) {
                       Toast.makeText(getApplicationContext(), "Put your real level in", Toast.LENGTH_SHORT).show();
                   }
                }
                return handled;
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
}
