package controllers;

import dboperations.BusinessDeveloperDatabaseOpration;
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
import users.Project;
import users.Task;
import users.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BusinessDeveloperDashboardController implements Initializable {
    // blow are all arraylist for use datatable of their concern type
    ObservableList<Project> projectList;
    ObservableList<Task> TaskList;
    private ObservableList < String > userTypes;
    private ObservableList <User> selectResource;
    private ObservableList < String > selectResourceRole;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BDNameLabel.setText(LoginUIController.getPassableUsername());
        BDLabel.setText("BD");
        pane_HomePage.setVisible(true);
        pane_BD_addNewProject.setVisible(false);
        pane_BD_clientRequirmentDetails.setVisible(false);
        loadResourceProjectsDataTable();
    }
// FX defined variable decleration
    @FXML
    private Label BDNameLabel;

    @FXML
    private Label BDLabel;

    @FXML
    private AnchorPane pane_HomePage;

    @FXML
    private AnchorPane pane_BD_clientRequirmentDetails;

    @FXML
    private TableView<Project> BD_ProjectListTable;

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
    private Label researchProposalStatusLabel;

    @FXML
    private TableView<Task> TableView_TaskDetails;

    @FXML
    private TableColumn<Task, String> col_taskID;

    @FXML
    private TableColumn<Task, String> col_TaskDetails;

    @FXML
    private TableColumn<Task, String> col_TaskResource;

    @FXML
    private TableColumn<Task, String> col_completionDate;

    @FXML
    private TableColumn<Task, String> col_TaskStatus;

    @FXML
    private Label ProjectIDLabel;

    @FXML
    private Label ProjectNameLabel;

    @FXML
    private TextArea txt_taskDetails;

    @FXML
    private ComboBox<User> comboBox_selectTaskResource;

    @FXML
    private DatePicker datePicker_taskLastDate;

    @FXML
    private AnchorPane pane_BD_addNewProject;

    @FXML
    private TextField txtBox_cost;

    @FXML
    private TextField txtBox_client;

    @FXML
    private Label projectUpdateStatusLable;

    @FXML
    private TextField txtBox_projectTitle;

    @FXML
    private Button btn_addNewProject;

    @FXML
    private TextArea txtBox_projectRequirment;

    @FXML
    private DatePicker txtBox_datepicker;

    @FXML
    private Label lbl_projectid;
    // btn click event for button to add new project
    @FXML
    void BtnClick_addNewProject(ActionEvent event) {
        System.out.println("AddProjectBtn Clicked : ");
        String id=lbl_projectid.getText();
        String client = txtBox_client.getText();
        String title = txtBox_projectTitle.getText();
        String cost = txtBox_cost.getText();
        String requirement = txtBox_projectRequirment.getText();
        String deliveryDate ="";
        if(txtBox_datepicker!=null)
        {
            deliveryDate = txtBox_datepicker.getValue().toString();
        }

        if(!(client.isEmpty() || title.isEmpty() || cost.isEmpty() || requirement.isEmpty() || deliveryDate.isEmpty())) {
            Project project = new Project("0", title, client, requirement, cost, deliveryDate);
            System.out.println("Project Cost: "+project.getCost()+"  Project Requirement: "+project.getRequirements());
            BusinessDeveloperDatabaseOpration dbOps = new BusinessDeveloperDatabaseOpration();
            if(dbOps.insertProject(project)) {
                projectList.clear();
                loadResourceProjectsDataTable();
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
                System.out.println("add Failed.");
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("DataBase Error");
                a.setContentText("Fail to Add Project");
                a.show();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Empty Input");
            a.setContentText("Some Input Fields are empty! Fill All");
            a.show();
        }
    }
    // btn click event for button to add new task
    @FXML
    void btnClicked_addProjectTaskDetails(ActionEvent event) {
        System.out.println("AddTaskBtn Clicked : ");
        String ProjectId=ProjectIDLabel.getText();
        String details = txt_taskDetails.getText();
        String taskResource = "";
        if(comboBox_selectTaskResource.getValue()!=null)
        {
            taskResource = comboBox_selectTaskResource.getValue().getID();
        }
        String CompDate ="";
        if(datePicker_taskLastDate.getValue()!=null)
        {
            CompDate = datePicker_taskLastDate.getValue().toString();
        }
        System.out.println(" ____"+CompDate+"___"+taskResource+"----"+details+"ProjectId   --"+ProjectId);
        if(!(ProjectId.isEmpty() || details.isEmpty() || taskResource.isEmpty() || CompDate.isEmpty() )) {
            Task task = new Task("0", details, taskResource, CompDate, "Pending");
            task.setTaskProject(ProjectId);
            BusinessDeveloperDatabaseOpration dbOps = new BusinessDeveloperDatabaseOpration();
            if(dbOps.insertTask(task)) {
                TaskList.clear();
                Project proj=new Project();
                proj.setID(ProjectId);
                loadProjectTaskDataTable(proj);
                txt_taskDetails.setText("");
                txtBox_datepicker.getEditor().clear();

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Success Message");
                a.setContentText("New Task Added Successfully");
                a.show();
            }else{
                System.out.println("add Failed.");
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("DataBase Error");
                a.setContentText("Fail to Add Task");
                a.show();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Empty Input");
            a.setContentText("Some Input Fields are empty! Fill All");
            a.show();
        }
    }
    // btn click event for button to signout
    @FXML
    void handleDashboardSignout(ActionEvent event) throws IOException {
        /*
         * Signing out will take user to login page
         * */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Signed Out!");
        alert.setContentText("Signed Out Successfully");

        Parent LoginUIParent = FXMLLoader.load(getClass().getResource("/gui/LoginUI.fxml"));
        Scene LoginUIScene = new Scene(LoginUIParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(LoginUIScene);
        window.show();
    }
    // btn click event for module project
    @FXML
    void navigateToProjectPane(ActionEvent event) {
        pane_HomePage.setVisible(false);
        pane_BD_addNewProject.setVisible(true);
        pane_BD_clientRequirmentDetails.setVisible(false);

    }
    // btn click event for button to open task module
    @FXML
    void navigateToTaskPane(ActionEvent event) {
        pane_HomePage.setVisible(false);
        pane_BD_addNewProject.setVisible(false);
        pane_BD_clientRequirmentDetails.setVisible(true);
    }
    // table click event for task table
    @FXML
    void onMouseClicked_TableView_TaskDetails(MouseEvent event) {

    }
    // table click event for Project list table
    @FXML
    void onMouseClicked_BD_ProjectListTable(MouseEvent event) {
        System.out.println("mouse Clicked Project Row Selected : ");
        Project project = BD_ProjectListTable.getSelectionModel().getSelectedItem();
        selectResourceCombobox(project);
        ProjectIDLabel.setText(project.getID());
        ProjectNameLabel.setText(project.getClient());
        loadProjectTaskDataTable(project);
    }
    // function to load project data
    public void loadResourceProjectsDataTable(){
        projectList=new BusinessDeveloperDatabaseOpration().getAllProject();
        BD_ProjectListTable.setItems(projectList);
        col_ProjectID.setCellValueFactory(new PropertyValueFactory("ID"));
        col_ResProjectTilte.setCellValueFactory(new PropertyValueFactory("Title"));
        col_ResProjCost.setCellValueFactory(new PropertyValueFactory("Cost"));
        col_ResDeliveryDate.setCellValueFactory(new PropertyValueFactory("DeliveryDate"));
        col_ResClient.setCellValueFactory(new PropertyValueFactory("Client"));
        col_ResProjRequirement.setCellValueFactory(new PropertyValueFactory("Requirements"));
    }
    // function to load task data
    public void loadProjectTaskDataTable(Project project){
        TaskList=new BusinessDeveloperDatabaseOpration().getProjectTasks(project);
        TableView_TaskDetails.setItems(TaskList);
        col_taskID.setCellValueFactory(new PropertyValueFactory("TaskID"));
        col_TaskDetails.setCellValueFactory(new PropertyValueFactory("TaskDetails"));
        col_TaskResource.setCellValueFactory(new PropertyValueFactory("TaskResource"));
        col_completionDate.setCellValueFactory(new PropertyValueFactory("TaskCompletionDate"));
        col_TaskStatus.setCellValueFactory(new PropertyValueFactory("TaskStatus"));
    }
    // function to load data
    private void selectResourceCombobox(Project project){
        System.out.println("Resource combo Called : ");
        selectResource= new BusinessDeveloperDatabaseOpration().getProjectResourceUser(project);
        comboBox_selectTaskResource.setItems(selectResource);
        comboBox_selectTaskResource.getSelectionModel().selectLast();
        comboBox_selectTaskResource.setConverter(new StringConverter<User>() {

            @Override
            public String toString(User object) {
                if(object!=null){return (object.getName()+"  ("+object.getID()+")");}else{return "Not Found";}
            }

            @Override
            public User fromString(String string) {
                return comboBox_selectTaskResource.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
        comboBox_selectTaskResource.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null)
                System.out.println("Selected airport: " + newval.getName()
                        + ". ID: " + newval.getID());
        });
    }
}
