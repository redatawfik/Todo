package com.todo.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;

import java.util.List;

public class TaskRepository {

    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTasks;

    public TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getInstance(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    public void insertTask(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> mTaskDao.insertTask(task));
    }

    public void updateTask(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> mTaskDao.updateTask(task));
    }

    public void deleteAllTasks() {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> mTaskDao.deleteAllTasks());
    }

    public void deleteTask(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> mTaskDao.deleteTask(task));
    }
}
