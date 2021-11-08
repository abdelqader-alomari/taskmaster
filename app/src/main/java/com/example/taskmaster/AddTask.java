package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button addTask = findViewById(R.id.button4);
        addTask.setOnClickListener(new View.OnClickListener() {

            public int tasksCounter;
            @Override
            public void onClick(View view) {
                EditText taskTitleField = findViewById(R.id.taskTitleInput);
                String taskTitle = taskTitleField.getText().toString();

                EditText taskBodyField = findViewById(R.id.taskDescriptionInput);
                String taskBody = taskBodyField.getText().toString();

                EditText taskStateField = findViewById(R.id.taskStateInput);
                String taskState = taskStateField.getText().toString();

                Task task = new Task(taskTitle, taskBody, taskState);


               Long addedTaskId = AppDatabase.getInstance(getApplicationContext()).taskDao().insertTask(task);
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