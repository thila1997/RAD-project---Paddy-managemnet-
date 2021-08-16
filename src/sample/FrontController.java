package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
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

import java.io.IOException;

public class FrontController {


    @FXML
    private Button exitBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private JFXButton signupBtn;

    @FXML
    private AnchorPane panel;

    //reg page
    Stage stag = new Stage();
    public void reg(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegistrationController.fxml"));
        stag.initStyle(StageStyle.TRANSPARENT);
        stag.setTitle("RAD");
        Scene scene= new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stag.setScene(scene);
        stag.show();

        signupBtn.getScene().getWindow().hide();

    }

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

    //login button
    Stage stages = new Stage();
    public void join(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginController.fxml"));
        stages.initStyle(StageStyle.TRANSPARENT);
        stages.setTitle("RAD");
        Scene scene= new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stages.setScene(scene);
        stages.show();

        loginBtn.getScene().getWindow().hide();

    }



}
