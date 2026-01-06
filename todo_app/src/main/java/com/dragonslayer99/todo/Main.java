package com.dragonslayer99.todo;

import java.util.Scanner;

import com.dragonslayer99.todo.commands.Commands;
import com.dragonslayer99.todo.utils.DisplayInstructions;
import com.dragonslayer99.todo.utils.FileOperations;

public class Main {

        public static void main(String[] args) {
                DisplayInstructions displayInstructionsObj = new DisplayInstructions();
                displayInstructionsObj.displayInstruction();
                FileOperations.CreateFile();

                Commands command = new Commands();

                Runtime.getRuntime().addShutdownHook(new Thread(() -> { // for saving the changes made to the existingTodos list before program termination.
                        try {
                                command.exit();  
                        } catch (Exception e) {
                                System.err.println("Error occured during unexpected program termination!");
                        }
                }));

                try (Scanner scanner = new Scanner(System.in)) {
                        String usercommand;

                    OUTER:
                    while (true) {
                        System.out.println("Enter command: ");
                        usercommand = scanner.nextLine();
                        String scannerCmd[] = usercommand.split(" ");
                        switch (scannerCmd[0]) {
                            case "add":
                                command.addTodo(usercommand);
                                break;
                            case "update":
                                command.updateTodo(usercommand);
                                break;
                            case "delete":
                                command.deleteTodo(usercommand);
                                break;
                            case "display":
                                command.display();
                                break;
                            case "exit":
                                break OUTER;
                            default:
                                System.out.println("Please enter valid command!");
                                break;
                        }
                    }
                } catch (Exception e) {
                        System.out.println(DisplayInstructions.RED + "Erro while reading user input"
                                        + DisplayInstructions.RESET);
                        System.out.println(e);
                }

        }
}
