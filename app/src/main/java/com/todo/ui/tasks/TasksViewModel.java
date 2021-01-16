package com.todo.ui.tasks;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.todo.data.Task;
import com.todo.data.TaskRepository;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {

    private TaskRepository mRepository;
    private final LiveData<List<Task>> mAllTasks;

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
}