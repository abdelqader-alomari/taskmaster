package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.amplifyframework.analytics.AnalyticsEvent;
import com.amplifyframework.core.Amplify;

public class AllTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

            AnalyticsEvent event = AnalyticsEvent.builder()
                    .name("Launch AllTasks activity")
                    .addProperty("Channel", "SMS")
                    .addProperty("Successful", true)
                    .addProperty("ProcessDuration", 792)
                    .addProperty("UserAge", 120.3)
                    .build();

            Amplify.Analytics.recordEvent(event);
        }
}