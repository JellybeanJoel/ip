package jenie.task;

import java.util.ArrayList;

/**
 * Manages the collection of tasks and provides search functionality.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     *
     * @param keyword The string to search for.
     * @return A TaskList containing only the matching tasks.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) { // Use the plural 'tasks' field
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Marks a task as done based on its index in the list.
     *
     * @param index The zero-based index of the task.
     * @return The updated Task object.
     */
    public Task markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task as not done based on its index in the list.
     *
     * @param index The zero-based index of the task.
     * @return The updated Task object.
     */
    public Task unmarkTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.unmarkAsDone();
        return task;
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
