package sample;

import com.jfoenix.controls.JFXTextField;
import database.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Users;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StorageUpdateController implements Initializable {

    @FXML
    private AnchorPane panel;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private JFXTextField paddyAmountTxt;

    @FXML
    private Button updateBtn;

    @FXML
    private Button seenowBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button clrBtn;

    @FXML
    private JFXTextField memberNICtxt;

    @FXML
    private JFXTextField PriceperTxt1;

    @FXML
    private JFXTextField totalPriceTxt;

    @FXML
    private Button calculateBtn;

    @FXML
    private Button mainMenuBtn;

    String  pprice;
    String amount;
    Double amountDouble;
    double ppriceDouble;
    double totalamount;
    int paddy_id;
    Double previousAmount;
    String ptype;
    String NIC;
    int userId;
    Double userStorage;

    //clear button
    public void Clear(ActionEvent actionEvent) {
        combo.setValue(" ");
        memberNICtxt.clear();
        paddyAmountTxt.clear();
        totalPriceTxt.clear();
        PriceperTxt1.clear();
    }

    public void calculate(ActionEvent actionEvent) {
        boolean isVaild=validUser();
        if(isVaild) {


            ptype = combo.getValue();


            amount = paddyAmountTxt.getText();
            amountDouble = Double.parseDouble(amount);

            DBHandler con = new DBHandler();
            try (Connection conn = con.getConnection()) {

                String query = "SELECT * from paddy_type where paddy_type_name='" + ptype + "'";

                Statement st;
                ResultSet rs;

                try {

                    st = conn.createStatement();
                    rs = st.executeQuery(query);

                    while (rs.next()) {

                        pprice = rs.getString("paddy_type_price");
                        paddy_id = rs.getInt("paddy_type_id");
                        PriceperTxt1.setText(pprice);
                        ppriceDouble = Double.parseDouble(pprice);
                        totalamount = ppriceDouble * amountDouble;
                        totalPriceTxt.setText(Double.toString(totalamount));


                    }


                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

        }
    }

    private boolean validUser() {
        NIC=memberNICtxt.getText();

        DBHandler con=new DBHandler();
        try (Connection conn = con.getConnection()) {

            String query = "SELECT * from user where user_NIC='"+NIC+"'";

            Statement st;
            ResultSet rs;

            try {

                st = conn.createStatement();
                rs = st.executeQuery(query);

                if (rs.next())
                {
                    userId=rs.getInt("user_id");

                    return true;
//

                }else{
                    JOptionPane.showMessageDialog(null, "please Enter valid NIC number");
                    return false;
                }



            } catch (Exception ex) {

                ex.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public void update(ActionEvent actionEvent) {

        DBHandler con=new DBHandler();
        try (Connection conn = con.getConnection()) {

            String query = "SELECT * from paddy_storage where paddy_type_id='"+paddy_id+"'";

            Statement st;
            ResultSet rs;

            try {

                st = conn.createStatement();
                rs = st.executeQuery(query);

                while (rs.next())
                {
                    System.out.println("paddy details in update");

                    previousAmount=rs.getDouble("total_amount");

                    previousAmount=previousAmount+amountDouble;


                    updateRecord();
                    updateUserRecord();


                }



            } catch (Exception ex) {

                ex.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }


    private void updateRecord(){




        String query="UPDATE paddy_storage SET total_amount="+previousAmount+ "where paddy_type_id='"+paddy_id+"'";

        executeQuery(query);



    }

    private void updateUserRecord() {
        Double userStorageAmount;
        DBHandler con=new DBHandler();
        try (Connection conn = con.getConnection()) {


            String query="SELECT * from user_paddy_storage WHERE user_id='"+userId +"' AND paddy_type_id ='" +paddy_id +"'";

            Statement st;
            ResultSet rs;

            try {

                st = conn.createStatement();
                rs = st.executeQuery(query);

                if(rs.next())
                {


                    updateUserStorageRecord();
                }else{

                    insertUserStorageRecord();
                }



            } catch (Exception ex) {

                ex.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }



    }

    private void insertUserStorageRecord() {

        String query = "INSERT INTO user_paddy_storage (user_id,paddy_type_id,amount) VALUES ("+userId+","+paddy_id+","+amountDouble+")";
        executeQuery(query);

    }

    private void updateUserStorageRecord() {


        DBHandler con=new DBHandler();
        try (Connection conn = con.getConnection()) {

            String query="SELECT * from user_paddy_storage WHERE user_id='"+userId +"' AND paddy_type_id ='" +paddy_id +"'";

            Statement st;
            ResultSet rs;

            try {

                st = conn.createStatement();
                rs = st.executeQuery(query);

                while (rs.next())
                {

                    userStorage=rs.getDouble("amount");

                    userStorage=userStorage+amountDouble;



                    updateUserStorageRecordQuery();


                }



            } catch (Exception ex) {

                ex.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }


    }

    private void updateUserStorageRecordQuery() {

        String query="UPDATE user_paddy_storage SET amount="+userStorage+" where user_id="+userId+" AND paddy_type_id="+paddy_id;
        executeQuery(query);
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







    // to go storage page
    Stage stag = new Stage();
    public void seenow(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StorageController.fxml"));
        stag.initStyle(StageStyle.TRANSPARENT);
        stag.setTitle("RAD");
        Scene scene= new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stag.setScene(scene);
        stag.show();

        seenowBtn.getScene().getWindow().hide();

    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combo.getItems().add("Rathu Kakulu");
        combo.getItems().add("Sudu Kakulu");
        combo.getItems().add("Nadu");
        combo.setPromptText("Choose One");

    }

    //go to main menu page
    Stage stages = new Stage();

    public void main(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPageController.fxml"));
        stages.initStyle(StageStyle.TRANSPARENT);
        stages.setTitle("RAD");
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stages.setScene(scene);
        stages.show();

        mainMenuBtn.getScene().getWindow().hide();


    }



}

