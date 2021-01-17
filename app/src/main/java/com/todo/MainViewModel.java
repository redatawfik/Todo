package com.todo;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.todo.data.Task;
import com.todo.data.TaskRepository;

public class MainViewModel extends AndroidViewModel {

    private final TaskRepository mRepository;

    public MainViewModel(Application application) {
        super(application);
        mRepository = new TaskRepository(application);
    }

    public void deleteTasks(Task task) {
        mRepository.insertTask(task);
    }
}
