package com.adam.app.todoapp.model;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    // from timestamp to date
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    // from date to timestamp
    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
