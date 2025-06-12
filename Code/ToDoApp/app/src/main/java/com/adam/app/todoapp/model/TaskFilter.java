package com.adam.app.todoapp.model;

/**
 * Singleton
 * Handle Task type filter
 */
public final class TaskFilter {

    private TaskFilter() {}

    private static class Helper {
        private static final TaskFilter INSTANCE = new TaskFilter();
    }

    public static TaskFilter getInstance() {
        return Helper.INSTANCE;
    }

    /**
     * Filter type for tasks.: enum
     */
    public enum FilterType {
        ALL, INCOMPLETE , COMPLETED
    }

    private FilterType mFiltertype;

    /**
     * get filter type
     */
    public FilterType getFilterType() {
        return mFiltertype;
    }

    /**
     * set filter type
     */
    public void setFilterType(FilterType filterType) {
        mFiltertype = filterType;
    }
}
