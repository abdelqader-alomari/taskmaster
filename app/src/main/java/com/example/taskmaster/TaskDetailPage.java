package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class TaskDetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_page);
        TextView taskName = findViewById(R.id.textView3);
        TextView content = findViewById(R.id.textView5);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String title = getIntent().getStringExtra("title");
        String body = getIntent().getStringExtra("body");
        taskName.setText(sharedPreferences.getString("username","Enter your name")+"'s "+ title + " details" );

        content.setText(body);

        if(title == null || body == null){
            taskName.setText(sharedPreferences.getString("username", "user") + "'s" + "Task " + "details");
            content.setText("There is no details");
        }
    }
}