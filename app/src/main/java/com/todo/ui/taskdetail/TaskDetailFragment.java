package com.todo.ui.taskdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.todo.R;
import com.todo.data.Task;
import com.todo.databinding.FragmentTaskDetailBinding;
import com.todo.ui.tasks.TasksFragment;

import java.util.Date;

public class TaskDetailFragment extends Fragment {

    private TaskDetailViewModel mTaskDetailViewModel;
    private FragmentTaskDetailBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mTaskDetailViewModel =
                new ViewModelProvider(this).get(TaskDetailViewModel.class);

        binding = FragmentTaskDetailBinding.inflate(getLayoutInflater());

        binding.addTaskButton.setOnClickListener(view -> addTask());
//        View root = inflater.inflate(R.layout.fragment_task_detail, container, false);
//
//
//        final Button addTaskButton = root.findViewById(R.id.add_task_button);
//        addTaskButton.setOnClickListener(view -> {
//            addTask(root);
//        });


        //final TextView textView = root.findViewById(R.id.text_task_detail);
//        mTaskDetailViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//               // textView.setText(s);
//            }
//        });
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

        Date date = null;
        if (binding.selectDateInput.isChecked()) {
            date = new Date();
            int hour = binding.datePicker.getHour();
            int minute = binding.datePicker.getMinute();
            date.setHours(hour);
            date.setMinutes(minute);
        }

        Task task = new Task(title, date, priority);
        mTaskDetailViewModel.insertTask(task);

       // action =
        Navigation.findNavController(binding.getRoot()).navigate(R.id.nav_tasks);
        //Navigation.createNavigateOnClickListener(R.id.action, null);
    }
}