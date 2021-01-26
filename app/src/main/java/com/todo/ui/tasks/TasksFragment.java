package com.todo.ui.tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.todo.R;
import com.todo.data.Task;
import com.todo.databinding.FragmentTasksBinding;
import com.todo.ui.taskdetail.TaskDetailFragment;

public class TasksFragment extends Fragment implements TaskItemCallback {

    private TasksViewModel mTasksViewModel;
    private FragmentTasksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        binding = FragmentTasksBinding.inflate(getLayoutInflater());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.nav_task_detail);
            }
        });

        mTasksViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);

        RecyclerView recyclerView = binding.tasksRecyclerView;
        final TaskAdapter adapter = new TaskAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mTasksViewModel.getAllTasks().observe(getViewLifecycleOwner(), adapter::submitList);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.tasks_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_clear_completed_tasks) {
            mTasksViewModel.deleteCompletedTasks();
            Snackbar.make(binding.getRoot(), "Removed completed tasks", Snackbar.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void checkBoxStatusChanged(boolean completed, Task task) {
        task.setCompleted(completed);
        mTasksViewModel.updateTask(task);
        Snackbar.make(binding.getRoot(), "Task: " + task.getTitle() +
                (completed ? " completed" : " not completed"), Snackbar.LENGTH_LONG).show();
    }
}