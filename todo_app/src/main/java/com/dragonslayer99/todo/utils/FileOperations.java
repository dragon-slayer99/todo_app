package com.dragonslayer99.todo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.dragonslayer99.todo.todos.Todo;

public class FileOperations {
    public static final String FILE_NAME = "todos.json";

    public static void CreateFile() {
        try {
            File file = new File(FILE_NAME);
            if (file.createNewFile()) {
                System.out.println("file successfully created!");
            } else {
                System.err.println("file already exists!");
            }
        } catch (IOException e) {
            System.err.println("IO exception occured: " + e);
        } catch (SecurityException e) {
            System.err.println("Permission denied!: " + e);
        }

    }

    public static String getLastLine() {

        String currentLine;
        String lastLine = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            while ((currentLine = reader.readLine()) != null) {
                lastLine = currentLine;
            }

            return lastLine;

        } catch (IOException e) {
            System.err.println(DisplayInstructions.RED + "Error occured while reading for the last line in the file!"
                    + DisplayInstructions.RESET);
            System.err.println(e);
        }

        return lastLine;
    }

    public static ArrayList<Todo> getTodos() {

        String currentLine;
        ArrayList<Todo> todoList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            while ((currentLine = reader.readLine()) != null) {
                int id;
                String task;
                String date;
                String time;
                String status;

                id = Integer.parseInt(currentLine.substring(7, currentLine.indexOf(',')));
                task = currentLine.substring(currentLine.indexOf("task") + 7, currentLine.indexOf("date") - 2);
                date = currentLine.substring(currentLine.indexOf("date") + 7, currentLine.indexOf("time") - 2);
                time = currentLine.substring(currentLine.indexOf("time") + 7, currentLine.indexOf("status") - 2);
                status = currentLine.substring(currentLine.indexOf("status") + 10, currentLine.indexOf('}'));

                Todo todo = new Todo(id, task, date, time, status);

                todoList.add(todo);

            }

            return todoList;

        } catch (IOException e) {
            System.err.println(DisplayInstructions.RED + "Error occured while reading for the last line in the file!"
                    + DisplayInstructions.RESET);
            System.err.println(e);
        }

        return null;
    }
}
