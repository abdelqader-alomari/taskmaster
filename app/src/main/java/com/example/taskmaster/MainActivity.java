package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String user = sharedPreferences.getString("UserName","user");
        String team = sharedPreferences.getString("Team","no id");
        String teamName = sharedPreferences.getString("TeamName","noTeam");

        System.out.println(" Username : " + user);
        System.out.println("Team is : " + team);

        TextView welcome = findViewById(R.id.user);
        welcome.setText( user+"’s Tasks");
        TextView showTeam = findViewById(R.id.showTeam);
        showTeam.setText("Team: " + teamName);

        configureAmplify();
        createTeams();

        RecyclerView allTasksRecyclerView = findViewById(R.id.recyclerview);
        List<Task> tasks= new ArrayList<>();
        if(team == "noTeam"){
            tasks = getData(allTasksRecyclerView);
        }
        else{
            tasks = getTeamTasks(allTasksRecyclerView);
        }
        Log.i("omari",tasks.toString());
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allTasksRecyclerView.setAdapter(new TaskAdapter(tasks));

        Button addTask = MainActivity.this.findViewById(R.id.addTask);
        addTask.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddTask.class);
            startActivity(intent);
        });

        Button allTasks = MainActivity.this.findViewById(R.id.allTasks);
        allTasks.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AllTasks.class);
            startActivity(intent);
        });

        findViewById(R.id.settings).setOnClickListener(view -> {
            Intent settings = new Intent(MainActivity.this, SettingsPage.class);
            startActivity(settings);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String user = sharedPreferences.getString("UserName","user");
        String team = sharedPreferences.getString("Team","noTeam");
        System.out.println(user);
        TextView welcome = findViewById(R.id.user);
        welcome.setText( user+"’s Tasks");
    }

    private void configureAmplify() {
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i(TAG, "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e(TAG, "Could not initialize Amplify", error);
        }}

    private  List<Task> getData( RecyclerView allTaskDataRecyclerView ){
        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allTaskDataRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        List<Task> taskQuere=new ArrayList<>();
        Amplify.API.query(
                ModelQuery.list(Task.class),
                response -> {
                    for (Task task : response.getData()) {
                        taskQuere.add(task);
                        taskQuere.toString();
                        Log.i("MyAmplifyApp", taskQuere.toString());
                        Log.i("MyAmplifyApp", "Successful query, found tasks.");
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        return  taskQuere;
    }

    private  List<Task> getTeamTasks( RecyclerView allTaskDataRecyclerView ){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String team = sharedPreferences.getString("Team","none");
        System.out.println(team);
        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allTaskDataRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        List<Task> teamQuere=new ArrayList<>();
        Amplify.API.query(
                ModelQuery.list(Task.class,Task.TEAM_ID.contains(team)),
                response -> {
                    for (Task task : response.getData()) {
                        teamQuere.add(task);
                        teamQuere.toString();
                        Log.i("MyAmplifyApp", teamQuere.toString());
                        Log.i("MyAmplifyApp", "Successful query, found team tasks.");
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        return  teamQuere;
    }

    private void createTeams(){
        AtomicBoolean atomic= new AtomicBoolean(false);
        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    if(response.getData().getRequestForNextResult()==null){
                        System.out.println("response");
                        System.out.println(response.getData().getRequestForNextResult());
                        atomic.set(true);
                        Log.i("Teams", "Successful query, found teams.");
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        if(atomic.equals(false)){
            Team team1 = Team.builder()
                    .name("Team 1").id("1")
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(team1),
                    response -> Log.i("MyAmplifyApp", "Added Task with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );
            Team team2 = Team.builder()
                    .name("Team 2").id("2")
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(team2),
                    response -> Log.i("MyAmplifyApp", "Added Task with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );
            Team team3 = Team.builder()
                    .name("Team 3").id("3")
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(team3),
                    response -> Log.i("MyAmplifyApp", "Added Task with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );
        } }
}