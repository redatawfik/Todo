package com.todo.ui.tasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.todo.R;
import com.todo.data.Task;

public class TaskAdapter extends ListAdapter<Task, TaskAdapter.ViewHolder> {

    private TaskItemCallback itemCallback;

    protected TaskAdapter(@NonNull DiffUtil.ItemCallback<Task> diffCallback, TaskItemCallback itemCallback) {
        super(diffCallback);
        this.itemCallback = itemCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = getItem(position);
        holder.bind(task, itemCallback);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox title;
        private final TextView dueDate;
        private final TextView priority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            dueDate = itemView.findViewById(R.id.due_date);
            priority = itemView.findViewById(R.id.priority);

            title.setOnCheckedChangeListener((buttonView, isChecked) -> {

            });
        }

        static ViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.task_recyclerview_item, parent, false);
            return new ViewHolder(view);
        }

        public void bind(Task task, TaskItemCallback itemCallback) {
            title.setText(task.getTitle());
            title.setChecked(task.isCompleted());
            if (task.getDueDate() != null) {
                dueDate.setText(task.getDueDate().toString());
            } else {
                dueDate.setText("No date");
            }

            priority.setText(String.valueOf(task.getPriority()));
            title.setOnCheckedChangeListener((buttonView, isChecked) ->
                    itemCallback.checkBoxStatusChanged(isChecked, task));
        }
    }

    static class TaskDiff extends DiffUtil.ItemCallback<Task> {

        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDueDate().equals(newItem.getDueDate()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    }
}
