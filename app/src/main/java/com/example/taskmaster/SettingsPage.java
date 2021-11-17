package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.analytics.AnalyticsEvent;
import com.amplifyframework.core.Amplify;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        recordEvent();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        findViewById(R.id.save_user).setOnClickListener(view -> {
            TextView text = findViewById(R.id.Username);
            RadioButton team1=findViewById(R.id.radioButton1s);
            RadioButton team2=findViewById(R.id.radioButton2s);
            RadioButton team3=findViewById(R.id.radioButton3s);

            String id = null;
            String teamName = null;
            if(team1.isChecked()){
                id="1";
                teamName = "SSD Coders";
            }
            else if(team2.isChecked()){
                id="2";
                teamName = "Java Lovers";
            }
            else if(team3.isChecked()){
                id="3";
                teamName = "Dev Masters";
            }
            String name =text.getText().toString();
            editor.putString("UserName",name);
            editor.putString("Team",id);
            editor.putString("TeamName",teamName);
            editor.apply();
            Intent gotToInst = new Intent(SettingsPage.this,MainActivity.class);
            startActivity(gotToInst);
        });
    }
    private void recordEvent(){
        AnalyticsEvent event = AnalyticsEvent.builder()
                .name("Launch Setting Activity")
                .addProperty("Channel", "SMS")
                .addProperty("Successful", true)
                .addProperty("ProcessDuration", 792)
                .addProperty("UserAge", 120.3)
                .build();

        Amplify.Analytics.recordEvent(event);
    }

}