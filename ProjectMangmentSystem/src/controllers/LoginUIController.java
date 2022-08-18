package controllers;

import dboperations.LoginDatabaseOperation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.LoginInfo;
import users.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginUIController implements Initializable {

    @FXML
    private TextField loginUsernameField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Label loginStatusLabel;

    private static String passableUsername; // it has getter method

    public static String getPassableUsername() {
        return passableUsername;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleLoginButton(ActionEvent actionEvent) throws SQLException {
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();

        if (!username.isEmpty() && !password.isEmpty()){
            loginStatusLabel.setText(null);
            // User user = new User(name, email, username, password, "temp");
            LoginInfo logins = new LoginInfo(username, password);
            LoginDatabaseOperation LoginOps = new LoginDatabaseOperation();
            /*
            * If login credentials are valid, we change scene to corresponding user's dashboard
            * */
            User loginUser=LoginOps.verifyUserLogin(logins);
            if (loginUser!=null){
                System.out.println("Verified");
                loginStatusLabel.setText("Welcome");
                passableUsername = loginUser.getName();
                String userType = loginUser.getUserType();
                try {
                    Parent DashboardParent;

                    if (userType.equals("Admin")) { // user is "admin"
                        DashboardParent = FXMLLoader.load(getClass().getResource("/gui/AdminDashboard.fxml"));

                    }else if(userType.equals("Business Developer")) { // user is "Business Developer"
                        DashboardParent = FXMLLoader.load(getClass().getResource("/gui/BusinessDeveloperDashboard.fxml"));
                    }
                   else // user is "Back to Login"
                        DashboardParent = FXMLLoader.load(getClass().getResource("/gui/LoginUI.fxml"));

                    Scene StudentDashboardScene = new Scene(DashboardParent);

                    Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    window.setScene(StudentDashboardScene);
                    window.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("User insertion error!");
                loginStatusLabel.setText("Invalid credentials!");
            }
        }
        else{
            System.out.println("Empty fields!");
            loginStatusLabel.setText("Required fields can not be empty!");
        }
    }

    @FXML
    private void handleLoginResetButton(ActionEvent actionEvent) {
        loginUsernameField.clear();
        loginPasswordField.clear();
    }

}
