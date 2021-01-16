package com.todo.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "task_table")
public class Task {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String title;

    @TypeConverters(DateTypeConverter.class)
    private Date dueDate;

    private int priority;

    public Task(String title, Date dueDate, int priority) {
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
