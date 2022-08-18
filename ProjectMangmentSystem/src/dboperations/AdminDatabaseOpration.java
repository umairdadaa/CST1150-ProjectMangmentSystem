package dboperations;

import dbconnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import users.Project;
import users.Resource;
import users.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDatabaseOpration {
    public boolean insertUser(User user) {
        if(!exists(user)) {
            String queryForInserUser = String.format("INSERT INTO userinfo VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    user.getID(),
                    user.getName(),
                    user.getEmail(),
                    user.getContact(),
                    user.getAddress(),
                    user.getPassword(),
                    user.getUserType());

            /*
             * queryForUserTable takes signup information of the user and
             * inserts into corresponding table
             * Currently, the tables are: Student, Teacher, Chairman
             * */

            // executing queries
            System.out.println(queryForInserUser);
            Connection connection = DBConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(queryForInserUser);
            } catch (SQLException sqlE) {
                System.out.println("Invalid Credentials.");
                sqlE.printStackTrace();
                return false;
            }
            return true;
        }else{
            System.out.println("ID already Exist.");
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("DataBase Error");
            a.setContentText("User ID Already Exist! Plz Try with New Unique ID");
            a.show();
            return false;
        }
    }
    public boolean updateUser(User user) {
        String queryForUpdate = String.format("UPDATE userinfo SET  userName='%s', userEmail='%s',userContact='%s',userAddress='%s',userPassword='%s',userType='%s' WHERE userID='%s'",
                user.getName(),
                user.getEmail(),
                user.getContact(),
                user.getAddress(),
                user.getPassword(),
                user.getUserType(),
                user.getID());
        // executing queries
        System.out.println(queryForUpdate);
        Connection connection = DBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(queryForUpdate);
        } catch (SQLException sqlE){
            System.out.println("Update User Failed.");
            sqlE.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteUser(User user) {
        String queryForDelete = String.format("DELETE FROM userinfo WHERE userID='%s'",
                user.getID());
        // executing queries
        System.out.println(queryForDelete);
        Connection connection = DBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(queryForDelete);
        } catch (SQLException sqlE){
            System.out.println("Delete user Query Failed.");
            sqlE.printStackTrace();
            return false;
        }
        return true;
    }
    public User getUser(String userID) {
        String query = String.format("SELECT * FROM userinfo WHERE WHERE userID='%s')",
                userID);
        Connection connection = DBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                User user=new User(
                        resultSet.getString("userID"),
                        resultSet.getString("userName"),
                        resultSet.getString("userEmail"),
                        resultSet.getString("userContact"),
                        resultSet.getString("userAddress"),
                        resultSet.getString("userPassword"),
                        resultSet.getString("userType")
                );
                return user;
            }} catch (SQLException sqlE){
            System.out.println("getUser() Query Failed.");
            sqlE.printStackTrace();
            return null;
        }
        return null;
    }
    public boolean exists(User user) {
        String query = String.format("SELECT userID FROM userinfo WHERE userID='%s' ",
                user.getID());
        Connection connection = DBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return (resultSet.next());
        } catch (SQLException sqlE){
            System.out.println("Delete user Query Failed.");
            sqlE.printStackTrace();
            return false;
        }
    }
    public ObservableList<User> getAllUser()  {
        ObservableList<User> userList= FXCollections.observableArrayList();
        String query = String.format("SELECT * FROM userinfo");
        Connection connection = DBConnection.getConnection();
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

    // Database Operation for Project Management
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
    public boolean updateProject(Project project) {
        project.toString();
        String queryForUpdate = String.format("UPDATE projectinfo SET  projectClient='%s', projectTitle='%s',projectCost='%s',projectRequirement='%s',projectDeliveryDate='%s' WHERE projectID ='%s'",
                project.getClient(),
                project.getTitle(),
                project.getCost(),
                project.getRequirements(),
                project.getDeliveryDate(),
                project.getID());
        // executing queries
        System.out.println(queryForUpdate);
        Connection connection = DBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(queryForUpdate);
        } catch (SQLException sqlE){
            System.out.println("Update User Failed.");
            sqlE.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteProject(Project project) {
        String queryForDelete = String.format("DELETE FROM projectinfo WHERE projectID ='%s'",
                project.getID());
        // executing queries
        System.out.println(queryForDelete);
        Connection connection = DBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(queryForDelete);
        } catch (SQLException sqlE){
            System.out.println("Delete user Query Failed.");
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
        String query = String.format("SELECT projectID FROM projectinfo WHERE projectID='%s'",
                project.getID());
        System.out.println(query);
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

    // database opration for project Resources Management
    public ObservableList<Resource> getProjectResources(Project project)  {
        System.out.println("get Project Resource method");
        ObservableList<Resource> resList= FXCollections.observableArrayList();
        String query = String.format("SELECT * FROM project_resource INNER JOIN userinfo ON userinfo.userID=project_resource.resourceUser WHERE resourceProject='%s'",project.getID());
        Connection connection = DBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Resource res=new Resource(
                        resultSet.getString("resourceID"),
                        resultSet.getString("userName"),
                        resultSet.getString("userEmail"),
                        resultSet.getString("userType"),
                        resultSet.getString("resourceRole")
                );
                resList.add(res);
            }
            return resList;
        }catch (SQLException sqlE){
            System.out.println("getProjectResource() Query Failed.");
            sqlE.printStackTrace();
            return null;
        }
    }
    public boolean insertProjectResource(String UserID,String ProjectID,String Role) {
            String queryForInsertResource = String.format("INSERT INTO project_resource VALUES('0', '%s', '%s', '%s')",
                    UserID,
                    ProjectID,
                    Role);
            System.out.println(queryForInsertResource);
            Connection connection = DBConnection.getConnection();
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(queryForInsertResource);
            } catch (SQLException sqlE) {
                System.out.println("Invalid Credentials.");
                sqlE.printStackTrace();
                return false;
            }
            return true;
    }
    public boolean existsResource(Project project) {
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
    public boolean updateProjectResourceRole(Resource res) {
        String queryForUpdate = String.format("UPDATE project_resource SET  resourceRole='%s' WHERE resourceID  ='%s'",
                res.getResType(),
                res.getResID());
        // executing queries
        System.out.println(queryForUpdate);
        Connection connection = DBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(queryForUpdate);
        } catch (SQLException sqlE){
            System.out.println("Update Resource Role Failed.");
            sqlE.printStackTrace();
            return false;
        }
        return true;
    }
}
