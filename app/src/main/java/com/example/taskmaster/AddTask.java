package com.example.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

public class AddTask extends AppCompatActivity {

    private static final String TAG = "AddTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button addTask = AddTask.this.findViewById(R.id.button4);
        addTask.setOnClickListener(view -> {
            EditText taskTitle = findViewById(R.id.taskTitleInput);
            String title = taskTitle.getText().toString();

            EditText taskBody = findViewById(R.id.taskDescriptionInput);
            String body = (taskBody.getText().toString());

            EditText taskState = findViewById(R.id.taskStateInput);
            String state = (taskState.getText().toString());

            RadioButton team1 = findViewById(R.id.radioButton1);
            RadioButton team2 = findViewById(R.id.radioButton2);
            RadioButton team3 = findViewById(R.id.radioButton3);

            String id = null;
            if (team1.isChecked()) {
                id = "1";
            } else if (team2.isChecked()) {
                id = "2";
            } else if (team3.isChecked()) {
                id = "3";
            }
            dataStore(title, body, state, id);

            System.out.println(" Task Title: " + title);
            System.out.println(" Task Body: " + body);
            System.out.println(" Task State: " + state);

            Intent intent = new Intent(AddTask.this, MainActivity.class);
            startActivity(intent);
        });
    }
    private void dataStore(String title, String body, String state, String id) {
        Task task = Task.builder().teamId(id).title(title).body(body).state(state).build();

        Amplify.API.mutate(ModelMutation.create(task), succuess -> {
            Log.i(TAG, "Saved to DYNAMODB");
        }, error -> {
            Log.i(TAG, "error saving to DYNAMODB");
        });
    }
}
