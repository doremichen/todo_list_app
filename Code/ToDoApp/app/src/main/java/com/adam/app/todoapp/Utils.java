package com.adam.app.todoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

public abstract class Utils {
    // TAG
    private static final String TAG = "ToDoApp";

    // info
    public static void info(String message) {
        Log.i(TAG, message);
    }

    // error
    public static void error(String message) {
        Log.e(TAG, message);
    }

    // show toast
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Button content of dialog class
     * label: string
     * listener: DialogInterface.OnClickListener
     */
    public static class ButtonContent {
        // label
        private String mLabel;
        // listener
        private DialogInterface.OnClickListener mListener;
        // constructor
        public ButtonContent(String label, DialogInterface.OnClickListener listener) {
            this.mLabel = label;
            this.mListener = listener;
        }
        // getter
        public String getLabel() {
            return mLabel;
        }

        public DialogInterface.OnClickListener getListener() {
            return mListener;
        }

    }



    /**
     * Show dialog
     * @param context
     * @param title
     * @param message
     * @param positiveButton
     * @param negativeButton
     */
    public static void showDialog(Context context,
                                  String title,
                                  String message,
                                  ButtonContent positiveButton,
                                  ButtonContent negativeButton) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton.getLabel(), positiveButton.getListener())
                .setNegativeButton(negativeButton.getLabel(), negativeButton.getListener())
                .show();
    }
}
