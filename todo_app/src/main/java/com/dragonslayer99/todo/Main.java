package com.dragonslayer99.todo;

import java.util.Scanner;

import com.dragonslayer99.todo.commands.Commands;
import com.dragonslayer99.todo.utils.DisplayInstructions;
import com.dragonslayer99.todo.utils.FileOperations;

public class Main {

        public static void main(String[] args) {

                DisplayInstructions displayinstruction = new DisplayInstructions();
                displayinstruction.displayInstruction();
                FileOperations.CreateFile();

                Commands command = new Commands();

                try (Scanner scanner = new Scanner(System.in)) {
                        String usercommand;

                        while (true) {

                                System.out.println("Enter command: ");
                                usercommand = scanner.nextLine();

                                if (usercommand.contains("add")) {
                                        command.addTodo(usercommand);
                                } else if (usercommand.contains("update")) {
                                        command.updateTodo(usercommand);
                                } else if (usercommand.contains("delete")) {
                                        System.out.println("Task Deleted!");
                                } else if (usercommand.contains("display")) {
                                        command.display();
                                } else if (usercommand.contains("exit")) {
                                        System.out.println("Program ended!");
                                        break;
                                } else {
                                        System.out.println("Please enter valid command!");
                                }
                        }
                } catch (Exception e) {
                        System.out.println(DisplayInstructions.RED + "Erro while reading user input" + DisplayInstructions.RESET);
                        System.out.println(e);
                }

        }
}
