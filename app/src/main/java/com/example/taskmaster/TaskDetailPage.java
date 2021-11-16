package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.squareup.picasso.Picasso;

public class TaskDetailPage extends AppCompatActivity {
    private String fileURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_page);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String title = sharedPreferences.getString("task_title","task_title");
        String description = sharedPreferences.getString("task_description","description");
        String state = sharedPreferences.getString("task_state","state");
        String filename= sharedPreferences.getString("task_imgUrl","image");

        TextView welcome = findViewById(R.id.title_detail);
        TextView task_description = findViewById(R.id.task_description);
        TextView task_state = findViewById(R.id.task_state);
        welcome.setText("Task Title: " + title);
        task_description.setText("Task Description: " + description);
        task_state.setText("Task State: " + state);

        TextView fileLinkDetail = findViewById(R.id.fileLinkDetail);
        fileLinkDetail.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(fileURL));
            startActivity(i);
        });
        System.out.println(filename);



        if (filename!= null) {

            Amplify.Storage.getUrl(
                    filename,
                    result -> {
                        Log.i("MyAmplifyApp", "Successfully generated: " + result.getUrl());
                        runOnUiThread(() -> {
                            if (filename.endsWith("png")
                                    || filename.endsWith("jpg")
                                    || filename.endsWith("jpeg")
                                    || filename.endsWith("gif")) {
                                ImageView taskImageDetail = findViewById(R.id.taskImageDetail);
                                System.out.println(result.getUrl());
                                Picasso.get().load(String.valueOf(result.getUrl())).into(taskImageDetail);

                                taskImageDetail.setVisibility(View.VISIBLE);
                            }else{
                                fileURL = String.valueOf(result.getUrl());
                                fileLinkDetail.setVisibility(View.VISIBLE);
                            }
                        });
                    },
                    error -> Log.e("MyAmplifyApp", "URL generation failure", error)
            );
        }

    }

}
