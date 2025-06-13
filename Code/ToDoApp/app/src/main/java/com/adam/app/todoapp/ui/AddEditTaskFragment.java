/**
 * AddEditTaskFragment: Fragment for AddEditTaskFragment
 * Description: Fragment class for AddEditTaskFragment
 * Date: 2023-05-27
 */
package com.adam.app.todoapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.adam.app.todoapp.Utils;
import com.adam.app.todoapp.model.Task;
import com.adam.app.todoapp.viewmodel.TaskViewModel;
import com.med.app.todoapp.R;

/**
 *
 */
public class AddEditTaskFragment extends Fragment {
    // title edit text
    private EditText mTitleEditText;
    // description edit text
    private EditText mDescriptionEditText;
    // completed checkbox
    private CheckBox mCompletedCheckBox;
    // save button
    private Button mSaveButton;

    // Task view model
    private TaskViewModel mTaskViewModel;
    // Current Task
    private Task mCurrentTask;

    // constant ARG_TASK_ID
    public static final String ARG_TASK_ID = "task_id";

    // constructor
    public AddEditTaskFragment() {
        // Required empty public constructor
    }

    // onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_edit_task, container, false);
    }

    // onViewCreate
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initial view
        mTitleEditText = view.findViewById(R.id.edit_text_title);
        mDescriptionEditText = view.findViewById(R.id.edit_text_description);
        mCompletedCheckBox = view.findViewById(R.id.checkbox_completed);
        mSaveButton = view.findViewById(R.id.button_save_task);

        //initial view model
        mTaskViewModel = new ViewModelProvider(
                requireActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())
                ).get(TaskViewModel.class);

        // Show title, description, and completed status if edit mode
        Bundle args = getArguments();
        if (args != null && args.containsKey(ARG_TASK_ID)) {
            int taskId = args.getInt(ARG_TASK_ID);
            mTaskViewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> {
                for (Task task : tasks) {
                    // edit task by id
                    if (task.getId() == taskId) {
                        mCurrentTask = task;
                        mTitleEditText.setText(task.getTitle());
                        mDescriptionEditText.setText(task.getDescription());
                        mCompletedCheckBox.setChecked(task.getIsCompleted());
                        break;
                    }
                }
            });
        }

        // Save button click listener
        mSaveButton.setOnClickListener(v -> {
            String title = mTitleEditText.getText().toString();
            String description = mDescriptionEditText.getText().toString();
            boolean isCompleted = mCompletedCheckBox.isChecked();

            // Show toast if title is empty
            if (title.isEmpty()) {
                //show toast
                Utils.showToast(AddEditTaskFragment.this.getContext(), "title is empty");
                return;
            }

            // When mCurrentTask is null, it means it's a new task
            if (mCurrentTask == null) {
                // Create a new task
                Task newTask = new Task();
                newTask.setTitle(title);
                newTask.setDescription(description);
                newTask.setIsCompleted(isCompleted);
                // Insert new task
                mTaskViewModel.insertTask(newTask);
            } else {
                // Update existing task
                mCurrentTask.setTitle(title);
                mCurrentTask.setDescription(description);
                mCurrentTask.setIsCompleted(isCompleted);
                // Update task
                mTaskViewModel.updateTask(mCurrentTask);
            }

            // show toast
            Utils.showToast(AddEditTaskFragment.this.getContext(), "Task saved");

            // Navigate back to TaskListFragment
            requireActivity().getSupportFragmentManager().popBackStack();
        });

    }
}