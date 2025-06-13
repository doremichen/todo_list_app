package com.adam.app.todoapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.adam.app.todoapp.model.Task;
import com.adam.app.todoapp.model.TaskDao;
import com.adam.app.todoapp.model.TaskDatabase;
import com.adam.app.todoapp.model.TaskFilter;
import com.adam.app.todoapp.repository.ITaskRepository;
import com.adam.app.todoapp.repository.TaskRepositoryImpl;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private final ITaskRepository mTaskRepository;
    private final MutableLiveData<TaskFilter> mFilterLiveData = new MutableLiveData<>(TaskFilter.ALL);
    private final LiveData<List<Task>> mFilteredTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        TaskDao taskDao = TaskDatabase.getInstance(application).taskDao();
        mTaskRepository = new TaskRepositoryImpl(taskDao);

        // Initail mFilteredTasks with default value
        mFilteredTasks = Transformations.switchMap(
                mFilterLiveData,
                filter -> {
                    switch (filter) {
                        case INCOMPLETE:
                            return mTaskRepository.getTasksByStatus(false);
                        case COMPLETED:
                            return mTaskRepository.getTasksByStatus(true);
                        case ALL:
                        default:
                            return mTaskRepository.getAllTasks();
                    }
                }
        );
    }

    public LiveData<List<Task>> getTasks() {
        return mFilteredTasks;
    }

    public void setFilter(TaskFilter filter) {
        mFilterLiveData.setValue(filter);
    }

    public void insertTask(Task task) {
        mTaskRepository.insertTask(task);
    }

    public void updateTask(Task task) {
        mTaskRepository.updateTask(task);
    }

    public void deleteTask(Task task) {
        mTaskRepository.deleteTask(task);
    }

    public void deleteTaskById(int id) {
        mTaskRepository.deleteTaskById(id);
    }
}
