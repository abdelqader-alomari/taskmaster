package com.example.taskmaster;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amplifyframework.analytics.AnalyticsEvent;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class AddTask extends AppCompatActivity {

    String img="";
    private static final String TAG = "AddTask";
    private String uploadedFileNames;
    ActivityResultLauncher<Intent> someActivityResultLauncher;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        recordEvent();

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        try {
                            onChooseFile(result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

        findViewById(R.id.uploadImg).setOnClickListener(view -> {
            Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.setType("*/*");
            chooseFile = Intent.createChooser(chooseFile, "Choose a file");
            someActivityResultLauncher.launch(chooseFile);
        });


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
    private void dataStore(String title, String body, String state, String id)
    {
        String fileNameIfThere = uploadedFileNames == null ? "" : uploadedFileNames;
        Task task = Task.builder().teamId(id).title(title).body(body).state(state).imgUrl(fileNameIfThere).build();

        Amplify.API.mutate(ModelMutation.create(task), succuess -> {
            Log.i(TAG, "Saved to DYNAMODB");
        }, error -> {
            Log.i(TAG, "error saving to DYNAMODB");
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void onChooseFile(ActivityResult activityResult) throws IOException {

        Uri uri = null;
        if (activityResult.getData() != null) {
            uri = activityResult.getData().getData();
        }
        assert uri != null;
        String uploadedFileName = new Date().toString() + "." + getMimeType(getApplicationContext(), uri);

        File uploadFile = new File(getApplicationContext().getFilesDir(), "uploadFile");

        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            FileUtils.copy(inputStream, new FileOutputStream(uploadFile));
        } catch (Exception exception) {
            Log.e("onChooseFile", "onActivityResult: file upload failed" + exception.toString());
        }

        Amplify.Storage.uploadFile(
                uploadedFileName,
                uploadFile,
                success -> Log.i("onChooseFile", "uploadFileToS3: succeeded " + success.getKey()),
                error -> Log.e("onChooseFile", "uploadFileToS3: failed " + error.toString())
        );
        uploadedFileNames= uploadedFileName;
    }
    public static String getMimeType(Context context, Uri uri) {
        String extension;

        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
        } else {
            extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());

        }

        return extension;
    }

    private boolean permissionGranted(){
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
    }
    private void recordEvent(){
        AnalyticsEvent event = AnalyticsEvent.builder()
                .name("Launch AddTask activity")
                .addProperty("Channel", "SMS")
                .addProperty("Successful", true)
                .addProperty("ProcessDuration", 792)
                .addProperty("UserAge", 120.3)
                .build();

        Amplify.Analytics.recordEvent(event);
    }
}
