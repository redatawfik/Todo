package com.todo.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class TaskRepository {

    private final TaskDao mTaskDao;
    private final LiveData<PagedList<Task>> mAllTasks;

    public TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getInstance(application);
        mTaskDao = db.taskDao();
        PagedList.Config myPagingConfig = new PagedList.Config.Builder()
                .setPageSize(30)
                .setInitialLoadSizeHint(50)
                .setPrefetchDistance(150)
                .setEnablePlaceholders(true)
                .build();

        mAllTasks = new LivePagedListBuilder<>(
                mTaskDao.getAllTasks(), myPagingConfig).build();
        //mAllTasks = mTaskDao.getAllTasks();
    }

    public LiveData<PagedList<Task>> getAllTasks() {
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

    public void deleteCompletedTasks() {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> mTaskDao.deleteCompletedTasks());
    }
}
