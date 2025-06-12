package com.adam.app.todoapp.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.adam.app.todoapp.model.Task;
import com.adam.app.todoapp.model.TaskDao;
import com.adam.app.todoapp.model.TaskDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Singleton
 * Used to implement TaskRepository
 */
public class TaskRepositoryImpl implements ITaskRepository {

    // taskDao
    private TaskDao mTaskDao;
    // executor service
    private ExecutorService mExecutorService;

    // constructor with task dao
    public TaskRepositoryImpl(TaskDao taskDao) {
        mTaskDao = taskDao;
        mExecutorService = Executors.newSingleThreadExecutor();
    }


    @Override
    public void insertTask(Task task) {
        mExecutorService.execute(() -> mTaskDao.insertTask(task));
    }

    @Override
    public void updateTask(Task task) {
        mExecutorService.execute(() -> mTaskDao.updateTask(task));
    }

    @Override
    public void deleteTask(Task task) {
        mExecutorService.execute(() -> mTaskDao.deleteTask(task));
    }

    @Override
    public void deleteTaskById(int id) {
        mExecutorService.execute(() -> mTaskDao.deleteTaskById(id));
    }

    @Override
    public LiveData<List<Task>> getAllTasks() {
        return mTaskDao.getAllTasks();
    }

    @Override
    public LiveData<List<Task>> getTasksByStatus(boolean isCompleted) {
        return mTaskDao.getTasksByStatus(isCompleted);
    }
}
