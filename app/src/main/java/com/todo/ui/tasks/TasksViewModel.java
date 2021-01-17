package com.todo.ui.tasks;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.todo.data.Task;
import com.todo.data.TaskRepository;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {

    private final LiveData<List<Task>> mAllTasks;
    private TaskRepository mRepository;

    public TasksViewModel(Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
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