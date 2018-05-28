package app.popov.gohookah;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.util.IOUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import app.popov.gohookah.logic.database.Firebase;

public class HookahPage extends AppCompatActivity {
    static TextView textView;
    static FileWriter fileWriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hookah_page);
        Intent intent = getIntent();
        textView = (TextView) findViewById(R.id.testText);
    }


    public static void setText(String text){
        textView.setText(text);
    }

}
