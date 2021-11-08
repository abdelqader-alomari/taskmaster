package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("Select * FROM Tasks")
    List<Tasks> getAll();

    @Query("Select * FROM Tasks WHERE id = :id")
    Tasks getTaskById(long id);

    @Insert
    Long insertTask(Tasks task);
}
