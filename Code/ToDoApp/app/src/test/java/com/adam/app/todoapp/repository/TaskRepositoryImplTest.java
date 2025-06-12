package com.adam.app.todoapp.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.adam.app.todoapp.model.Task;
import com.adam.app.todoapp.model.TaskDao;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

public class TaskRepositoryImplTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    // mock dao
    @Mock
    private TaskDao mTaskDao;
    // repository
    private ITaskRepository mTaskRepository;

    @Before
    public void setup() {
        mTaskDao = mock(TaskDao.class);
        mTaskRepository = new TaskRepositoryImpl(mTaskDao);
    }

    @Test
    public void testInsertTask_shouldCallDaoInsertMethod() {
        // given
        Task task = new Task();
        // when
        mTaskRepository.insertTask(task);
        // then
        verify(mTaskDao, timeout(1000L)).insertTask(task);
    }

    @Test
    public void testUpdateTask_shouldCallDaoUpdateMethod() {
        // given
        Task task = new Task();
        // when
        mTaskRepository.updateTask(task);
        // then
        verify(mTaskDao).updateTask(task);
    }

    @Test
    public void testDeleteTask_shouldCallDaoDeleteMethod() {
        // given
        Task task = new Task();
        // when
        mTaskRepository.deleteTask(task);
        // then
        verify(mTaskDao, timeout(1000L)).deleteTask(task);
    }

    @Test
    public void testDeleteTaskById_shouldCallDaoDeleteMethod() {
        // given
        int id = 1;
        // when
        mTaskRepository.deleteTaskById(id);

        // then
        verify(mTaskDao, timeout(1000L)).deleteTaskById(id);
    }

    @Test
    public void testGetAllTasks_shouldReturnLiveData() {
        // given
        MutableLiveData<List<Task>> dummyData = new MutableLiveData<>();
        // when
        when(mTaskDao.getAllTasks()).thenReturn(dummyData);
        LiveData<List<Task>> result = mTaskRepository.getAllTasks();
        // then assert result
        assert result == dummyData;
    }
}
