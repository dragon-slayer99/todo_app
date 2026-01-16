package com.dragonslayer99.todo.utils;

import java.util.List;

import com.dragonslayer99.todo.todos.Todo;

public class DisplayInstructions {
        public static final String RESET = "\u001B[0m";
        public static final String BLACK = "\u001B[30m";
        public static final String RED = "\u001B[38;5;160m";
        // public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String ORANGE = "\u001B[38;5;202m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String WHITE = "\u001B[37m";
        public static final String BGBLACK = "\u001B[40m";
        public static final String BGRED = "\u001B[41m";
        public static final String BGGREEN = "\u001B[42m";
        public static final String BGORANGE = "\u001B[48;5;202m";
        public static final String BGBLUE = "\u001B[48;5;21m";
        public static final String BGPURPLE = "\u001B[45m";
        public static final String BGCYAN = "\u001B[46m";
        public static final String BGWHITE = "\u001B[47m";

        public static final String SUCCESS = "\u001B[38;5;227m";
        public static final String UNSUCCESS = "\u001B[38;5;45m";
        public static final String ABANDONED = "\u001B[38;5;202m";

        public void displayInstruction() {
                final String ASCII_ART = """

                                ████████╗ █████╗ ███████╗██╗  ██╗ ██████╗██╗     ██╗
                                ╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝██╔════╝██║     ██║
                                   ██║   ███████║███████╗█████╔╝ ██║     ██║     ██║
                                   ██║   ██╔══██║╚════██║██╔═██╗ ██║     ██║     ██║
                                   ██║   ██║  ██║███████║██║  ██╗╚██████╗███████╗██║
                                   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝ ╚═════╝╚══════╝╚═╝
                                """;

                System.out.println(GREEN + ASCII_ART + RESET);

                System.out.println(
                                BGBLUE + " Use following commands " + RESET + "\n");
                System.out.println(
                                "1. Add a task: " + GREEN + "add <task_name>" + RESET);
                System.out.println(
                                "2. Update the status of the task: " + PURPLE + "update <task_number> <status>"
                                                + RESET);
                System.out.println(
                                "3. Delete a task: " + RED + "delete <task_number>" + RESET);
                System.out.println(
                                "4. Display the task list: " + CYAN + "display" + RESET);
                System.out.println(
                                "5. Save changes: " + PURPLE + "save" + RESET);
                System.out.println(
                                "6. To exit the program: " + ORANGE + "Exit" + RESET);
                System.out.println();                
        }

        public static void printLine(int dashLineLength, String color) {
                System.out.print(color);
                for (int i = 0; i < dashLineLength; i++) {
                        System.out.print("-");
                }
                System.out.print(DisplayInstructions.RESET);
                System.out.println();
        }

        public static void printError(String msg) {
                System.out.println(RED + msg + RESET);
        }

        public static void printSuccess(String msg) {
                System.out.println(GREEN + msg + RESET);
        }

        public static void displayTodos(List<Todo> todos, int maxTaskLength, int maxStatusLength) {

                if (todos.isEmpty()) {
                        DisplayInstructions.printError("No tasks to display!");
                        return;
                }

                int dashLineLength = String
                                .format("| %-7s | %-" + maxTaskLength + "s | %-11s | %-9s | %-" + maxStatusLength
                                                + "s |",
                                                "TaskNo.", "Task", "Date", "Time", "Status")
                                .length();

                DisplayInstructions.printLine(dashLineLength, DisplayInstructions.GREEN);

                System.out.printf(DisplayInstructions.GREEN + "| %-7s | %-" + maxTaskLength + "s | %-11s | %-9s | %-"
                                + maxStatusLength + "s |"
                                + DisplayInstructions.RESET, "TaskNo.", "Task", "date", "time", "status");

                System.out.println();
                DisplayInstructions.printLine(dashLineLength, DisplayInstructions.GREEN);

                for (Todo t : todos) {

                        String reset = DisplayInstructions.RESET;
                        String greenPipe = DisplayInstructions.GREEN + "|" + DisplayInstructions.RESET;

                        String displayStr = greenPipe + " %-7s " + greenPipe + " %-" + maxTaskLength + "s "
                                        + greenPipe + " %-11s "
                                        + greenPipe + " %-9s "
                                        + greenPipe + " " + "%-" + maxStatusLength
                                        + "s" + " " + greenPipe;

                        String userStatus = t.getStatus().toLowerCase();
                        String statusColor = UserStatus.completedStatus.contains(userStatus)
                                        ? DisplayInstructions.SUCCESS
                                        : UserStatus.inCompleteStatus.contains(userStatus)
                                                        ? DisplayInstructions.UNSUCCESS
                                                        : UserStatus.cannotCompleteStatus.contains(userStatus)
                                                                        ? DisplayInstructions.ABANDONED
                                                                        : "";

                        if (UserStatus.completedStatus.contains(userStatus)
                                        || UserStatus.inCompleteStatus.contains(userStatus)
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

}
