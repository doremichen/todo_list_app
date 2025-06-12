package com.adam.app.todoapp.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
    public static final String DATABASE_NAME = "task_database";
    private static TaskDatabase sInstance;

    public static synchronized TaskDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    TaskDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return sInstance;
    }
}
