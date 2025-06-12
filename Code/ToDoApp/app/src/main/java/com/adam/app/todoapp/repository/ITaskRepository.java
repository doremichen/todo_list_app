/**
 *  TaskRepository: Interface for TaskRepository
 *  Description: Interface for TaskRepository
 *  Date: 2023-05-27
 */
package com.adam.app.todoapp.repository;

import androidx.lifecycle.LiveData;

import com.adam.app.todoapp.model.Task;

import java.util.List;

/**
 * insert(task)
 * update(task)
 * delete(task)
 * getAllTasks()
 * getTasksByStatus(isCompleted)
 */
public interface ITaskRepository {
    void insertTask(Task task);
    void updateTask(Task task);
    void deleteTask(Task task);
    void deleteTaskById(int id);
    LiveData<List<Task>> getAllTasks();
    LiveData<List<Task>> getTasksByStatus(boolean isCompleted);
}
