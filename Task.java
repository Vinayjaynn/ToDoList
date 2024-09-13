
class Task {
    // Fields
    String description;
    int priority;

    // Constructor
    Task(String description, int priority) {
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