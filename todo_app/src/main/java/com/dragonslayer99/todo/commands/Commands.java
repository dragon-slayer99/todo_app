package com.dragonslayer99.todo.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.dragonslayer99.todo.todos.Todo;
import com.dragonslayer99.todo.utils.DisplayInstructions;
import com.dragonslayer99.todo.utils.FileOperations;
import com.dragonslayer99.todo.utils.GetDateTime;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Commands {

    List<Todo> existingTodos;
    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File(FileOperations.FILE_NAME);
    int maxTaskLength = 40;
    int maxStatusLength = 15;

    public Commands() throws IOException {
        loadTodos(objectMapper, file);
    }

    public final void loadTodos(ObjectMapper objectMapper, File file) throws IOException {
        // ObjectMapper objectMapper = new ObjectMapper();
        existingTodos = new ArrayList<>();

        // File file = new File(FileOperations.FILE_NAME);

        if (file.exists() && file.length() > 0) {
            existingTodos = objectMapper.readValue(file,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Todo.class));
        }

        for (Todo t : existingTodos) {

            int currTaskLength = t.getTask().length();
            int currStatusLength = t.getStatus().length();

            if (currTaskLength > maxTaskLength) {
                maxTaskLength = currTaskLength;
            }

            if (currStatusLength > maxStatusLength) {
                maxStatusLength = currStatusLength;
            }
        }

        maxStatusLength += 2; // little spacing for more readablity of the task
        maxTaskLength += 2;

    }

    public void addTodo(String todo) {

        String task = todo.replace("add ", "");
        String ID = UUID.randomUUID().toString();
        Todo jsonTodo = new Todo(ID, task, GetDateTime.getDate(), GetDateTime.getTime(),
                "In Progress");

        // loadTodos(objectMapper, file);

        existingTodos.add(jsonTodo);
        if (task.length() > maxTaskLength) {
            maxTaskLength = task.length() + 2;
        }
        // objectMapper.writeValue(file, existingTodos);
        System.err.println("successfully written");

    }

    public String updateTodo(String cmd) { // update 01 complete

        String[] args = cmd.split(" ");
        if (args.length < 3) {
            System.out.println("Invalid command");
            return "";
        }
        // List<Todo> todoList;

        for (Todo t : existingTodos) {
            if (t.getID().equals(args[1])) {

                String newStatus = args[2];
                for (int i = 3; i < args.length; i++) {

                    newStatus = newStatus + " " + args[i];

                }
                t.setStatus(newStatus);
                // objectMapper.writeValue(file, existingTodos);
                System.out.println("Updated successfully");
                return "complete";
            }
        }

        return "Error occured during updation of the task";
    }

    public void deleteTodo(String cmd) { // delete 3423-25435-345346-g4545-b4535
        String args[] = cmd.split(" ");

        if (args.length != 2) {
            System.out.println("Invalid command!");
            return;
        }

        for (int idx = 0; idx < existingTodos.size(); idx++) {

            Todo currTodo = existingTodos.get(idx);
            if (currTodo.getID().equals(args[1])) {

                existingTodos.remove(idx);
                System.out.println("Task deleted successfully!");
                return;
            }

        }
    }

    public void display() throws JacksonException {

        int dashLineLength = String
                .format("| %-37s | %-" + maxTaskLength + "s | %-11s | %-9s | %-" + maxStatusLength + "s |", "ID",
                        "Task", "Date", "Time", "Status")
                .length();

        for (int i = 0; i < dashLineLength; i++) {
            System.out.print("-");
        }
        System.out.print("\n");

        System.out.printf(DisplayInstructions.GREEN + "| %-37s | %-" + maxTaskLength + "s | %-11s | %-9s | %-"
                + maxStatusLength + "s |"
                + DisplayInstructions.RESET, "ID", "Task", "date", "time", "status");

        System.out.println();
        for (int i = 0; i < dashLineLength; i++) {
            System.out.print("-");
        }
        System.out.print("\n");

        for (Todo t : existingTodos) {

            System.out.printf("| %-37s | %-" + maxTaskLength + "s | %-11s | %-9s | %-" + maxStatusLength + "s |",
                    t.getID(), t.getTask(), t.getDate(),
                    t.getTime(),
                    t.getStatus());
            System.out.println();

            for (int i = 0; i < dashLineLength; i++) {
                System.out.print("-");
            }

            System.out.println();

        }

    }

    public void exit() {

        try {

            objectMapper.writeValue(file, existingTodos);

        } catch (IOException e) {
            System.err.println("Error while writing to the file!");
        }
        System.out.println("Program terminated!");

    }

}
