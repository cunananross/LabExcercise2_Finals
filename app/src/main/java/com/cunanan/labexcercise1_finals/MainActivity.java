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
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnShare, btnInternal,btnNext;
    EditText etUsername, etPassword;
    TextView tvDisplay;
    FileInputStream fis;
    FileOutputStream fos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = (EditText) findViewById(R.id.et_Username);
        etPassword = (EditText) findViewById(R.id.et_Password);
        btnShare = (Button) findViewById(R.id.SharedPreferences);
        btnInternal = (Button) findViewById(R.id.InternalStorage);
        btnNext = (Button)  findViewById(R.id.Next);
    }

    public void Share(View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username",etUsername.getText().toString());
        editor.putString("password",etPassword.getText().toString());
        editor.apply();
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
    }

    public void  Internal(View view) {
        String username = " " + etUsername.getText().toString() + " and";
        String password = " Password is " + etPassword.getText().toString() + " ";
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(username.getBytes());
            fos.write(password.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } Toast.makeText(this, "Message saved!", Toast.LENGTH_SHORT).show();
    }

    public void goNext (View view){
        Intent intent = new Intent(this, Next.class);
        startActivity(intent);
    }
}
