package com.planetofheroes.amarioforester.poh;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SparksActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sparks);

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SparksActivity.this, PopUp_screen.class));
            }
        });

        EditText editText = (EditText) findViewById(R.id.hui);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_NEXT) {
                    String inputString = textView.getText().toString();
                    Toast.makeText(SparksActivity.this, inputString, Toast.LENGTH_SHORT).show();
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
