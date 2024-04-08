import java.time.LocalDate;
import java.util.Scanner;
// Main class to run the Task Manager application
public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        // Main menu loop
        while (true) {
            // Displaying menu options
            System.out.println("Task Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Generate Report for Tasks Due Today");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            // Handling user's choice
            switch (choice) {
                case 1:
                    addTask(taskManager, scanner);
                    break;
                case 2:
                    markTaskAsCompleted(taskManager, scanner);
                    break;
                case 3:
                    deleteTask(taskManager, scanner);
                    break;
                case 4:
                    viewAllTasks(taskManager);
                    break;
                case 5:
                    generateReportTasksDueToday(taskManager);
                    break;
                case 6:
                    System.out.println("Exiting Task Manager...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    // Method to add a new task
    private static void addTask(TaskManager taskManager, Scanner scanner) {
        System.out.print("Enter Task Description: ");
        scanner.nextLine(); // Consume newline
        String description = scanner.nextLine();
        System.out.print("Enter Due Date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(scanner.next());
        System.out.print("Is this a high priority task? (yes/no): ");
        String priorityInput = scanner.next().toLowerCase();
        boolean highPriority = priorityInput.equals("yes") || priorityInput.equals("y"); // Set high priority flag
        taskManager.addTask(description, dueDate, highPriority);
        System.out.println("Task added successfully! Task ID is " + taskManager.getTasks().size());
    }
    // Method to mark a task as completed
    private static void markTaskAsCompleted(TaskManager taskManager, Scanner scanner) {
        System.out.print("Enter Task ID to Mark as Completed: ");
        int taskId = scanner.nextInt();
        boolean taskFound = false;
        for (Task task : taskManager.getTasks()) {
            if (task.getTaskId() == taskId) {
                taskManager.markTaskAsCompleted(taskId);
                System.out.println("Task marked as completed successfully!");
                taskFound = true;
                break;
            }
        }
        if (!taskFound) {
            System.out.println("Task with ID " + taskId + " not found.");
        }
    }
    // Method to delete a task
    private static void deleteTask(TaskManager taskManager, Scanner scanner) {
        System.out.print("Enter Task ID to Delete: ");
        int taskId = scanner.nextInt();
        boolean taskFound = false;
        for (Task task : taskManager.getTasks()) {
            if (task.getTaskId() == taskId) {
                taskManager.deleteTask(taskId);
                System.out.println("Task deleted successfully!");
                taskFound = true;
                break;
            }
        }
        if (!taskFound) {
            System.out.println("Task with ID " + taskId + " not found.");
        }
    }
    // Method to view all tasks
    private static void viewAllTasks(TaskManager taskManager) {
        System.out.println("Displaying all tasks:");
        taskManager.viewAllTasks();
    }

    // Method to generate a report of tasks due today
    private static void generateReportTasksDueToday(TaskManager taskManager) {
        System.out.println("Generating report:");
        taskManager.generateReportTasksDueToday(LocalDate.now()).forEach(task ->
                System.out.println("Task " + task.getTaskId() + ": " + task.getDescription() +
                        ", Due " + task.getDueDate()));
    }
}