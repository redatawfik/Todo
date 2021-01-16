package com.todo.ui.tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.todo.R;
import com.todo.data.Task;

import java.util.Date;

public class TasksFragment extends Fragment {

    private TasksViewModel mTasksViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mTasksViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);

        View root = inflater.inflate(R.layout.fragment_tasks, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.tasks_recyclerView);
        final TaskAdapter adapter = new TaskAdapter(new TaskAdapter.TaskDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        mTasksViewModel.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            Toast.makeText(getContext(), tasks != null ? String.valueOf(tasks.size()) : "empty list", Toast.LENGTH_SHORT).show();

            adapter.submitList(tasks);
        });
        return root;
    }
}