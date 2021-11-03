package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    ArrayList<Task> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTask = findViewById(R.id.button3);
        tasks = (ArrayList<Task>) AppDatabase.getInstance(getApplicationContext()).taskDao().getAll();

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, AddTask.class);
                startActivity(intent1);
            }
        });

        Button allTasks = findViewById(R.id.button2);
        allTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this,AllTasks.class);
                startActivity(intent2);
            }
        });

        Button task1 = findViewById(R.id.Task1);
        Button task2 = findViewById(R.id.Task2);
        Button task3 = findViewById(R.id.Task3);

        task1.setText("Task 1");
        task2.setText("Task 2");
        task3.setText("Task 3");

        TextView showUser = findViewById(R.id.user);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        showUser.setText(sharedPreferences.getString("username","user")+ "'s Tasks");

        String title1 = task1.getText().toString();
        String title2 = task2.getText().toString();
        String title3 = task3.getText().toString();

        findViewById(R.id.Task1).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TaskDetailPage.class);
            intent.putExtra("title", title1);
            startActivity(intent);
        });
        findViewById(R.id.Task2).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TaskDetailPage.class);
            intent.putExtra("title", title2);
            startActivity(intent);
        });
        findViewById(R.id.Task3).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TaskDetailPage.class);
            intent.putExtra("title", title3);
            startActivity(intent);
        });
        findViewById(R.id.settings).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SettingsPage.class);
            startActivity(intent);
        });

//        ArrayList<Task> tasksData = new ArrayList<Task>();
//            tasksData.add(new Task("Java","Learn Java","complete"));
////            tasksData.add(new Task("Android","Learn Android","in progress"));
////            tasksData.add(new Task("LinkedList","Review and Practice LinkedList","assigned"));
////            tasksData.add(new Task("AWS","Explore Amazon and deploy android","new"));

        RecyclerView allTasksRecyclerView = findViewById(R.id.RecyclerView);

        // set a layout manager
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set the adapter for this recycler view
        allTasksRecyclerView.setAdapter(new TaskAdapter(tasks));


    }
}