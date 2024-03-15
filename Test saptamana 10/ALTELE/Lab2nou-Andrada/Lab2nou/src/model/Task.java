package model;

import java.util.Objects;

public abstract class Task {

    private String taskId;
    private String description;

    public String getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task(String _taskId, String _description) {
        taskId = _taskId;
        description = _description;
    }

    public abstract void execute();

    @Override
    public String toString() {
        return taskId + " " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task task = (Task) obj;
        return Objects.equals(getTaskId(), task.taskId) &&
                Objects.equals(getDescription(), task.description);

    }
    @Override
    public int hashCode(){
        return Objects.hash(taskId,description);
    }
}
