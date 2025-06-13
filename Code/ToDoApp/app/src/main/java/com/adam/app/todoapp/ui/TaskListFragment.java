package com.adam.app.todoapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.adam.app.todoapp.Utils;
import com.adam.app.todoapp.model.Task;
import com.adam.app.todoapp.model.TaskFilter;
import com.adam.app.todoapp.viewmodel.TaskViewModel;
import com.med.app.todoapp.R;
import com.med.app.todoapp.databinding.FragmentTaskListBinding;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * interface menu item
     */
    interface MenuItem {
        void onClick();
    }

    /**
     * menu item: All
     */
    class ALL implements MenuItem {
        @Override
        public void onClick() {
            // log
            Utils.info("All");
            // set filter
            mViewModel.setFilter(TaskFilter.ALL);
        }
    }

    /**
     * menu item: Completed
     */
    class COMPLETED implements MenuItem {
        @Override
        public void onClick() {
            // log
            Utils.info("Completed");
            // set filter
            mViewModel.setFilter(TaskFilter.COMPLETED);
        }
    }

    /**
     * menu item: Active
     */
    class ACTIVE implements MenuItem {
        @Override
        public void onClick() {
            // log
            Utils.info("Active");
            // set filter
            mViewModel.setFilter(TaskFilter.INCOMPLETE);
        }
    }

    /**
     * map menu item
     * key: resId
     * value: MenuItem
     */
    private final Map<Integer, MenuItem> mMenuItemMap = new HashMap<>();

    // build map
    {
        mMenuItemMap.put(R.id.action_filter_all, new ALL());
        mMenuItemMap.put(R.id.action_filter_completed, new COMPLETED());
        mMenuItemMap.put(R.id.action_filter_active, new ACTIVE());
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
                    // navigate to AddEditTaskFragment
                    NavController navController = NavHostFragment.findNavController(this);
                    navController.navigate(R.id.action_taskListFragment_to_addEditTaskFragment);
                }
        );

        // set menu item click listener
        mBinding.toolbar.setOnMenuItemClickListener(
                item -> {
                        // from map get menu item
                        MenuItem menuItem = mMenuItemMap.get(item.getItemId());
                        if (menuItem != null) {
                            menuItem.onClick();
                        }
                    return true;
                }
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
        // navigate to AddEditTaskFragment
        Bundle args = new Bundle();
        args.putInt(AddEditTaskFragment.ARG_TASK_ID, task.getId());
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_taskListFragment_to_addEditTaskFragment, args);

    }

    @Override
    public void onItemLongClick(Task task) {
        Utils.ButtonContent postiveButton = new Utils.ButtonContent("Yes", (dialog, which) -> {
            mViewModel.deleteTask(task);
        });
        Utils.ButtonContent negativeButton = new Utils.ButtonContent("No", null);
        // Show delete confirmation dialog
        Utils.showDialog(requireContext(),
                "Delete Task",
                "Are you sure you want to delete this task?",
                postiveButton,
                negativeButton);
    }
}
