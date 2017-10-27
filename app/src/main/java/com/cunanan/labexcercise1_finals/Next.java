package com.cunanan.labexcercise1_finals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Next extends AppCompatActivity {

    Button btnShare, btnInternal;
    TextView tvDisplay, tvDisplay2;
    FileInputStream fis;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        btnInternal = (Button) findViewById(R.id.Internal);
        btnShare = (Button) findViewById(R.id.Share);
        tvDisplay = (TextView) findViewById(R.id.tv_Display);
        tvDisplay2 = (TextView) findViewById(R.id.tv_Display2);
        preferences = getPreferences(Context.MODE_PRIVATE);
    }

    public void GoBack (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void ShareDis(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String user = preferences.getString("username", "");
        String pwd = preferences.getString("password", "");
        tvDisplay2.setText("The Username is " + user + "and is " + pwd);
    }
    public void InternalDis(View view){
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("output.txt");
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tvDisplay.setText("The Username is" + buffer.toString());
    }
    public void Clear(View view){
        tvDisplay.setText("");
        tvDisplay2.setText("");
    }
}
