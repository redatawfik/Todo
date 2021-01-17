package com.todo.ui.taskdetail;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.todo.data.Task;
import com.todo.data.TaskRepository;

public class TaskDetailViewModel extends AndroidViewModel {

    private final TaskRepository mRepository;

    public TaskDetailViewModel(Application application) {
        super(application);
        mRepository = new TaskRepository(application);
    }

    public void insertTask(Task task) {
        mRepository.insertTask(task);
    }
}