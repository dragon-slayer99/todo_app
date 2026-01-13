<h1 align="center">Todo App</h1>

[![Java](https://img.shields.io/badge/Java-17%2B-orange)](https://www.oracle.com/java/technologies/downloads/)
[![Build](https://img.shields.io/badge/build-maven-blue)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)

A robust, CLI-based task management application built with Java. This tool allows you to efficiently organize your tasks directly from your terminal, offering a streamlined workflow for adding, updating, and tracking your daily to-dos.

## Features

-   **Task Management**: Easily create, update, and remove tasks.
-   **Persistent Storage**: Your tasks are automatically saved, ensuring you never lose track of your progress.
-   **Interactive CLI**: User-friendly command-line interface with clear instructions and visual feedback.
-   **Bulk Actions**: clear all tasks with a single command.
-   **Detailed Display**: View your tasks in a formatted table with timestamps and status.

## Getting Started

### Prerequisites

-   **Java 17** or higher installed on your machine.

### Installation

1.  **Clone the repository** (if you haven't already):
    ```bash
    git clone https://github.com/dragon-slayer99/todo_app.git
    cd todo_app
    ```

2.  **Locate the JAR file**:
    The application is packaged as a runnable JAR file located in the `target` directory.
    -   `target/todo_app-0.1-SNAPSHOT.jar`

## Usage

To start the application, run the JAR file from your terminal:

```bash
java -jar target/todo_app-0.1-SNAPSHOT.jar
```

### Running with a Batch File

For convenience, you can create a simple batch file (`.bat`) to launch the application without typing the command every time.

1.  **Create a file** named `run.bat` in the project root directory.
2.  **Add the following line** to the file:
    ```batch
    @echo off
    java -jar target/todo_app-0.1-SNAPSHOT.jar
    pause
    ```
3.  **Run the application** by simply double-clicking `run.bat` or by running `run.bat` in your terminal.

### Commands

Once the application is running, you can use the following commands:

-   **Add a task**:
    ```text
    add <task_name>
    ```
    Example: `add Buy groceries`

-   **Update a task**:
    ```text
    update <task_ID> <status>
    ```
    Example: `update 1234-5678 Done`

-   **Delete a task**:
    ```text
    delete <task_ID>
    ```
    Example: `delete 1234-5678`

-   **Delete all tasks**:
    ```text
    delete *
    ```
    *Note: You will be asked for confirmation.*

-   **Display tasks**:
    ```text
    display
    ```

-   **Exit**:
    ```text
    exit
    ```

## Technologies

-   **Java**: Core programming language.
-   **Maven**: Dependency management and build tool.
-   **Jackson**: High-performance JSON processor for data persistence.
-   **JUnit**: Testing framework.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1.  Fork the project
2.  Create your feature branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
