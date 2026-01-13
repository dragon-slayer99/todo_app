package com.dragonslayer99.todo.utils;

import java.io.File;
import java.io.IOException;

public class FileOperations {
    public static final String FILE_NAME = "todos.json";

    public static void CreateFile() {
        try {
            File file = new File(FILE_NAME);
            if (file.createNewFile()) {
                DisplayInstructions.printSuccess("file successfully created!");
            } else {
                System.out.println(DisplayInstructions.CYAN + "file already exists!" + DisplayInstructions.RESET);
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("IO exception occured: " + e);
        } catch (SecurityException e) {
            System.err.println("Permission denied!: " + e);
        }

    }


}
