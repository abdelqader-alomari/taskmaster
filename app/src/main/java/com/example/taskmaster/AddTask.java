package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;

public class AddTask extends AppCompatActivity {
    private static final String TAG = "AddTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button addTask = findViewById(R.id.button4);
        addTask.setOnClickListener(new View.OnClickListener() {

            public int tasksCounter;

            private void dataStore(String title,String body,String status){
                Todo task = Todo.builder()
                        .title(title)
                        .body(body)
                        .state(status)
                        .build();

                // save with the datastore
                Amplify.DataStore.save(task, result -> {
                    Log.i(TAG, "Task Saved");
                }, error -> {
                    Log.i(TAG, "Task Not Saved");
                });

                // query with the datastore
                Amplify.DataStore.query(
                        Todo.class,
                        queryMatches -> {
                            while (queryMatches.hasNext()) {
                                Log.i(TAG, "Successful query, found tasks.");
                                Todo taskMaster = queryMatches.next();
                                Log.i(TAG, taskMaster.getTitle());
//                        label.setText(taskMaster.getTitle());
                            }
                        },
                        error -> {
                            Log.i(TAG,  "Error retrieving tasks", error);
                        });
            }

            @Override
            public void onClick(View view) {
                EditText taskTitleField = findViewById(R.id.taskTitleInput);
                String taskTitle = taskTitleField.getText().toString();

                EditText taskBodyField = findViewById(R.id.taskDescriptionInput);
                String taskBody = taskBodyField.getText().toString();

                EditText taskStateField = findViewById(R.id.taskStateInput);
                String taskState = taskStateField.getText().toString();

                dataStore(taskTitle, taskBody, taskState);

//                System.out.println(
//                        "***********************************************" + "Task ID : " + addedTaskId + "*********************************************"
//                );

                Intent intent = new Intent(AddTask.this, MainActivity.class);
                TextView tasks = findViewById(R.id.textView12);
                tasksCounter++;
                tasks.setText(String.valueOf(tasksCounter));
                Toast.makeText(AddTask.this,"Task successfully added",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}