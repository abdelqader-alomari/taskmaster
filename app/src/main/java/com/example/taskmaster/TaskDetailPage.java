package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_page);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String title = sharedPreferences.getString("task_title","task_title");
        String description = sharedPreferences.getString("task_description","description");
        String state = sharedPreferences.getString("task_state","state");

        TextView welcome = findViewById(R.id.title_detail);
        TextView task_description = findViewById(R.id.task_description);
        TextView task_state = findViewById(R.id.task_state);
        welcome.setText("Task Title: " + title);
        task_description.setText("Task Description: " + description);
        task_state.setText("Task State: " + state);
    }
}
