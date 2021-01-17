package com.todo.ui.tasks;

import com.todo.data.Task;

public interface TaskItemCallback {
    void checkBoxStatusChanged(boolean value, Task task);
}
