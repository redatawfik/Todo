package com.todo.ui.tasks;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.todo.data.Task;
import com.todo.data.TaskRepository;

public class TasksViewModel extends AndroidViewModel {

    private final LiveData<PagedList<Task>> mAllTasks;
    private final TaskRepository mRepository;

    public TasksViewModel(Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getAllTasks();
    }

    public LiveData<PagedList<Task>> getAllTasks() {
        return mAllTasks;
    }

    public void insertTask(Task task) {
        mRepository.insertTask(task);
    }

    public void deleteCompletedTasks() {
        mRepository.deleteCompletedTasks();
    }

    public void updateTask(Task task) {
        mRepository.updateTask(task);
    }
}