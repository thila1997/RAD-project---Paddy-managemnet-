package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import database.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationController {


    @FXML
    private AnchorPane scenePanel1;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextArea txtAddress;

    @FXML
    private JFXTextField txtContactNo;

    @FXML
    private Button backBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button registerBtn;

    @FXML
    private JFXTextField lastnametxt;

    String FName, LName, Address, NIC;
    String ContNo;

//clear button
    public void clear(ActionEvent actionEvent) {
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        txtNIC.clear();

    }

    //exit button
    Stage stage;
    public void logout(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setContentText("Do you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePanel1.getScene().getWindow();
            //System.out.println("successful");
            stage.close();
        }
    }

    //back button
    Stage stages = new Stage();
    public void join(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPageController.fxml"));
        stages.initStyle(StageStyle.TRANSPARENT);
        stages.setTitle("RAD");
        Scene scene= new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stages.setScene(scene);
        stages.show();

        backBtn.getScene().getWindow().hide();


    }


    //go to storage update form
    Stage stag = new Stage();
    public void storage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MemberDetailsController.fxml"));
        stag.initStyle(StageStyle.TRANSPARENT);
        stag.setTitle("RAD");
        Scene scene= new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stag.setScene(scene);
        stag.show();
        insertRecord();
      registerBtn.getScene().getWindow().hide();


    }


    @FXML
    void initialize() {

}




    private void insertRecord(){

        String query = "INSERT INTO user (user_first_name,user_last_name,user_NIC,user_address,user_contact_no) VALUES ('"+txtFirstName.getText()+"','"+txtLastName.getText()+"','"+txtNIC.getText()+"','"+txtAddress.getText()+"',"+txtContactNo.getText()+")";
        executeQuery(query);

        try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("MemberDetailsController.fxml"));

                Stage stage=new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(loader.load()));

                stage.setTitle("rad");
                stage.show();
                registerBtn.getScene().getWindow().hide();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }


    @FXML
    void valid(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (txtFirstName.getText().isBlank() == false && txtLastName.getText().isBlank() == false && txtNIC.getText().isBlank() == false && txtAddress.getText().isBlank() == false && txtContactNo.getText().isBlank() == false) {
            insertRecord();
            //registerBtn.setText("Successsfully added");
        } else {
            JOptionPane.showMessageDialog(null, "please fill First Name, Last Name, NIC no, Address and Contact No");
        }
    }


    private void executeQuery(String query) {
        DBHandler con=new DBHandler();
        Connection conn = con.getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }





}
