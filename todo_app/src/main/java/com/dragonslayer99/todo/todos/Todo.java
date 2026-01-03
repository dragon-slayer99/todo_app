package com.dragonslayer99.todo.todos;

public class Todo {
    private final int id;
    private String task;
    private final String date;
    private final String time;
    private String status;

    public Todo (int id, String task, String date, String time, String status) {
        this.id = id;
        this.task = task;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public int getID() {
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

    public void setTask(String task) {
        this.task = task;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
