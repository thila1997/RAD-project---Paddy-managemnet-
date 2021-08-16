package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Controller {

    @FXML
    private Button exitBtn;

    @FXML
    private Label lbl1;

    @FXML
    private AnchorPane scenePanel;

    @FXML
    private Button joinBtn;

    @FXML
    private Button alreadyBtn;

    @FXML
    private Button backBtn;




    Stage stage;
    public void logout(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setContentText("Do you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePanel.getScene().getWindow();
            //System.out.println("successful");
            stage.close();
        }
    }


    //reg page
    Stage stages = new Stage();
    public void join(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegistrationController.fxml"));
        stages.initStyle(StageStyle.TRANSPARENT);
        stages.setTitle("RAD");
        Scene scene= new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stages.setScene(scene);
        stages.show();

        joinBtn.getScene().getWindow().hide();

    }

    //login button
    Stage stag = new Stage();
    public void already(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StorageUpdateController.fxml"));
        stag.initStyle(StageStyle.TRANSPARENT);
        stag.setTitle("RAD");
        Scene scene= new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stag.setScene(scene);
        stag.show();

        alreadyBtn.getScene().getWindow().hide();

    }

    // back button
    Stage stg = new Stage();
    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StorageController.fxml"));
        stg.initStyle(StageStyle.TRANSPARENT);
        stg.setTitle("RAD");
        Scene scene= new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stg.setScene(scene);
        stg.show();

        backBtn.getScene().getWindow().hide();


    }




    @FXML
    void initialize() {




    }

}
