package com.dragonslayer99.todo.utils;

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
                                "5. To exit the program: " + ORANGE + "Exit" + RESET);

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

}
