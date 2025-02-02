import java.util.*;

class Task {
    private String id;
    private String description;
    private int priority;

    public Task(String id, String description, int priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Task[ID=" + id + ", Description=" + description + ", Priority=" + priority + "]";
    }
}

class TaskManager {
    private PriorityQueue<Task> taskQueue;
    private Map<String, Task> taskMap;

    public TaskManager() {
        taskQueue = new PriorityQueue<>(Comparator.comparingInt(Task::getPriority).reversed());
        taskMap = new HashMap<>();
    }

    public void addTask(String id, String description, int priority) {
        if (taskMap.containsKey(id)) {
            System.out.println("Task with ID " + id + " already exists.");
            return;
        }
        Task newTask = new Task(id, description, priority);
        taskQueue.add(newTask);
        taskMap.put(id, newTask);
    }

    public void removeTask(String id) {
        if (!taskMap.containsKey(id)) {
            System.out.println("Task with ID " + id + " not found.");
            return;
        }
        Task taskToRemove = taskMap.get(id);
        taskQueue.remove(taskToRemove);
        taskMap.remove(id);
    }

    public Task getHighestPriorityTask() {
        return taskQueue.peek();
    }

    public void displayTasks() {
        for (Task task : taskQueue) {
            System.out.println(task);
        }
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.addTask("T1", "Fix bug in login module", 3);
        manager.addTask("T2", "Implement new feature", 5);
        manager.addTask("T3", "Update documentation", 2);

        System.out.println("Highest Priority Task: " + manager.getHighestPriorityTask());
        
        manager.removeTask("T2");
        System.out.println("After removing T2:");
        manager.displayTasks();
    }
}
