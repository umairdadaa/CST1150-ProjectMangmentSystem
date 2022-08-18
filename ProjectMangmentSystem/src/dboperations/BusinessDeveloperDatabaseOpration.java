package dboperations;

import dbconnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import users.Project;
import users.Resource;
import users.Task;
import users.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BusinessDeveloperDatabaseOpration
{
    public boolean insertProject(Project project) {
        String queryForInsertProject = String.format("INSERT INTO projectinfo VALUES(0, '%s', '%s', '%s', '%s', '%s')",

                project.getClient(),
                project.getTitle(),
                project.getCost(),
                project.getRequirements(),
                project.getDeliveryDate());

        // executing queries
        System.out.println(queryForInsertProject);
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(queryForInsertProject);
        } catch (SQLException sqlE) {
            System.out.println("Invalid Credentials.");
            sqlE.printStackTrace();
            return false;
        }
        return true;

    }
    public Project getProject(String projectID) {
        String query = String.format("SELECT * FROM projectinfo WHERE projectID ='%s'",
                projectID);
        Connection connection = DBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                Project project=new Project(
                        resultSet.getString("projectID"),
                        resultSet.getString("projectClient"),
                        resultSet.getString("projectTitle"),
                        resultSet.getString("projectCost"),
                        resultSet.getString("projectRequirement"),
                        resultSet.getString("projectDeliveryDate")
                );
                return project;
            }} catch (SQLException sqlE){
            System.out.println("getUser() Query Failed.");
            sqlE.printStackTrace();
            return null;
        }
        return null;
    }
    public boolean existsProject(Project project) {
        String query = String.format("SELECT projectID FROM projectinfo WHERE projectID='%s' ",
                project.getID());
        Connection connection = DBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return (resultSet.next());
        } catch (SQLException sqlE){
            System.out.println("Project Exist Query Failed.");
            sqlE.printStackTrace();
            return false;
        }
    }
    public ObservableList<Project> getAllProject()  {
        ObservableList<Project> projectList= FXCollections.observableArrayList();
        String query = String.format("SELECT * FROM projectinfo");
        Connection connection = DBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Project project=new Project(
                        resultSet.getString("projectID"),
                        resultSet.getString("projectTitle"),
                        resultSet.getString("projectClient"),
                        resultSet.getString("projectRequirement"),
                        resultSet.getString("projectCost"),
                        resultSet.getString("projectDeliveryDate")
                );
                projectList.add(project);
            }
            return projectList;
        }catch (SQLException sqlE){
            System.out.println("getAllProject() Query Failed.");
            sqlE.printStackTrace();
            return null;
        }
    }
    public ObservableList<Task> getProjectTasks(Project project)  {
        ObservableList<Task> resList= FXCollections.observableArrayList();
        String query = String.format("SELECT * FROM task_details INNER JOIN userinfo ON userinfo.userID=task_details.Task_resource WHERE Task_project='%s'",project.getID());
        Connection connection = DBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Task task=new Task(
                        resultSet.getString("Task_id"),
                        resultSet.getString("Task_details"),
                        (resultSet.getString("userName")+" ("+resultSet.getString("Task_resource")+")"),
                        resultSet.getString("Task_completionDate"),
                        resultSet.getString("Task_Status")
                );
                resList.add(task);
            }
            return resList;
        }catch (SQLException sqlE){
            System.out.println("getProjectResource() Query Failed.");
            sqlE.printStackTrace();
            return null;
        }
    }
    public boolean insertTask(Task task) {
        String queryForInsertTask = String.format("INSERT INTO task_details VALUES(0, '%s', '%s', '%s', '%s', '%s')",
                task.getTaskProject(),
                task.getTaskDetails(),
                task.getTaskResource(),
                task.getTaskCompletionDate(),
                task.getTaskStatus());

        // executing queries
        System.out.println(queryForInsertTask);
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(queryForInsertTask);
        } catch (SQLException sqlE) {
            System.out.println("Invalid Credentials.");
            sqlE.printStackTrace();
            return false;
        }
        return true;

    }
    public ObservableList<User> getProjectResourceUser(Project project)  {
        ObservableList<User> userList= FXCollections.observableArrayList();
        String query = String.format("SELECT * FROM userinfo INNER JOIN project_resource ON project_resource.resourceUser=userinfo.userID WHERE project_resource.resourceProject='%s'",project.getID());
        Connection connection = DBConnection.getConnection();
        System.out.println(query);
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                User user=new User(
                        resultSet.getString("userID"),
                        resultSet.getString("userName"),
                        resultSet.getString("userEmail"),
                        resultSet.getString("userContact"),
                        resultSet.getString("userAddress"),
                        resultSet.getString("userPassword"),
                        resultSet.getString("userType")
                );
                userList.add(user);
            }
            return userList;
        }catch (SQLException sqlE){
            System.out.println("getAllUser() Query Failed.");
            sqlE.printStackTrace();
            return null;
        }
    }
}
