package com.smartwave.taskr.object;

/**
 * Created by smartwavedev on 6/16/16.
 */
public class TaskObject {

    private int id;
    private String taskName;
    private String taskDescription;
    private String taskStatus;

    public TaskObject() {
    }

    public TaskObject(int id, String taskName, String taskDescription, String taskStatus) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
    }

    public TaskObject(String taskName, String taskDescription, String taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskDescription() {

        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {

        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

}
