package com.adam.app.todoapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks ORDER BY created_at DESC")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM tasks WHERE is_completed = :isCompleted ORDER BY created_at DESC")
    LiveData<List<Task>> getTasksByStatus(boolean isCompleted);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Update
    void updateTask(Task task);

}
