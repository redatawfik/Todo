package com.todo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface TaskDao {

    @Insert(onConflict = IGNORE)
    void insertTask(Task task);

    @Query("SELECT * from task_table")
    LiveData<List<Task>> getAllTasks();

    @Update
    void updateTask(Task task);

    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    @Delete
    void deleteTask(Task task);

    @Query("DELETE FROM task_table WHERE completed = true")
    void deleteCompletedTasks();
}
