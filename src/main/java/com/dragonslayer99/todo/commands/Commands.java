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
import com.dragonslayer99.todo.utils.UserStatus;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Commands {

    List<Todo> existingTodos;
    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File(FileOperations.FILE_NAME);
    int maxTaskLength = 6;
    int maxStatusLength = 15;
    private int taskNo;

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

        taskNo = existingTodos.isEmpty() ? 1 : existingTodos.size() - 1;

        maxStatusLength += 2; // little spacing for more readablity of the task
        maxTaskLength += 2;

    }

    public void addTodo(String todo) {

        String task = todo.replace("add ", "");
        String ID = UUID.randomUUID().toString();
        String currTaskNo = String.format("%03d", taskNo);
        Todo jsonTodo = new Todo(currTaskNo, ID, task, GetDateTime.getDate(), GetDateTime.getTime(),
                "In Progress");
        taskNo++;

        // loadTodos(objectMapper, file);

        existingTodos.add(jsonTodo);
        if (task.length() > maxTaskLength) {
            maxTaskLength = task.length() + 2;
        }
        // objectMapper.writeValue(file, existingTodos);
        DisplayInstructions.printSuccess("successfully written");

    }

    public void updateTodo(String cmd) { // update 01 complete

        String[] args = cmd.split(" ");
        if (args.length < 3) {
            DisplayInstructions.printError("Missing parameter (eg. update 01 completed)");
            return;
        }
        // List<Todo> todoList;

        String formattedUserTaskNo = args[1].replaceFirst("^0+(?!$)", "");
        String formattedSysTaskNo;

        for (Todo t : existingTodos) {

            formattedSysTaskNo = t.getTaskNo().replaceFirst("^0+(?!$)", "");

            if (formattedSysTaskNo.equals(formattedUserTaskNo) || t.getID().equals(args[1])) {

                String newStatus = args[2];
                for (int i = 3; i < args.length; i++) {

                    newStatus = newStatus + " " + args[i];

                }
                t.setStatus(newStatus);
                // objectMapper.writeValue(file, existingTodos);
                DisplayInstructions.printSuccess("Updated successfully");
                return;
            }
        }

        DisplayInstructions.printError("Task not found!");

    }

    public void deleteTodo(String cmd) { // delete 3423-25435-345346-g4545-b4535
        String args[] = cmd.split(" ");

        if (args.length != 2) {
            DisplayInstructions.printError("Invalid command format (eg. delete 01 or delete *)");
            return;
        }

        String formattedUserTaskNo = args[1].replaceFirst("^0+(?!$)", "");
        String formattedSysTaskNo;

        for (int idx = 0; idx < existingTodos.size(); idx++) {

            Todo currTodo = existingTodos.get(idx);
            formattedSysTaskNo = currTodo.getTaskNo().replaceFirst("^0+(?!$)", "");

            if (formattedSysTaskNo.equals(formattedUserTaskNo) || currTodo.getID().equals(args[1])) {

                existingTodos.remove(idx);
                DisplayInstructions.printSuccess("Task deleted successfully!");
                return;
            }

        }

        DisplayInstructions.printError("Task not found!");
    }

    public void clearTodo(String cmd) {
        if (cmd.equals("y") || cmd.equals("yes")) {
            existingTodos.clear();
            DisplayInstructions.printSuccess("Successfully cleared all tasks!");
        }
    }

    public void display() throws JacksonException {

        if (existingTodos.isEmpty()) {
            DisplayInstructions.printError("No tasks to display!");
            return;
        }

        int dashLineLength = String
                .format("| %-7s | %-" + maxTaskLength + "s | %-11s | %-9s | %-" + maxStatusLength + "s |",
                        "TaskNo.", "Task", "Date", "Time", "Status")
                .length();

        DisplayInstructions.printLine(dashLineLength, DisplayInstructions.GREEN);

        System.out.printf(DisplayInstructions.GREEN + "| %-7s | %-" + maxTaskLength + "s | %-11s | %-9s | %-"
                + maxStatusLength + "s |"
                + DisplayInstructions.RESET, "TaskNo.", "Task", "date", "time", "status");

        System.out.println();
        DisplayInstructions.printLine(dashLineLength, DisplayInstructions.GREEN);

        for (Todo t : existingTodos) {

            String reset = DisplayInstructions.RESET;
            String greenPipe = DisplayInstructions.GREEN + "|" + DisplayInstructions.RESET;

            String displayStr = greenPipe + " %-7s " + greenPipe + " %-" + maxTaskLength + "s "
                    + greenPipe + " %-11s "
                    + greenPipe + " %-9s "
                    + greenPipe + " " + "%-" + maxStatusLength
                    + "s" + " " + greenPipe;

            String userStatus = t.getStatus().toLowerCase();
            String statusColor = UserStatus.completedStatus.contains(userStatus) ? DisplayInstructions.SUCCESS
                    : UserStatus.inCompleteStatus.contains(userStatus) ? DisplayInstructions.UNSUCCESS
                            : UserStatus.cannotCompleteStatus.contains(userStatus) ? DisplayInstructions.ABANDONED : "";

            if (UserStatus.completedStatus.contains(userStatus) || UserStatus.inCompleteStatus.contains(userStatus)
                    || UserStatus.cannotCompleteStatus.contains(userStatus)) {
                displayStr = greenPipe + " %-7s " + greenPipe + " %-" + maxTaskLength + "s "
                        + greenPipe + " %-11s "
                        + greenPipe + " %-9s "
                        + greenPipe + " " + statusColor + "%-" + maxStatusLength
                        + "s" + reset + " " + greenPipe;

            }

            System.out.printf(displayStr, t.getTaskNo(),
                    t.getTask(), t.getDate(),
                    t.getTime(),
                    t.getStatus());
            System.out.println();

            DisplayInstructions.printLine(dashLineLength, DisplayInstructions.GREEN);

        }

    }

    public void exit() {

        try {

            objectMapper.writeValue(file, existingTodos);

        } catch (IOException e) {
            DisplayInstructions.printError("Error while writing to the file!");
        }
        System.out.println("Program terminated!");

    }

}
