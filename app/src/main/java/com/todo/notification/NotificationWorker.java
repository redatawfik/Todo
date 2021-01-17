package com.todo.notification;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static com.todo.Constants.KEY_TASK_TITLE;

public class NotificationWorker extends Worker {

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Context applicationContext = getApplicationContext();

        String taskTitle = getInputData().getString(KEY_TASK_TITLE);

        NotificationUtils.makeStatusNotification(taskTitle, applicationContext);

        return Result.success();
    }
}
