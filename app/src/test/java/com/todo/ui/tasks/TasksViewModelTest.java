package com.todo.ui.tasks;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.todo.data.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.Date;

@RunWith(AndroidJUnit4.class)
public class TasksViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TasksViewModel mTasksViewModel;

    @Mock
    Observer<PagedList<Task>> observer;

    Task task = new Task("first task", new Date(), 1, false);

    @Before
    public void setUp() {
        mTasksViewModel = new TasksViewModel(ApplicationProvider.getApplicationContext());
       // mTasksViewModel.getAllTasks().observeForever(observer);
    }

    @After
    public void cleanUp() {
      //  mTasksViewModel.getAllTasks().removeObserver(observer);
    }

    @Test
    public void testtt() {
      //  mTasksViewModel.insertTask(task);

    }
}