package com.todo.ui.taskdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.android.material.snackbar.Snackbar;
import com.todo.R;
import com.todo.data.Task;
import com.todo.databinding.FragmentTaskDetailBinding;
import com.todo.notification.NotificationWorker;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.todo.Constants.KEY_TASK_TITLE;

public class TaskDetailFragment extends Fragment {

    private TaskDetailViewModel mTaskDetailViewModel;
    private FragmentTaskDetailBinding binding;
    private Date mDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mTaskDetailViewModel =
                new ViewModelProvider(this).get(TaskDetailViewModel.class);

        binding = FragmentTaskDetailBinding.inflate(getLayoutInflater());

        binding.addTaskButton.setOnClickListener(view -> addTask());

        return binding.getRoot();
    }

    private void addTask() {
        String title = binding.titleInput.getText().toString();

        int priority = 1;
        if (binding.mediumPriorityInput.isChecked()) {
            priority = 2;
        } else if (binding.lowPriorityInput.isChecked()) {
            priority = 3;
        }

        mDate = null;
        if (binding.selectDateInput.isChecked()) {
            mDate = new Date();
            int hour = binding.datePicker.getHour();
            int minute = binding.datePicker.getMinute();
            mDate.setHours(hour);
            mDate.setMinutes(minute);
        }

        Task task = new Task(title, mDate, priority, false);
        mTaskDetailViewModel.insertTask(task);

        Navigation.findNavController(binding.getRoot()).navigate(R.id.nav_tasks);

        scheduleReminder(title);
        showSnackBar(title);
    }

    private void showSnackBar(String title) {
        Snackbar snackbar = Snackbar
                .make(binding.getRoot(), title, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void scheduleReminder(String title) {
        if (mDate == null) return;

        long diffInMillies = Math.abs(mDate.getTime() - new Date().getTime());
        long diffInMinutes = diffInMillies / (1000 * 60);

        Data inputDate = new Data.Builder()
                .putString(KEY_TASK_TITLE, title)
                .build();

        OneTimeWorkRequest request =
                new OneTimeWorkRequest.Builder(NotificationWorker.class)
                        .setInitialDelay(diffInMinutes, TimeUnit.MINUTES)
                        .setInputData(inputDate)
                        .build();

        WorkManager
                .getInstance(binding.getRoot().getContext())
                .enqueue(request);
    }
}