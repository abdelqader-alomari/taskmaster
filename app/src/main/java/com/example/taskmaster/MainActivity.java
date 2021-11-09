package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    boolean configured = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (configured) {
            configureAmplify();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTask = findViewById(R.id.button3);

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
                Intent intent2 = new Intent(MainActivity.this, AllTasks.class);
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
        showUser.setText(sharedPreferences.getString("username", "user") + "'s Tasks");

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

        RecyclerView allTasksRecyclerView = findViewById(R.id.RecyclerView);

        // set a layout manager
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set the adapter for this recycler view
        List<Todo> tasks = new ArrayList<Todo>();
        tasks = Data();
        allTasksRecyclerView.setAdapter(new TaskAdapter(tasks));
    }

    private void configureAmplify() {
        configured = false;
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i(TAG, "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e(TAG, "Could not initialize Amplify", error);
        }
    }

    private List<Todo> Data() {
        List<Todo> foundExpense = new ArrayList<>();

        Amplify.DataStore.query(
                Todo.class,
                queryMatches -> {
                    while (queryMatches.hasNext()) {
                        Log.i(TAG, "Successful query, found tasks.");
                        foundExpense.add(queryMatches.next());
                    }
                },
                error -> {
                    Log.i(TAG, "Error retrieving expenses", error);
                });
        return foundExpense;
    }
}