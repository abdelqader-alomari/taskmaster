package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
                Toast click = Toast.makeText(getApplicationContext(),"Submitted!",Toast.LENGTH_LONG);
                TextView tasks = findViewById(R.id.textView12);
                tasksCounter++;
                tasks.setText(String.valueOf(tasksCounter));
                click.show();
            }
        });
    }
}