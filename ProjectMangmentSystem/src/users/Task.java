package users;

public class Task {
    private String TaskID;
    private String TaskDetails;
    private String TaskResource;
    private String TaskCompletionDate;
    private String TaskStatus;
    private String TaskProject="";


    public Task() {
    }

    public Task(String taskID, String taskDetails, String taskResource, String taskCompletionDate, String taskStatus) {
        TaskID = taskID;
        TaskDetails = taskDetails;
        TaskResource = taskResource;
        TaskCompletionDate = taskCompletionDate;
        TaskStatus = taskStatus;
    }

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    public String getTaskDetails() {
        return TaskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        TaskDetails = taskDetails;
    }

    public String getTaskResource() {
        return TaskResource;
    }

    public void setTaskResource(String taskResource) {
        TaskResource = taskResource;
    }

    public String getTaskCompletionDate() {
        return TaskCompletionDate;
    }

    public void setTaskCompletionDate(String taskCompletionDate) {
        TaskCompletionDate = taskCompletionDate;
    }

    public String getTaskStatus() {
        return TaskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        TaskStatus = taskStatus;
    }

    public String getTaskProject() {
        return TaskProject;
    }

    public void setTaskProject(String taskProject) {
        TaskProject = taskProject;
    }
}
