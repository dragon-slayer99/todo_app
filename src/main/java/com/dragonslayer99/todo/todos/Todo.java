package com.dragonslayer99.todo.todos;

public class Todo {
    private String taskNo;
    private String id;
    private String task;
    private String date;
    private String time;
    private String status;

    public Todo() {
    }

    public Todo(String taskNo,String id, String task, String date, String time, String status) {
        this.taskNo = taskNo;
        this.id = id;
        this.task = task;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public String getID() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
