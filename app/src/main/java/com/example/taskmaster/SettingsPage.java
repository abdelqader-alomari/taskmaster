package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        findViewById(R.id.submit).setOnClickListener(view -> {
            EditText username = findViewById(R.id.username);
            String name = username.getText().toString();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor enteredName = sharedPreferences.edit();
            enteredName.putString("username",name);
            enteredName.apply();
            Intent main = new Intent(SettingsPage.this,MainActivity.class);
            startActivity(main);
        });
    }
}