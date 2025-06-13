package com.adam.app.todoapp.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.adam.app.todoapp.model.Task;
import com.med.app.todoapp.databinding.ItemTaskBinding;

public class TaskListAdapter extends ListAdapter<Task, TaskListAdapter.TaskViewHolder> {

    private static final DiffUtil.ItemCallback<Task> DIFF_CALLBACK = new DiffUtil.ItemCallback<Task>() {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.equals(newItem);
        }
    };

    public interface OnTaskItemListener {
        void onCheckChanged(Task task, boolean isChecked);
        void onItemClick(Task task);
        void onItemLongClick(Task task);
    }

    // task item listener
    private final OnTaskItemListener mTaskItemListener;

    protected TaskListAdapter(OnTaskItemListener listener) {
        super(DIFF_CALLBACK);
        mTaskItemListener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate item task binding
        ItemTaskBinding binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false);

        return new TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        // bind task
        holder.bind(getItem(position));
    }

    // TaskViewHolder class extend RecyclerView.ViewHolder
    class TaskViewHolder extends RecyclerView.ViewHolder {

        // item task binding
        private final ItemTaskBinding mBinding;


        public TaskViewHolder(ItemTaskBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        // bind task
        public void bind(Task task) {
            mBinding.checkBoxCompleted.setChecked(task.getIsCompleted());
            mBinding.textViewTitle.setText(task.getTitle());
            mBinding.textViewDescription.setText(task.getDescription());

            // set check box check change listener
            mBinding.checkBoxCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
                // callback
                mTaskItemListener.onCheckChanged(task, isChecked);
                // update task
                updateTask(task, isChecked);
            });

            // set click listener
            mBinding.getRoot().setOnClickListener(view -> {
                // callback
                mTaskItemListener.onItemClick(task);

            });
            
            // set long click listener
            mBinding.getRoot().setOnLongClickListener(view -> {
                // callback
                mTaskItemListener.onItemLongClick(task);

                // delete task
                deleteTask(task);
                return true;
            });
        }

    }

    private void deleteTask(Task task) {
    }

    private void updateTask(Task task, boolean isChecked) {
    }
}
