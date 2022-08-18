package controllers;

import dboperations.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import users.*;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    private String AdminName; // currently logged in admin
    // userlist to use in user datatable
    ObservableList<User> userList=new AdminDatabaseOpration().getAllUser();
    ObservableList<Project> projectList=new AdminDatabaseOpration().getAllProject();
    ObservableList<Resource> resList; // to use in resource drpdown list and below all
    private ObservableList < String > userTypes;
    private ObservableList < User > selectResource;
    private ObservableList < String > selectResourceRole;
    // method always call on start of form
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminNameLabel.setText(LoginUIController.getPassableUsername());
        AdminLabel.setText("Admin");
        pane_HomePage.setVisible(true);
        pane_userManagement.setVisible(false);
        pane_adminProjectResource.setVisible(false);
        pane_projectManagement.setVisible(false);
        userTypeCombobox();
        selectResourceCombobox();
        selectResourceRoleCombobox();
        loadUsersDataTable();
        loadProjectsDataTable();
        loadResourceProjectsDataTable();

    }
// all defined variable in FX form
    @FXML
    private Label adminNameLabel;

    @FXML
    private Label AdminLabel;

    @FXML
    private Label ProjectIDLabel;

    @FXML
    private Label ProjectNameLabel;

    @FXML
    private ComboBox <User> comboBox_selectResource;

    @FXML
    private ComboBox <String> comboBox_selectResourceRole;


    @FXML
    private Label lbl_projectid;

    @FXML
    private AnchorPane pane_HomePage;

    @FXML
    private AnchorPane pane_userManagement;

    @FXML
    private TextField txtBox_Email;

    @FXML
    private TextField txtBox_userID;

    @FXML
    private TextField txtBox_contactNumber;

    @FXML
    private TextField txtBox_Address;

    @FXML
    private TextField txtBox_Password;

    @FXML
    private ComboBox <String> comboBox_userType;

    @FXML
    private Label StatusLabel;

    @FXML
    private TextField txtBox_userName;

    @FXML
    private TableView<User> tableUser;

    @FXML
    private TableColumn<User, String> colID;

    @FXML
    private TableColumn<User, String> colName;

    @FXML
    private TableColumn<User, String> colEmail;

    @FXML
    private TableColumn<User, String> colContact;

    @FXML
    private TableColumn<User, String> colAddress;

    @FXML
    private TableColumn<User, String> colUserType;

    @FXML
    private TableColumn<User, String> colPassword;

    @FXML
    private Button btn_addNewProject;

    @FXML
    private AnchorPane pane_adminProjectResource;

    @FXML
    private TableView<?> ProjectsTableView;

    @FXML
    private Label researchProposalStatusLabel;

    @FXML
    private TableView<Resource> TeamMemberTableView;

    @FXML
    private TableColumn<Resource, String> col_res_id;

    @FXML
    private TableColumn<Resource, String> col_res_name;

    @FXML
    private TableColumn<Resource, String> col_res_type;

    @FXML
    private TableColumn<Resource, String> col_res_email;

    @FXML
    private TableColumn<Resource, String> col_res_role;

    @FXML
    private Label ProjectManagerLabel;

    @FXML
    private Label DeveloperNameLabel;

    @FXML
    private Label DeveloperDesignationLable;

    @FXML
    private ComboBox<String> comboBox_Role;

    @FXML
    private TableView<Project> resourceProjectListTable;

    @FXML
    private TableColumn<Project, String> col_ProjectID;

    @FXML
    private TableColumn<Project, String> col_ResProjectTilte;

    @FXML
    private TableColumn<Project, String> col_ResProjCost;

    @FXML
    private TableColumn<Project, String> col_ResDeliveryDate;

    @FXML
    private TableColumn<Project, String> col_ResClient;

    @FXML
    private TableColumn<Project, String> col_ResProjRequirement;
    @FXML
    private AnchorPane pane_projectManagement;

    @FXML
    private TextField txtBox_cost;

    @FXML
    private TextField txtBox_client;

    @FXML
    private Label projectUpdateStatusLable;

    @FXML
    private TextField txtBox_projectTitle;

    @FXML
    private TextArea txtBox_projectRequirment;

    @FXML
    private DatePicker txtBox_datepicker;

    @FXML
    private TableView<Project> table_projectData;

    @FXML
    private TableColumn<Project, String> colID1;

    @FXML
    private TableColumn<Project, String> col_projectTilte;

    @FXML
    private TableColumn<Project, String> col_cost;

    @FXML
    private TableColumn<Project, String> col_deliveryDate;

    @FXML
    private TableColumn<Project, String> col_requirement;

    @FXML
    private TableColumn<Project, String> col_client;
// btn click event for delete the project
    @FXML
    void BtnClick_DeleteProject(ActionEvent event) {
        System.out.println("DeleteProjectBtn Clicked : ");
        btn_addNewProject.setDisable(false);
        String id=ProjectIDLabel.getText();
        String client = txtBox_client.getText();
        String title = txtBox_projectTitle.getText();
        String cost = txtBox_cost.getText();
        String requirement = txtBox_projectRequirment.getText();
        String deliveryDate = txtBox_datepicker.getValue().toString();

        if(!(client.isEmpty() || title.isEmpty() || cost.isEmpty() || requirement.isEmpty() || deliveryDate.isEmpty())) {
            Project project = new Project(id, title, client, requirement, cost, deliveryDate);
            AdminDatabaseOpration dbOps = new AdminDatabaseOpration();
            if(dbOps.existsProject(project)) {
                dbOps.deleteProject(project);
                projectList.clear();
                projectList = new AdminDatabaseOpration().getAllProject();
                loadProjectsDataTable();
                txtBox_client.setText("");
                txtBox_projectTitle.setText("");
                txtBox_cost.setText("");
                txtBox_projectRequirment.setText("");
                txtBox_datepicker.getEditor().clear();

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Success Message");
                a.setContentText("Project Deleted Successfully");
                a.show();
            }
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("First Select Project From Table");
                a.show();
            }

    }
    // btn click event for button to add  the project
    @FXML
    void BtnClick_addNewProject(ActionEvent event) {
        System.out.println("AddProjectBtn Clicked : ");
        String id=lbl_projectid.getText();
        String client = txtBox_client.getText();
        String title = txtBox_projectTitle.getText();
        String cost = txtBox_cost.getText();
        String requirement = txtBox_projectRequirment.getText();
        String deliveryDate ="";
        if(txtBox_datepicker.getValue()!=null)
        {
             deliveryDate = txtBox_datepicker.getValue().toString();
        }

        if(!(client.isEmpty() || title.isEmpty() || cost.isEmpty() || requirement.isEmpty() || deliveryDate.isEmpty())) {
            Project project = new Project("0", title, client, requirement, cost, deliveryDate);
            System.out.println("Project Cost: "+project.getCost()+"  Project Requirement: "+project.getRequirements());
            AdminDatabaseOpration dbOps = new AdminDatabaseOpration();
            if(!dbOps.existsProject(project)) {
                dbOps.insertProject(project);
                projectList.clear();
                projectList=new AdminDatabaseOpration().getAllProject();
                loadProjectsDataTable();
                txtBox_client.setText("");
                txtBox_projectTitle.setText("");
                txtBox_cost.setText("");
                txtBox_projectRequirment.setText("");
                txtBox_datepicker.getEditor().clear();

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Success Message");
                a.setContentText("New Project Added Successfully");
                a.show();
            }else{
                System.out.println("ID already Exist.");
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("DataBase Error");
                a.setContentText("Project ID Already Exist! Plz Try with New Unique ID");
                a.show();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Empty Input");
            a.setContentText("Some Input Fields are empty! Fill All");
            a.show();
        }
    }
    // btn click event for update the project
    @FXML
    void BtnClick_updateProject(ActionEvent event) {
        System.out.println("Update Project Btn Clicked : ");
        btn_addNewProject.setDisable(false);
        String id=ProjectIDLabel.getText();
        String client = txtBox_client.getText();
        String title = txtBox_projectTitle.getText();
        String cost = txtBox_cost.getText();
        String requirement = txtBox_projectRequirment.getText();
        String deliveryDate = txtBox_datepicker.getValue().toString();

        if(!(client.isEmpty() || title.isEmpty() || cost.isEmpty() || requirement.isEmpty() || deliveryDate.isEmpty())) {
            Project project = new Project(id, title, client, requirement, cost, deliveryDate);
            AdminDatabaseOpration dbOps = new AdminDatabaseOpration();
            if(dbOps.existsProject(project)) {
                dbOps.updateProject(project);
                projectList.clear();
                projectList = new AdminDatabaseOpration().getAllProject();
                loadProjectsDataTable();
                txtBox_client.setText("");
                txtBox_projectTitle.setText("");
                txtBox_cost.setText("");
                txtBox_projectRequirment.setText("");
                txtBox_datepicker.getEditor().clear();

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Success Message");
                a.setContentText("New Project Added Successfully");
                a.show();
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Database Error");
                a.setContentText("Project update Fails");
                a.show();
            }
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("Select a Project to Update First");
                a.show();
            }

    }
    // btn click event for button save in Resources module to change the role of user
    @FXML
    void SaveProjectResourceRoleChanged(ActionEvent event) {
        Resource res = TeamMemberTableView.getSelectionModel().getSelectedItem();
        res.setResType(comboBox_Role.getValue());
        AdminDatabaseOpration dbOps=new AdminDatabaseOpration();
        if( dbOps.updateProjectResourceRole(res))
        {
            Project project = resourceProjectListTable.getSelectionModel().getSelectedItem();
            resList.clear();
            loadProjectResourcesDataTable(project);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Success Message");
            a.setContentText("Project Resource  Role Updated Successfully");
            a.show();
        }

    }
    // btn click event for button to add new user
    @FXML
    void btn_addNewUser(ActionEvent event) {
        System.out.println("AddBtn Clicked : ");
        String id = txtBox_userID.getText();
        String name = txtBox_userName.getText();
        String email = txtBox_Email.getText();
        String contact = txtBox_contactNumber.getText();
        String address = txtBox_Address.getText();
        String password = txtBox_Password.getText();
        String userType = comboBox_userType.getValue();
        if(!(id.isEmpty() || name.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty() || password.isEmpty() || userType.isEmpty())) {
            User user = new User(id, name, email, contact, address, password, userType);
            AdminDatabaseOpration dbOps = new AdminDatabaseOpration();
            if(!dbOps.exists(user)) {
            dbOps.insertUser(user);
            userList.clear();
            userList=new AdminDatabaseOpration().getAllUser();
            loadUsersDataTable();
            txtBox_userID.setText("");
            txtBox_userName.setText("");
            txtBox_Email.setText("");
            txtBox_contactNumber.setText("");
            txtBox_Address.setText("");
            txtBox_Password.setText("");

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Success Message");
            a.setContentText("New User Added Successfully");
            a.show();
            }else{
                System.out.println("ID already Exist.");
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("DataBase Error");
                a.setContentText("User ID Already Exist! Plz Try with New Unique ID");
                a.show();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Empty Input");
            a.setContentText("Some Input Fields are empty! Fill All");
            a.show();
        }

    }
    // btn click event for button to delete the  user
    @FXML
    void btn_deleteUser(ActionEvent event) {
        String id = txtBox_userID.getText();
        String name = txtBox_userName.getText();
        String email = txtBox_Email.getText();
        String contact = txtBox_contactNumber.getText();
        String address = txtBox_Address.getText();
        String password = txtBox_Password.getText();
        String userType = comboBox_userType.getValue();
        if(!(id.isEmpty() || name.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty() || password.isEmpty() || userType.isEmpty())) {
            User user = new User(id, name, email, contact, address, password, userType);
            AdminDatabaseOpration dbOps = new AdminDatabaseOpration();
            dbOps.deleteUser(user);
            userList.clear();
            userList=new AdminDatabaseOpration().getAllUser();
            loadUsersDataTable();
            txtBox_userID.setEditable(true);
            txtBox_userID.setText("");
            txtBox_userName.setText("");
            txtBox_Email.setText("");
            txtBox_contactNumber.setText("");
            txtBox_Address.setText("");
            txtBox_Password.setText("");

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Delete Message");
            a.setContentText("User Deleted Successfully");
            a.show();
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Empty Input");
            a.setContentText("Some Input Fields are empty! Fill All");
            a.show();
        }
    }
    // btn click event for button to update the  user
    @FXML
    void btn_updateUser(ActionEvent event) {

        String id = txtBox_userID.getText();
        String name = txtBox_userName.getText();
        String email = txtBox_Email.getText();
        String contact = txtBox_contactNumber.getText();
        String address = txtBox_Address.getText();
        String password = txtBox_Password.getText();
        String userType = comboBox_userType.getValue();
        if(!(id.isEmpty() || name.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty() || password.isEmpty() || userType.isEmpty())) {
            User user = new User(id, name, email, contact, address, password, userType);
            AdminDatabaseOpration dbOps = new AdminDatabaseOpration();
            dbOps.updateUser(user);
            userList.clear();
            userList=new AdminDatabaseOpration().getAllUser();
            loadUsersDataTable();
            txtBox_userID.setEditable(true);
            txtBox_userID.setText("");
            txtBox_userName.setText("");
            txtBox_Email.setText("");
            txtBox_contactNumber.setText("");
            txtBox_Address.setText("");
            txtBox_Password.setText("");

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Success Message");
            a.setContentText("User Updated Successfully");
            a.show();
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Empty Input");
            a.setContentText("Some Input Fields are empty! Fill All");
            a.show();
        }
    }

    // btn click event for button to add new resource in selected project in project resource module
    @FXML
    void btnClicked_addResource(ActionEvent event) {
        User user=comboBox_selectResource.getSelectionModel().getSelectedItem();
        String ProjectID=ProjectIDLabel.getText();
        String UserID=user.getID();
        String Role=comboBox_selectResourceRole.getValue();
        AdminDatabaseOpration dbOps=new AdminDatabaseOpration();
        if( dbOps.insertProjectResource(UserID, ProjectID, Role)) {
            Project project = resourceProjectListTable.getSelectionModel().getSelectedItem();
            resList.clear();
            loadProjectResourcesDataTable(project);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Success Message");
            a.setContentText("New Project Resource Added Successfully");
            a.show();
        }else
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Failed Message");
                a.setContentText(" Project Resource Failed");
                a.show();
            }
    }
    // btn click event for button to logout from system
    @FXML
    void handleDashboardSignout(ActionEvent event) throws IOException {
        /*
         * Signing out will take user to login page
         * */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Signed Out!");
        alert.setContentText("Signed Out Successfully");

        alert.show();
        Parent LoginUIParent = FXMLLoader.load(getClass().getResource("/gui/LoginUI.fxml"));
        Scene LoginUIScene = new Scene(LoginUIParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(LoginUIScene);
        window.show();
    }
    // btn click event for button to add new user
    @FXML
    void navigateToProjectManagementPane(ActionEvent event) {
        pane_HomePage.setVisible(false);
        pane_userManagement.setVisible(false);
        pane_adminProjectResource.setVisible(false);
        pane_projectManagement.setVisible(true);

    }
    // btn click event for button to nagvigate to project management module
    @FXML
    void navigateToResourceManagementPane(ActionEvent event) {
        pane_HomePage.setVisible(false);
        pane_userManagement.setVisible(false);
        pane_adminProjectResource.setVisible(true);
        pane_projectManagement.setVisible(false);
        loadResourceProjectsDataTable();
    }
    // btn click event for button to nagvigate to User management module
    @FXML
    void navigateToUserManagementPane(ActionEvent event) {
        pane_HomePage.setVisible(false);
        pane_userManagement.setVisible(true);
        pane_adminProjectResource.setVisible(false);
        pane_projectManagement.setVisible(false);
    }
    //  table click event for table to select user in Table to update or delete
    @FXML
    void onMouseClickedRowSelection(MouseEvent event) {
        System.out.println("mouse Clicked Row Selected : ");
        User user = tableUser.getSelectionModel().getSelectedItem();
        txtBox_userID.setText(user.getID());
        txtBox_userID.setEditable(false);
        txtBox_userName.setText(user.getName());
        txtBox_Email.setText(user.getEmail());
        txtBox_contactNumber.setText(user.getContact());
        txtBox_Address.setText(user.getAddress());
        txtBox_Password.setText(user.getPassword());
        comboBox_userType.setValue(user.getUserType());
    }
    // table click event for button to select project in Table to update or delete
    @FXML
    void onMouseClicked_resourceProjectListTable(MouseEvent event) {
        System.out.println("mouse Clicked Project Row Selected : ");
        Project project = resourceProjectListTable.getSelectionModel().getSelectedItem();
        ProjectIDLabel.setText(project.getID());
        ProjectNameLabel.setText(project.getClient());
        loadProjectResourcesDataTable(project);
    }
    // table click event for button to select resource  in Table  to change role
    @FXML
    void onMouseClicked_TeamMemberTableView(MouseEvent event) {
        System.out.println("mouse Clicked TeamMeber Row Selected : ");
        Resource res = TeamMemberTableView.getSelectionModel().getSelectedItem();
        DeveloperNameLabel.setText(res.getResName());
        DeveloperDesignationLable.setText(res.getUserType());
        comboBox_Role.setValue(res.getResType());

    }
    // btn click event for button to select project in Table to update or delete
    @FXML
    void onMouseClickedProjectDataTable(MouseEvent event) throws ParseException {
        System.out.println("mouse Clicked Project Row Selected : ");
        btn_addNewProject.setDisable(true);
        Project project = table_projectData.getSelectionModel().getSelectedItem();
        ProjectIDLabel.setText(project.getID());
        txtBox_client.setText(project.getClient());
        txtBox_projectTitle.setText(project.getTitle());
        txtBox_cost.setText(project.getCost());
        txtBox_projectRequirment.setText(project.getRequirements());
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy");
        //formatter = formatter.withLocale( Locale.US );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        //LocalDate date = LocalDate.parse(project.getDeliveryDate(), formatter);
        LocalDate date = LocalDate.parse(project.getDeliveryDate());
        txtBox_datepicker.setValue(date);
    }
    // function to load data in user table
    public void loadUsersDataTable(){
        tableUser.setItems(userList);
        colID.setCellValueFactory(new PropertyValueFactory("ID"));
        colName.setCellValueFactory(new PropertyValueFactory("Name"));
        colEmail.setCellValueFactory(new PropertyValueFactory("Email"));
        colContact.setCellValueFactory(new PropertyValueFactory("Contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        colUserType.setCellValueFactory(new PropertyValueFactory("UserType"));
        colPassword.setCellValueFactory(new PropertyValueFactory("Password"));
    }
    // function to load data in project table
    public void loadProjectsDataTable(){
        table_projectData.setItems(projectList);
        colID1.setCellValueFactory(new PropertyValueFactory("ID"));
        col_projectTilte.setCellValueFactory(new PropertyValueFactory("Title"));
        col_cost.setCellValueFactory(new PropertyValueFactory("Cost"));
        col_deliveryDate.setCellValueFactory(new PropertyValueFactory("DeliveryDate"));
        col_client.setCellValueFactory(new PropertyValueFactory("Client"));
        col_requirement.setCellValueFactory(new PropertyValueFactory("Requirements"));
    }
    // function to load data in project table
    public void loadResourceProjectsDataTable(){
        resourceProjectListTable.setItems(projectList);
        col_ProjectID.setCellValueFactory(new PropertyValueFactory("ID"));
        col_ResProjectTilte.setCellValueFactory(new PropertyValueFactory("Title"));
        col_ResProjCost.setCellValueFactory(new PropertyValueFactory("Cost"));
        col_ResDeliveryDate.setCellValueFactory(new PropertyValueFactory("DeliveryDate"));
        col_ResClient.setCellValueFactory(new PropertyValueFactory("Client"));
        col_ResProjRequirement.setCellValueFactory(new PropertyValueFactory("Requirements"));
    }
    // function to load data in usertype dropdown menu
    private void userTypeCombobox(){
        userTypes = FXCollections.observableArrayList("Admin", "Project Manager", "Business Developer","Project Developer");
        comboBox_userType.setItems(userTypes);
        comboBox_userType.getSelectionModel().selectLast();
    }
    // function to load data in Resource Name dropdown menu in resource module
    private void selectResourceCombobox(){
        selectResource= new AdminDatabaseOpration().getAllUser();
        comboBox_selectResource.setItems(selectResource);
        comboBox_selectResource.getSelectionModel().selectLast();
        comboBox_selectResource.setConverter(new StringConverter<User>() {

            @Override
            public String toString(User object) {
                return (object.getName()+"  ("+object.getUserType()+")");
            }

            @Override
            public User fromString(String string) {
                return comboBox_selectResource.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
        comboBox_selectResource.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null)
                System.out.println("Selected airport: " + newval.getName()
                        + ". ID: " + newval.getID());
        });
    }
    // function to load data in Resourse Role dropdown menu resource module
    private void selectResourceRoleCombobox(){
        selectResourceRole = FXCollections.observableArrayList("Project Manager", "GUI Developer","Backend Developer","DataBase Developer","QA/Testing");
        comboBox_selectResourceRole.setItems(selectResourceRole);
        comboBox_selectResourceRole.getSelectionModel().selectFirst();
        comboBox_Role.setItems(selectResourceRole);
        comboBox_Role.getSelectionModel().selectFirst();
    }
    // function to load resource data table in resource module
    public void loadProjectResourcesDataTable(Project project){
        resList=new AdminDatabaseOpration().getProjectResources(project);
        TeamMemberTableView.setItems(resList);
        col_res_id.setCellValueFactory(new PropertyValueFactory("ResID"));
        col_res_name.setCellValueFactory(new PropertyValueFactory("ResName"));
        col_res_type.setCellValueFactory(new PropertyValueFactory("UserType"));
        col_res_email.setCellValueFactory(new PropertyValueFactory("ResEmail"));
        col_res_role.setCellValueFactory(new PropertyValueFactory("ResType"));
    }
}
