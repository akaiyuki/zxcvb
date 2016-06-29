package com.smartwave.taskr.object;

/**
 * Created by smartwavedev on 6/29/16.
 */
public class CommentObject {

    public String taskId;
    public String taskComment;
    private int id;

    public CommentObject() {
    }

    public CommentObject(int id, String taskId, String taskComment) {
        this.id = id;
        this.taskId = taskId;
        this.taskComment = taskComment;
    }
    public CommentObject(String taskId, String taskComment) {
        this.taskId = taskId;
        this.taskComment = taskComment;
    }

    public String getTaskComment() {
        return taskComment;
    }

    public void setTaskComment(String taskComment) {
        this.taskComment = taskComment;
    }

    public String getTaskId() {

        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
