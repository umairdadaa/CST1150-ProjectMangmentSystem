package dboperations;

import dbconnection.DBConnection;
import users.LoginInfo;
import users.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDatabaseOperation {

    public User verifyUserLogin(LoginInfo logins) {
        /*
         * This query returns count value (supposed to be 1) of username and password matching row in UserInfo table
         * */
        String queryVerification = String.format("SELECT * FROM userinfo WHERE userEmail='%s' AND userPassword='%s'",
                logins.getUsername(),
                logins.getPassword());

        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryVerification);
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // login info does not exist in LOGINS table
    }
}
