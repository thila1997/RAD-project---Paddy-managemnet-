package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

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
import database.DBHandler;



public class PricedetailsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane panel;

    @FXML
    private Button exitBtn;

    @FXML
    private Button changePriceBtn;



    @FXML
    private TableView<PriceDetails> tblPriceDetails;

    @FXML
    private TableColumn<PriceDetails, String> colPaddyVariety;

    @FXML
    private TableColumn<PriceDetails, Double> colPrice;



    @FXML
    private Button backBtn;



    @FXML
    void initialize() {
        assert panel != null : "fx:id=\"panel\" was not injected: check your FXML file 'PricedetailsController.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'PricedetailsController.fxml'.";
        assert tblPriceDetails != null : "fx:id=\"tblPriceDetails\" was not injected: check your FXML file 'PricedetailsController.fxml'.";
        assert colPaddyVariety != null : "fx:id=\"colPaddyVariety\" was not injected: check your FXML file 'PricedetailsController.fxml'.";
        assert colPrice != null : "fx:id=\"colPrice\" was not injected: check your FXML file 'PricedetailsController.fxml'.";
        assert changePriceBtn != null : "fx:id=\"changePriceBtn\" was not injected: check your FXML file 'PricedetailsController.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'PricedetailsController.fxml'.";

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showPriceDetails();
        } catch (SQLException exception) {
            System.out.println("Sql error");
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    //go to paddy price change page
    Stage stages = new Stage();
    public void change(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PriceChangeController.fxml"));
        stages.initStyle(StageStyle.TRANSPARENT);
        stages.setTitle("RAD");
        Scene scene= new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stages.setScene(scene);
        stages.show();

        changePriceBtn.getScene().getWindow().hide();


    }
    // back button
    Stage stag = new Stage();
    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPageController.fxml"));
        stag.initStyle(StageStyle.TRANSPARENT);
        stag.setTitle("RAD");
        Scene scene= new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stag.setScene(scene);
        stag.show();

        backBtn.getScene().getWindow().hide();


    }



    public ObservableList<PriceDetails> getPriceDetails() throws SQLException, ClassNotFoundException {
        ObservableList<PriceDetails> priceDetailsList = FXCollections.observableArrayList();
        DBHandler con=new DBHandler();
        try (Connection conn = con.getConnection()) {

            String query = "SELECT paddy_type_name , paddy_type_price FROM paddy_type";
            Statement st;
            ResultSet rs;

            try {

                st = conn.createStatement();
                rs = st.executeQuery(query);
                PriceDetails priceDetails;
                while (rs.next()) {
                    priceDetails = new PriceDetails(rs.getString("paddy_type_name"), rs.getDouble("paddy_type_price"));
                    priceDetailsList.add(priceDetails);
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
        return priceDetailsList;
    }

    public void showPriceDetails() throws SQLException, ClassNotFoundException {

        ObservableList<PriceDetails> list = getPriceDetails();

        colPaddyVariety.setCellValueFactory(new PropertyValueFactory<PriceDetails, String>("paddyVariety"));
        colPrice.setCellValueFactory(new PropertyValueFactory<PriceDetails, Double>("paddyPrice"));


        tblPriceDetails.setItems(list);
    }


}


