package com.dragonslayer99.todo;

import java.io.IOException;
import java.util.Scanner;

import com.dragonslayer99.todo.commands.Commands;
import com.dragonslayer99.todo.utils.DisplayInstructions;
import com.dragonslayer99.todo.utils.FileOperations;

public class Main {

    public static void main(String[] args) throws IOException {


        DisplayInstructions displayInstructionsObj = new DisplayInstructions();

        
        displayInstructionsObj.displayInstruction();
        FileOperations.CreateFile();

        Commands command = new Commands();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> { // for saving the changes made to the existingTodos list
                                                                // before program termination.
            try {
                command.exit();
            } catch (Exception e) {
                System.err.println("Error occured during unexpected program termination!");
            }
        }));

        try (Scanner scanner = new Scanner(System.in)) {
            String usercommand;

            OUTER: while (true) {
                System.out.print("Enter command: ");
                usercommand = scanner.nextLine();
                String scannerCmd[] = usercommand.split(" ");

                if (("add".equals(scannerCmd[0]) || "delete".equals(scannerCmd[0]) || "update".equals(scannerCmd[0]))
                        && scannerCmd.length <= 1) {
                    DisplayInstructions.printError("Invalid command format! (eg. < add | delete | update > < task_name | taskno | taskno <status> >)");
                    continue;
                }

                switch (scannerCmd[0].toLowerCase()) {
                    case "add" -> command.addTodo(usercommand);
                    case "update" -> command.updateTodo(usercommand);
                    case "y", "yes" -> command.clearTodo(usercommand);
                    case "n", "no", "N", "No", "NO" -> DisplayInstructions.printSuccess("Deletion process aborted!");
                    case "delete" -> {
                        boolean warningGiven = false;
                        if (scannerCmd[1].equals("*") && !warningGiven) {
                            DisplayInstructions.printError("This action will delete every saved task!");
                            System.out.println("Do you want to continue?(y/n)");
                            warningGiven = true;
                            break;
                        }
                        command.deleteTodo(usercommand);
                    }
                    case "display" -> command.display(usercommand);
                    case "save" -> command.save();
                    case "exit" -> {
                        break OUTER;
                    }

                    default -> DisplayInstructions.printError("Please enter valid command!");
                }
            }
        } catch (Exception e) {
            DisplayInstructions.printError("Error while reading user input");
            System.out.println(e);
        }

    }
}
