import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// Manages a list of tasks and provides operations to manipulate them
class TaskManager {
    private final List<Task> tasks; // List to store tasks
    private int nextTaskId; // Counter for generating unique task IDs

    // Constructor to initialize task list and task ID counter
    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.nextTaskId = 1; // Initialize the counter
    }

    // Adds a new task to the list with a unique task ID
    public void addTask(String description, LocalDate dueDate, boolean highPriority) {
        Task task = new Task(nextTaskId++, description, dueDate, highPriority); // Assign the current value of nextTaskId and then increment it
        tasks.add(task);
    }

    // Marks a task as completed based on its task ID
    public void markTaskAsCompleted(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                task.setCompleted(true);
                return;
            }
        }
        System.out.println("Task with ID " + taskId + " not found.");
    }

    // Deletes a task based on its task ID
    public void deleteTask(int taskId) {
        tasks.removeIf(task -> task.getTaskId() == taskId);
    }

    // Displays details of all tasks in the list
    public void viewAllTasks() {
        for (Task task : tasks) {
            String priority = task.isHighPriority() ? "High Priority" : "Normal Priority"; // Display priority
            System.out.println("Task " + task.getTaskId() + ": " + task.getDescription() + " - Due: " + task.getDueDate() +
                    " - Completed: " + task.isCompleted() + " - Priority: " + priority);
        }
    }

    // Generates a report of tasks due today
    public List<Task> generateReportTasksDueToday(LocalDate today) {
        List<Task> tasksDueToday = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDueDate().isEqual(today)) {
                tasksDueToday.add(task);
            }
        }
        return tasksDueToday;
    }

    // Method to get tasks
    public List<Task> getTasks() {
        return tasks;
    }
}