# TaskCLI User Guide

<p align="center">
  <img src="https://img.shields.io/badge/Version-0.1-blue?style=for-the-badge" alt="Version">
  <img src="https://img.shields.io/badge/Platform-Windows%20%7C%20macOS%20%7C%20Linux-green?style=for-the-badge" alt="Platform">
  <img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge" alt="Java">
</p>

---

## Table of Contents

1. [Introduction](#introduction)
2. [System Requirements](#system-requirements)
3. [Installation](#installation)
4. [Getting Started](#getting-started)
5. [Commands Reference](#commands-reference)
   - [Add Task](#1-add-task)
   - [Display Tasks](#2-display-tasks)
   - [Update Task](#3-update-task)
   - [Delete Task](#4-delete-task)
   - [Delete All Tasks](#5-delete-all-tasks)
   - [Exit](#6-exit)
6. [Task Statuses](#task-statuses)
7. [Display Filtering](#display-filtering)
8. [Data Storage](#data-storage)
9. [Tips & Best Practices](#tips--best-practices)
10. [Troubleshooting](#troubleshooting)
11. [FAQs](#faqs)

---

## Introduction

**TaskCLI** is a powerful, command-line based task management application designed for developers and power users who prefer working in the terminal. It provides a fast, distraction-free way to manage your daily to-dos without leaving your command line.

### Key Features

- ✅ **Simple Commands** – Add, update, delete, and view tasks with intuitive commands
- 📊 **Formatted Display** – Tasks are displayed in a beautiful, color-coded table
- 💾 **Persistent Storage** – Your tasks are automatically saved to a JSON file
- 🎨 **Color-Coded Statuses** – Visual indicators for task progress
- 🔍 **Filter Views** – Display completed, incomplete, or skipped tasks separately
- ⚡ **Auto-Save** – Changes are automatically saved when you exit

---

## System Requirements

| Requirement          | Details                               |
| -------------------- | ------------------------------------- |
| **Operating System** | Windows 10/11, macOS 10.14+, or Linux |
| **Java**             | Java 17 or higher                     |
| **Terminal**         | Any terminal with ANSI color support  |

### Checking Your Java Version

Open your terminal and run:

```bash
java -version
```

You should see output similar to:

```
java version "17.0.x" 2024-xx-xx LTS
```

If you don't have Java 17+ installed, download it from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [Adoptium](https://adoptium.net/).

---

## Installation

### Option 1: Download Pre-built JAR

1. Navigate to the project's `target` directory
2. Locate the file `todo_app-0.1-SNAPSHOT.jar`
3. You're ready to run!

### Option 2: Build from Source

1. Clone the repository:
   ```bash
   git clone https://github.com/dragon-slayer99/todo_app.git
   cd todo_app
   ```

2. Build with Maven:
   ```bash
   mvn clean package
   ```

3. The JAR file will be in the `target` directory

---

## Getting Started

### Running the Application

**Windows (Command Prompt or PowerShell):**
```bash
java -jar target/todo_app-0.1-SNAPSHOT.jar
```

**macOS/Linux:**
```bash
java -jar target/todo_app-0.1-SNAPSHOT.jar
```

### Using a Batch File (Windows)

For convenience, create a `run.bat` file:

```batch
@echo off
java -jar target/todo_app-0.1-SNAPSHOT.jar
pause
```

Double-click `run.bat` to launch the application.

### What You'll See

When you start TaskCLI, you'll see a welcome banner:

```
████████╗ █████╗ ███████╗██╗  ██╗ ██████╗██╗     ██╗
╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝██╔════╝██║     ██║
   ██║   ███████║███████╗█████╔╝ ██║     ██║     ██║
   ██║   ██╔══██║╚════██║██╔═██╗ ██║     ██║     ██║
   ██║   ██║  ██║███████║██║  ██╗╚██████╗███████╗██║
   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝ ╚═════╝╚══════╝╚═╝

 Use following commands 

1. Add a task: add <task_name>
2. Update the status of the task: update <task_number> <status>
3. Delete a task: delete <task_number>
4. Display the task list: display
5. To exit the program: Exit

Enter command: 
```

---

## Commands Reference

### 1. Add Task

Add a new task to your list.

**Syntax:**
```
add <task description>
```

**Examples:**
```
add Buy groceries from the store
add Complete project report by Friday
add Call mom for her birthday
```

**Output:**
```
successfully written
```

> 💡 **Tip:** Task descriptions can include spaces. Everything after `add ` becomes the task name.

---

### 2. Display Tasks

View all your tasks in a formatted table.

**Syntax:**
```
display
display <filter>
```

**Examples:**

Display all tasks:
```
display
```

**Sample Output:**
```
-----------------------------------------------------------------------------------
| TaskNo. | Task                              | date       | time     | status       |
-----------------------------------------------------------------------------------
| 001     | Buy groceries from the store      | 2026-01-14 | 12:01:50 | In Progress  |
| 002     | Complete project report           | 2026-01-14 | 12:02:09 | Done         |
| 003     | Call mom for her birthday         | 2026-01-14 | 12:02:26 | Skipped      |
-----------------------------------------------------------------------------------
```

**Available Filters:**

| Filter                | Description                            |
| --------------------- | -------------------------------------- |
| `display`             | Show all tasks                         |
| `display completed`   | Show only completed tasks              |
| `display incompleted` | Show only incomplete/in-progress tasks |
| `display skipped`     | Show only skipped/cancelled tasks      |

---

### 3. Update Task

Change the status of an existing task.

**Syntax:**
```
update <task_number> <new_status>
update <task_id> <new_status>
```

**Examples:**

Update by task number:
```
update 1 Done
update 01 Completed
update 2 In Progress
```

Update by task ID:
```
update 0f212bdc-d9df-4ad2-a440-4509b5a008d6 Finished
```

**Output:**
```
Updated successfully
```

**Error Handling:**
- If the task number doesn't exist: `Task not found!`
- If missing status: `Missing parameter (eg. update 01 completed)`

---

### 4. Delete Task

Remove a specific task from your list.

**Syntax:**
```
delete <task_number>
delete <task_id>
```

**Examples:**
```
delete 1
delete 01
delete 0f212bdc-d9df-4ad2-a440-4509b5a008d6
```

**Output:**
```
Task deleted successfully!
```

---

### 5. Delete All Tasks

Clear all tasks from your list.

**Syntax:**
```
delete *
```

**Confirmation Flow:**
```
Enter command: delete *
This action will delete every saved task!
Do you want to continue?(y/n)
y
Successfully cleared all tasks!
```

> ⚠️ **Warning:** This action is irreversible! All tasks will be permanently deleted.

**To cancel deletion:**
```
Do you want to continue?(y/n)
n
Deletion process aborted!
```

---

### 6. Exit

Save all changes and close the application.

**Syntax:**
```
exit
```

**Output:**
```
Program terminated!
```

> 💡 **Note:** All your tasks are automatically saved when you exit. You can also close the terminal window directly—TaskCLI has a shutdown hook that saves your data.

---

## Task Statuses

TaskCLI recognizes a wide variety of status keywords, grouped into three categories:

### ✅ Completed (Green)

Your task is done! Use any of these status keywords:

| Natural Language | Short Form   | Action-based   |
| ---------------- | ------------ | -------------- |
| done             | complete     | mark as done   |
| completed        | finished     | task completed |
| resolved         | closed       | finish task    |
| achieved         | accomplished | all done       |
| handled          | shipped      | nailed it      |
| wrapped up       | sorted       | crushed it     |

**Example:**
```
update 1 Done
update 2 completed
update 3 shipped
```

### 🔄 In Progress (Blue)

Your task is still being worked on:

| Status        | Description           |
| ------------- | --------------------- |
| in progress   | Active work           |
| pending       | Waiting to start      |
| on hold       | Temporarily paused    |
| blocked       | Waiting for something |
| under review  | Being reviewed        |
| almost done   | Nearly complete       |
| working on it | Currently active      |

**Example:**
```
update 1 In Progress
update 2 on hold
update 3 waiting for approval
```

### ❌ Cannot Complete (Orange)

The task can't be done:

| Status       | Description             |
| ------------ | ----------------------- |
| cancelled    | Task was cancelled      |
| skipped      | Task was skipped        |
| invalid      | Task is no longer valid |
| rejected     | Task was rejected       |
| won't fix    | Decided not to do       |
| out of scope | Not part of objectives  |
| expired      | Deadline passed         |

**Example:**
```
update 1 cancelled
update 2 skipped
update 3 out of scope
```

---

## Display Filtering

TaskCLI allows you to filter your task view based on status categories:

### View All Tasks
```
display
```

### View Completed Tasks Only
```
display completed
```
Shows tasks with statuses like: done, completed, finished, shipped, etc.

### View Incomplete Tasks Only
```
display incompleted
```
Shows tasks with statuses like: in progress, pending, on hold, blocked, etc.

### View Skipped/Cancelled Tasks Only
```
display skipped
```
Shows tasks with statuses like: cancelled, skipped, rejected, invalid, etc.

---

## Data Storage

### Where Are My Tasks Stored?

Tasks are stored in a JSON file named `todos.json` in the project root directory.

### File Format

```json
[
  {
    "taskNo": "001",
    "id": "0f212bdc-d9df-4ad2-a440-4509b5a008d6",
    "task": "Buy groceries from the store",
    "date": "2026-01-14",
    "time": "12:01:50",
    "status": "In Progress"
  }
]
```

### Data Fields

| Field    | Description                             |
| -------- | --------------------------------------- |
| `taskNo` | Sequential task number (001, 002, etc.) |
| `id`     | Unique UUID for each task               |
| `task`   | Task description                        |
| `date`   | Date when task was created              |
| `time`   | Time when task was created              |
| `status` | Current status of the task              |

### Auto-Save Feature

TaskCLI automatically saves your data:
- When you type `exit`
- When you close the terminal window (Ctrl+C or closing the window)
- The app uses a shutdown hook to ensure data is saved even on unexpected termination

---

## Tips & Best Practices

### 🎯 Task Management Tips

1. **Be Descriptive**: Write clear task descriptions
   ```
   ❌ report
   ✅ Complete quarterly sales report for Q4 2025
   ```

2. **Update Regularly**: Keep task statuses current
   ```
   update 1 working on it
   update 1 almost done
   update 1 completed
   ```

3. **Use Natural Language**: TaskCLI understands many status phrases
   ```
   update 1 nailed it
   update 2 waiting for approval
   ```

### ⌨️ Command Shortcuts

| Instead of... | You can type... |
| ------------- | --------------- |
| `display`     | `display`       |
| `y`           | `yes`           |
| `n`           | `no`            |

### 📊 Workflow Suggestions

**Daily Workflow:**
1. Start your day with `display` to see all tasks
2. Use `display incompleted` to focus on what needs to be done
3. Update tasks as you complete them
4. End your day with `display completed` to see your achievements!

---

## Troubleshooting

### Common Issues

#### "java is not recognized as an internal or external command"

**Solution:** Java is not in your system PATH. 
- Add Java to your PATH environment variable, or
- Use the full path to Java: `"C:\Program Files\Java\jdk-17\bin\java.exe" -jar todo_app-0.1-SNAPSHOT.jar`

#### "Error: Unable to access jarfile"

**Solution:** Make sure you're in the correct directory.
```bash
cd path/to/todo_app
java -jar target/todo_app-0.1-SNAPSHOT.jar
```

#### Colors Not Displaying Correctly

**Solution:** Your terminal may not support ANSI colors.
- **Windows:** Use Windows Terminal, PowerShell 7+, or Git Bash
- **macOS/Linux:** Most terminals support ANSI by default

#### "No tasks to display!"

**Solution:** You haven't added any tasks yet. Use:
```
add My first task
```

#### Tasks Not Saving

**Solution:** Make sure you have write permissions in the directory. TaskCLI saves to `todos.json` in the current directory.

---

## FAQs

### Q: Can I edit the todos.json file directly?

**A:** Yes, but be careful to maintain valid JSON format. It's recommended to use the CLI commands instead.

### Q: Is there a limit to how many tasks I can add?

**A:** No hard limit! TaskCLI can handle hundreds of tasks efficiently.

### Q: Can I use this on multiple computers?

**A:** Yes! Simply copy the `todos.json` file between computers to sync your tasks.

### Q: How do I backup my tasks?

**A:** Copy the `todos.json` file to a safe location. That file contains all your task data.

### Q: Can I undo a task deletion?

**A:** Unfortunately, no. Task deletion is permanent. Consider using status updates instead of deleting.

### Q: What happens if I type the wrong task number?

**A:** TaskCLI will display "Task not found!" and you can try again.

---

## Quick Reference Card

| Action           | Command                    |
| ---------------- | -------------------------- |
| Add task         | `add <task description>`   |
| View all tasks   | `display`                  |
| View completed   | `display completed`        |
| View in-progress | `display incompleted`      |
| View skipped     | `display skipped`          |
| Update status    | `update <number> <status>` |
| Delete task      | `delete <number>`          |
| Delete all       | `delete *`                 |
| Exit             | `exit`                     |

---

<p align="center">
  <b>Happy Task Managing! 🎯</b>
</p>

<p align="center">
  <i>Built with ❤️ by DragonSlayer99</i>
</p>
