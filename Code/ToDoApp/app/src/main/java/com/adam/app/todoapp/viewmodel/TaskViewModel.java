/**
 * TaskViewModel: ViewModel for Task
 * Description: ViewModel class for Task
 * Date: 2023-05-27
 */
package com.adam.app.todoapp.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.adam.app.todoapp.model.Task;
import com.adam.app.todoapp.model.TaskDao;
import com.adam.app.todoapp.model.TaskDatabase;
import com.adam.app.todoapp.model.TaskFilter;
import com.adam.app.todoapp.repository.ITaskRepository;
import com.adam.app.todoapp.repository.TaskRepositoryImpl;

import java.util.List;

public class TaskViewModel extends ViewModel {
    // task repository
    private ITaskRepository mTaskRepository;
    // MutableLiveData
    private MutableLiveData<TaskFilter> mFilterLiveData = new MutableLiveData<>(TaskFilter.ALL);;
    // LiveData for task list
    private LiveData<List<Task>> mFilteredTasks;

    // constructor with task repository
    public TaskViewModel(Context context) {
        TaskDao taskDao = TaskDatabase.getInstance(context).taskDao();
        mTaskRepository = new TaskRepositoryImpl(taskDao);
    }

    // get tasks
    public LiveData<List<Task>> getTasks() {
        return mFilteredTasks = Transformations.switchMap(
                mFilterLiveData,
                filter -> {
                    if (filter == TaskFilter.ALL) {
                        return mTaskRepository.getAllTasks();
                    } else if (filter == TaskFilter.INCOMPLETE) {
                        return mTaskRepository.getTasksByStatus(false);
                    } else {
                        return mTaskRepository.getTasksByStatus(true);
                    }
                }
        );
    }

    // set filter
    public void setFilter(TaskFilter filter) {
        mFilterLiveData.setValue(filter);
    }

    // insert task
    public void insertTask(Task task) {
        mTaskRepository.insertTask(task);
    }

    // update task
    public void updateTask(Task task) {
        mTaskRepository.updateTask(task);
    }

    // delete task
    public void deleteTask(Task task) {
        mTaskRepository.deleteTask(task);
    }

    // delete task by id
    public void deleteTaskById(int id) {
        mTaskRepository.deleteTaskById(id);
    }


}
