package com.adam.app.todoapp.model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

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

    // equals and hashcode methods
    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!(obj instanceof Task)) return false;
        Task task = (Task) obj;
        return mId == task.mId &&
                mIsCompleted == task.mIsCompleted &&
                Objects.equals(mTitle, task.mTitle) &&
                Objects.equals(mDescription, task.mDescription) &&
                Objects.equals(mCreatedAt, task.mCreatedAt) &&
                Objects.equals(mUpdatedAt, task.mUpdatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mTitle, mDescription, mIsCompleted, mCreatedAt, mUpdatedAt);
    }
}
