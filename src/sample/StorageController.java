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


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import models.PaddyStorage;
import database.DBHandler;
import models.PriceDetails;
import models.Users;

public class StorageController implements Initializable {

    @FXML
    private AnchorPane panel;

    @FXML
    private Button exitBtn;

    @FXML
    private TableView<PaddyStorage> tblStorage;



//    @FXML
//    private TableColumn<PaddyStorage,Integer> colPaddyTypeId;
@FXML
private TableColumn<PaddyStorage,String> colPaddyTypeId;



    @FXML
    private TableColumn<PaddyStorage,Double> colTotalAmount;

    @FXML
    private Button updateBtn;

    @FXML
    private Button backBtn;

    //update page
    Stage stag = new Stage();
    public void update(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StorageUpdateController.fxml"));
        stag.initStyle(StageStyle.TRANSPARENT);
        stag.setTitle("RAD");
        Scene scene= new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stag.setScene(scene);
        stag.show();

        updateBtn.getScene().getWindow().hide();

    }

    //exit button
    Stage stage;
    public void logout(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setContentText("Do you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) panel.getScene().getWindow();

            stage.close();
        }
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
    public ObservableList<PaddyStorage> getPaddyStorage() throws SQLException, ClassNotFoundException {
        int paddyNameId;
        String paddyTypeName;
        ObservableList<PaddyStorage> PaddyStorageList = FXCollections.observableArrayList();
        DBHandler con=new DBHandler();
        try (Connection conn = con.getConnection()) {

            String query = "SELECT * FROM paddy_storage";
            Statement st;
            ResultSet rs;

            try {

                st = conn.createStatement();
                rs = st.executeQuery(query);
                PaddyStorage PaddyStorage;
                while (rs.next()) {
                    paddyNameId=rs.getInt("paddy_type_id");
                    if(paddyNameId==1){
                        paddyTypeName="Nadu";
                    }else if(paddyNameId==2){
                        paddyTypeName="Rathu Kakulu";

                    }else{
                        paddyTypeName="Sudu Kakulu";
                    }
                    PaddyStorage = new PaddyStorage(paddyTypeName, rs.getDouble("total_amount"));
                    PaddyStorageList.add(PaddyStorage);

                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
        return PaddyStorageList;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showPaddyStorage();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void showPaddyStorage() throws SQLException, ClassNotFoundException {

        ObservableList<PaddyStorage> list = getPaddyStorage();

        colPaddyTypeId.setCellValueFactory(new PropertyValueFactory<PaddyStorage, String>("paddyTypeName"));

        colTotalAmount.setCellValueFactory(new PropertyValueFactory<PaddyStorage, Double>("totalPaddyAmount"));


        tblStorage.setItems(list);
    }



}
