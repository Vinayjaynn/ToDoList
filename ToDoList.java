import java.util.*;

public class ToDoList {
    // List to store tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0; // Initialize choice to avoid scope issues

        do {
            // Display menu
            System.out.println("\n\n\nTo-Do List Application");
            System.out.println("\n1. View Tasks");
            System.out.println("\n2. Add Task");
            System.out.println("\n3. Remove Task");
            System.out.println("\n4. Edit Task Priority");
            System.out.println("\n5. Exit");
            System.out.print("\nEnter your choice: \n");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
                System.out.print("\nEnter your choice: \n");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Process user choice
            switch (choice) {
                case 1:
                    viewTasks();
                    break;
                case 2:
                    addTask(scanner);
                    break;
                case 3:
                    removeTask(scanner);
                    break;
                case 4:
                    editTaskPriority(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.\n");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Display all tasks sorted by priority
    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your To-Do list is empty.\n");
        } else {
            Collections.sort(tasks, Comparator.comparingInt(task -> task.priority));
            System.out.println("Your To-Do List:\n");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    // Add a new task to the list
    private static void addTask(Scanner scanner) {
        System.out.print("Enter the task description: \n");
        String description = scanner.nextLine();

        int priority = -1;
        while (priority < 1 || priority > 10) {
            System.out.print("Enter the priority (1-10, with 1 being highest priority): \n");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 10.");
                scanner.next(); // Clear the invalid input
                System.out.print("Enter the priority (1-10, with 1 being highest priority): \n");
            }
            priority = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (priority < 1 || priority > 10) {
                System.out.println("Invalid priority! Please enter a value between 1 and 10.");
            }
        }

        tasks.add(new Task(description, priority));
        System.out.println("\nTask added: " + description + " with priority " + priority);
    }

    // Remove a task from the list
    private static void removeTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to remove.");
        } else {
            viewTasks();
            System.out.print("Enter the task number to remove: \n");

            int taskNumber = -1;
            while (taskNumber < 1 || taskNumber > tasks.size()) {
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid task number.");
                    scanner.next(); // Clear the invalid input
                    System.out.print("Enter the task number to remove: \n");
                }
                taskNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (taskNumber < 1 || taskNumber > tasks.size()) {
                    System.out.println("Invalid task number! Please try again.");
                }
            }

            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println("Task removed: " + removedTask.description);
        }
    }

    // Edit the priority of an existing task
    private static void editTaskPriority(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to edit.");
        } else {
            viewTasks();
            System.out.print("Enter the task number to edit the priority: \n");

            int taskNumber = -1;
            while (taskNumber < 1 || taskNumber > tasks.size()) {
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid task number.");
                    scanner.next(); // Clear the invalid input
                    System.out.print("Enter the task number to edit the priority: \n");
                }
                taskNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (taskNumber < 1 || taskNumber > tasks.size()) {
                    System.out.println("Invalid task number! Please try again.");
                }
            }

            Task task = tasks.get(taskNumber - 1);

            int newPriority = -1;
            while (newPriority < 1 || newPriority > 10) {
                System.out.print("Enter the new priority (1-10, with 1 being highest priority): \n");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 1 and 10.");
                    scanner.next(); // Clear the invalid input
                    System.out.print("Enter the new priority (1-10, with 1 being highest priority): \n");
                }
                newPriority = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (newPriority < 1 || newPriority > 10) {
                    System.out.println("Invalid priority! Please enter a value between 1 and 10.");
                }
            }

            task.priority = newPriority;
            System.out.println("Task updated: " + task.description + " with new priority " + newPriority);
        }
    }

    // Inner class to represent a task
    static class Task {
        // Fields
        private String description;
        private int priority;

        // Constructor
        public Task(String description, int priority) {
            this.description = description;
            this.priority = priority;
        }

        // Override toString method for formatted task output
        @Override
        public String toString() {
            return "[" + getPriorityLevel() + " Priority] " + description + " (Priority: " + priority + ")";
        }

        // Determine the priority level as a string
        public String getPriorityLevel() {
            if (priority >= 1 && priority <= 4) {
                return "High";
            } else if (priority >= 5 && priority <= 6) {
                return "Medium";
            } else {
                return "Low";
            }
        }
    }
}