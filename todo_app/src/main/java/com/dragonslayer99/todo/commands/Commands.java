package com.dragonslayer99.todo.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.dragonslayer99.todo.todos.Todo;
import com.dragonslayer99.todo.utils.DisplayInstructions;
import com.dragonslayer99.todo.utils.FileOperations;
import com.dragonslayer99.todo.utils.GetDateTime;

import tools.jackson.core.JacksonException;
// import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class Commands {

    List<Todo> existingTodos;
    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File(FileOperations.FILE_NAME);

    public Commands() {
        loadTodos(objectMapper, file);
    }

    public final void loadTodos(ObjectMapper objectMapper, File file) {
        // ObjectMapper objectMapper = new ObjectMapper();
        existingTodos = new ArrayList<>();

        // File file = new File(FileOperations.FILE_NAME);

        if (file.exists() && file.length() > 0) {
            existingTodos = objectMapper.readValue(file,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Todo.class));
        }
    }

    public void addTodo(String todo) {

        String task = todo.replace("add ", "");
        String ID = UUID.randomUUID().toString();
        Todo jsonTodo = new Todo(ID, task, GetDateTime.getDate(), GetDateTime.getTime(),
                "In Progress");

        loadTodos(objectMapper, file);

        existingTodos.add(jsonTodo);
        objectMapper.writeValue(file, existingTodos);
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

                String newStatus = "";
                for (int i = 2; i < args.length; i++) {
                    newStatus = newStatus + " " + args[i];
                }
                t.setStatus(newStatus);
                objectMapper.writeValue(file, existingTodos);
                System.out.println("Updated successfully");
                return "complete";
            }
        }

        return "Error occured during updation of the task";
    }

    public String deleteTodo() {
        return "";
    }

    public final void display() {

        try {
            // ObjectMapper objectMapper = new ObjectMapper();
            // existingTodos = objectMapper.readValue(new File(FileOperations.FILE_NAME),
            // new TypeReference<List<Todo>>() {
            // });
            // File file = new File(FileOperations.FILE_NAME);
            // if (file.exists() && file.length() > 0) {
            // existingTodos = objectMapper.readValue(file,
            // objectMapper.getTypeFactory().constructCollectionType(List.class, Todo.class));
            // }

            for (int i = 0; i < 145; i++) {
                System.out.print("-");
            }
            System.out.print("\n");

            System.out.printf(DisplayInstructions.GREEN + "| %-40s | %-40s | %-12s | %-12s | %-25s |"
                    + DisplayInstructions.RESET, "ID", "Task", "date", "time", "status");

            // int len = String.format("| %-40s | %-40s | %-10s | %-10s | %-25s |", "ID", "Task", "date", "time", "status").length();
            // System.out.println(len);    

            System.out.println();
            for (int i = 0; i < 145; i++) {
                System.out.print("-");
            }
            System.out.print("\n");

            for (Todo t : existingTodos) {

                System.out.printf("| %-40s | %-40s | %-12s | %-12s | %-25s |", t.getID(), t.getTask(), t.getDate(),
                        t.getTime(),
                        t.getStatus());
                System.out.println();

                for (int i = 0; i < 145; i++) {
                    System.out.print("-");
                }

                System.out.println();

            }
        } catch (JacksonException e) {
            System.err.println("Todos doesn't exist");
        }

    }

}
