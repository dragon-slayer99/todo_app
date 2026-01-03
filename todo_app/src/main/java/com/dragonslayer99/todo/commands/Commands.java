package com.dragonslayer99.todo.commands;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.dragonslayer99.todo.todos.Todo;
import com.dragonslayer99.todo.utils.DisplayInstructions;
import com.dragonslayer99.todo.utils.FileOperations;
import com.dragonslayer99.todo.utils.GetDateTime;

public class Commands {

    ArrayList<Todo> todoList;

    public Commands() {
        todoList = FileOperations.getTodos();
    }

    public void addTodo(String todo) {

        String task = todo.replace("add ", "");
    
        try (FileWriter writer = new FileWriter(FileOperations.FILE_NAME, true)) {

            String lastline = FileOperations.getLastLine();

            int taskID;

            if (lastline.equals("")) {
                taskID = 1;
            } else {
                taskID = Integer.parseInt(lastline.substring(7, lastline.indexOf(','))) + 1;
            }

            String todoToBeWritten = "{" +
                    "\"id\"" + ": " + taskID + "," +
                    "\"task\"" + ": " + "\"" + task + "\"" + "," +
                    "\"date\"" + ": " + GetDateTime.getDate() + "," +
                    "\"time\"" + ": " + GetDateTime.getTime() + "," +
                    "\"status\"" + ": " + "\"in progress\"" + "}" + "\n";

            writer.write(todoToBeWritten);

            writer.close();
            System.err.println("successfully written");

        } catch (IOException e) {
            System.err.println("Error while writting data to the file: " + e);
            System.err.println(DisplayInstructions.BGRED + "failed while writting" + DisplayInstructions.RESET);
        }

    }

    public String updateTodo(String cmd) {    // update 01 complete

        String[] cmdItems = cmd.split(" ");
        int id = Integer.parseInt(cmdItems[1]);
        String status = cmdItems[2];

        todoList.get(id).setStatus(status); 


        return "";
    }

    public String deleteTodo() {
        return "";
    }

    public final void display() {

        for (Todo todo : todoList) {
            System.out.print(todo.getID() + ". ");
            System.out.println(todo.getTask());
            System.out.println(todo.getDate());
            System.out.println(todo.getTime());
            System.out.println(todo.getStatus());

            System.out.println("--------------------------------------");
        }

    }

}
