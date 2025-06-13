package com.adam.app.todoapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.adam.app.todoapp.model.Task;
import com.adam.app.todoapp.viewmodel.TaskViewModel;
import com.med.app.todoapp.databinding.FragmentTaskListBinding;

public class TaskListFragment extends Fragment implements TaskListAdapter.OnTaskItemListener {

    // fragment task list binding
    private FragmentTaskListBinding mBinding;
    // task list adapter
    private TaskListAdapter mAdapter;
    // task view model
    private TaskViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentTaskListBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initial view model
        mViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        // initial recycler view
        mAdapter = new TaskListAdapter(this);
        mBinding.recyclerViewTasks.setAdapter(mAdapter);
        // set layout manager
        mBinding.recyclerViewTasks.setLayoutManager(new LinearLayoutManager(requireContext()));
        // observe tasks
        mViewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> {
            mAdapter.submitList(tasks);
        });
        // add floating button click listener
        mBinding.fabAddTask.setOnClickListener(
                v -> {
                    // TODO: Show Add Task Dialog or navigate to AddTaskFragment
                }
//                v -> requireActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, new TaskDetailFragment())
//                        .addToBackStack(null)
//                        .commit()
        );

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null; // avoid memory leak
    }

    @Override
    public void onCheckChanged(Task task, boolean isChecked) {
        task.setIsCompleted(isChecked);
        mViewModel.updateTask(task);

    }

    @Override
    public void onItemClick(Task task) {
        // TODO: Navigate to edit screen

    }

    @Override
    public void onItemLongClick(Task task) {
        // TODO: Show delete confirmation dialog
        mViewModel.deleteTask(task);
    }
}
