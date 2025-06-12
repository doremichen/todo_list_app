package com.adam.app.todoapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * id: int
 * title: string
 * description: string
 * isCompleted: boolean
 * createdAt: Date
 * updatedAt: Date
 */
@Entity(tableName = "tasks")
public class Task {
    // id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;
    // title
    @ColumnInfo(name = "title")
    private String mTitle;
    // description
    @ColumnInfo(name = "description")
    private String mDescription;
    // isCompleted
    @ColumnInfo(name = "is_completed")
    private boolean mIsCompleted;
    // createdAt
    @ColumnInfo(name = "created_at")
    private Date mCreatedAt;
    // updatedAt
    @ColumnInfo(name = "updated_at")
    private Date mUpdatedAt;
    // Getter and Setter methods for all the fields
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public boolean getIsCompleted() {
        return mIsCompleted;
    }

    public void setIsCompleted(boolean completed) {
        mIsCompleted = completed;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.mCreatedAt = createdAt;
    }

    public Date getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.mUpdatedAt = updatedAt;
    }
}
