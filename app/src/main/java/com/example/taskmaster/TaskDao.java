//package com.example.taskmaster;
//
//import androidx.room.Dao;
//import androidx.room.Insert;
//import androidx.room.Query;
//
//import java.util.List;
//
//@Dao
//public interface TaskDao {
//
//    @Query("Select * FROM task")
//    List<Task> getAll();
//
//    @Query("Select * FROM task WHERE id = :id")
//    Task getTaskById(long id);
//
//    @Insert
//    Long insertTask(Task task);
//}
