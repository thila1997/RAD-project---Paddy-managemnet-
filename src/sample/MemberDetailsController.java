package sample;

import database.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.PriceDetails;
import models.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MemberDetailsController implements Initializable {

    @FXML
    private AnchorPane panel;

    @FXML
    private TableView<Users> tblUser;

    @FXML
    private TableColumn<Users,String> colFirstName;

    @FXML
    private TableColumn<Users,String> colLastName;

    @FXML
    private TableColumn<Users,String> colNIC;

    @FXML
    private TableColumn<Users, String> colAddress;

    @FXML
    private TableColumn<Users,String> colContactNo;


    @FXML
    private Button addmemberBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button backBtn;

    //exit button
    Stage stage;
    public void logout(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setContentText("Do you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) panel.getScene().getWindow();
            //System.out.println("successful");
            stage.close();
        }
    }

    //reg page
    Stage stag = new Stage();
    public void addMember(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegistrationController.fxml"));
        stag.initStyle(StageStyle.TRANSPARENT);
        stag.setTitle("RAD");
        Scene scene= new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stag.setScene(scene);
        stag.show();

       addmemberBtn.getScene().getWindow().hide();

    }

    // back button
    Stage stages = new Stage();
    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPageController.fxml"));
        stages.initStyle(StageStyle.TRANSPARENT);
        stages.setTitle("RAD");
        Scene scene= new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stages.setScene(scene);
        stages.show();

        backBtn.getScene().getWindow().hide();


    }
    @FXML
    void initialize() {
        assert panel != null : "fx:id=\"panel\" was not injected: check your FXML file 'MemberDetailsController.fxml'.";
        assert tblUser != null : "fx:id=\"tblUser\" was not injected: check your FXML file 'MemberDetailsController.fxml'.";
        assert colFirstName != null : "fx:id=\"colFirstName\" was not injected: check your FXML file 'MemberDetailsController.fxml'.";
        assert colLastName != null : "fx:id=\"colLastName\" was not injected: check your FXML file 'MemberDetailsController.fxml'.";
        assert colNIC != null : "fx:id=\"colNIC\" was not injected: check your FXML file 'MemberDetailsController.fxml'.";
        assert colAddress != null : "fx:id=\"colAddress\" was not injected: check your FXML file 'MemberDetailsController.fxml'.";
        assert colContactNo != null : "fx:id=\"colContactNo\" was not injected: check your FXML file 'MemberDetailsController.fxml'.";
        assert addmemberBtn != null : "fx:id=\"addmemberBtn\" was not injected: check your FXML file 'MemberDetailsController.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'MemberDetailsController.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'MemberDetailsController.fxml'.";

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showUsers();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Users> getUsers() throws SQLException, ClassNotFoundException {
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        DBHandler con=new DBHandler();
        try (Connection conn = con.getConnection()) {

            String query = "SELECT user_first_name , user_last_name,user_address,user_contact_no,user_NIC FROM user";
            Statement st;
            ResultSet rs;

            try {

                st = conn.createStatement();
                rs = st.executeQuery(query);
                Users users;
                while (rs.next()) {
                    users = new Users(rs.getString("user_first_name"), rs.getString("user_last_name"),rs.getString("user_NIC"),rs.getString("user_address"),rs.getString("user_contact_no"));
                    usersList.add(users);
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
        return usersList;
    }

    public void showUsers() throws SQLException, ClassNotFoundException {

        ObservableList<Users> list = getUsers();

        colFirstName.setCellValueFactory(new PropertyValueFactory<Users, String>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Users, String>("lastName"));
        colNIC.setCellValueFactory(new PropertyValueFactory<Users, String>("NIC"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Users, String>("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<Users, String>("contactNo"));


        tblUser.setItems(list);
    }

}
