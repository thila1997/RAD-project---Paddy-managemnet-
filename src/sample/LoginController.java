package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

public class LoginController {

    @FXML
    private AnchorPane AnchorPane1;

    @FXML
    private JFXTextField emailTxt;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private Button backBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private JFXButton signupBtn;

    @FXML
    private Button clearBtn;

    //exit button
    Stage stage;
    public void logout(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setContentText("Do you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) AnchorPane1.getScene().getWindow();
            //System.out.println("successful");
            stage.close();
        }
    }

    @FXML
    void join(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (emailTxt.getText().isBlank() == false && passwordTxt.getText().isBlank() == false) {
            //loginmsgLbl.setText("You try to login");
            validateLogin();
        } else {
            JOptionPane.showMessageDialog(null, "please fill password and email address");
        }
    }

    @FXML
    public void validateLogin() throws SQLException, ClassNotFoundException {
//        databaseconnection connectNow= new databaseconnection();
//       Connection connectDB = connectNow.getConnection();
        DBHandler connectNow=new DBHandler();
        Connection connectDB=connectNow.getConnection();
       String verifyLogin = "SELECT * from admin WHERE email='"+emailTxt.getText() +"' AND password ='" +passwordTxt.getText() +"'";

       try {
           Statement statement= connectDB.createStatement();
           ResultSet queryResult = statement.executeQuery(verifyLogin);
           if (queryResult.next()) {
              // loginmsgLbl.setText("congrats");

               FXMLLoader loader=new FXMLLoader(getClass().getResource("MainPageController.fxml"));

               Stage stage=new Stage();
               stage.initStyle(StageStyle.TRANSPARENT);
               stage.setScene(new Scene(loader.load()));

               stage.setTitle("rad");
               stage.show();
               loginBtn.getScene().getWindow().hide();
           }else{
               JOptionPane.showMessageDialog(null, "please enter correct password email address");
           }



            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }

    //clear button
    public void clear(ActionEvent actionEvent) {
        emailTxt.clear();
        passwordTxt.clear();
    }

}
