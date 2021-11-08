package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    ArrayList<Tasks> allTasksData = new ArrayList<Tasks>();

    public TaskAdapter(ArrayList<Tasks> allTasksData) {
        this.allTasksData = allTasksData;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        public Tasks task;
        public TextView taskTitle;
        public TextView taskBody;
        public TextView taskState;
        public ConstraintLayout constraintLayout;

        public TaskViewHolder(View taskView) {
            super(taskView);
            taskTitle = taskView.findViewById(R.id.tTitle);
            taskBody = taskView.findViewById(R.id.tBody);
            taskState = taskView.findViewById(R.id.tState);
            constraintLayout= taskView.findViewById(R.id.ConstraintLayout);
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent,false);
        return new TaskViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.task = allTasksData.get(position);
        TextView title = holder.itemView.findViewById(R.id.tTitle);
        TextView body = holder.itemView.findViewById(R.id.tBody);
        TextView state = holder.itemView.findViewById(R.id.tState);
        ConstraintLayout constraintLayout = holder.itemView.findViewById(R.id.ConstraintLayout);
        title.setText(holder.task.title);
        body.setText(holder.task.body);
        state.setText(holder.task.state);

        Context context = holder.itemView.getContext();
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TaskDetailPage.class);
                intent.putExtra("title", holder.taskTitle.getText().toString());
                intent.putExtra("body", holder.taskBody.getText().toString());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return allTasksData.size();
    }

}
