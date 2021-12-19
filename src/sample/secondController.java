//THIS CONTROLLER IS FOR ADD PART SCENE (IN-HOUSE AND OUTSOURCED)

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;


import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class secondController implements Initializable {
    public TextField id;
    public TextField name;
    public TextField stock;
    public TextField price;
    public TextField max;
    public TextField machineID;
    public TextField min;
    public TextField company;
    public RadioButton addPartOutsourcedRadio;
    public RadioButton addPartInHouseRadio;
    public ToggleGroup tGroup;

    public boolean isOutSourced() {
        if (addPartOutsourcedRadio.isSelected()) {
            return true;
        }
       return false;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void OnCancelButton(ActionEvent actionEvent) throws IOException { //Cancel button for Add Part form

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("If you're sure you want to cancel, click OK.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            Parent root = FXMLLoader.load(getClass().getResource("/sample/mainScene.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene mainScene = new Scene(root, 767, 452);
            stage.setTitle("Inventory Management System");
            stage.setScene(mainScene);
            stage.show();

        }
        else {

        }
    }

    public void OnOutsourced(ActionEvent actionEvent) throws IOException { //changes add part form from in-house to outsourced
        Parent root = FXMLLoader.load(getClass().getResource("/sample/addPartOutsourced.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene addPartOutsourced = new Scene(root, 600, 453);
        stage.setTitle("Add Part");
        stage.setScene(addPartOutsourced);
        stage.show();
    }

    public void OnInHouse(ActionEvent actionEvent) throws IOException { //changes add part form from outsourced to in-house
        Parent root = FXMLLoader.load(getClass().getResource("/sample/addPart.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene addPart = new Scene(root, 600, 453);
        stage.setTitle("Add Part");
        stage.setScene(addPart);
        stage.show();
    }

    public void SaveAddPart(ActionEvent actionEvent) throws IOException {

        int count = 1;
        int partIDGen = count;

        if (Integer.parseInt(stock.getText()) > Integer.parseInt(min.getText()) && Integer.parseInt(max.getText()) > Integer.parseInt(min.getText()) && Integer.parseInt(min.getText()) > 0 && Integer.parseInt(max.getText()) > Integer.parseInt(stock.getText())) {
            if (isOutSourced()) {
                OutSourced part = new OutSourced();

                part.setId(partIDGen);
                part.setName(name.getText());
                part.setPrice(Double.parseDouble(price.getText()));
                part.setStock(Integer.parseInt(stock.getText()));
                part.setMin(Integer.parseInt(min.getText()));
                part.setMax(Integer.parseInt(max.getText()));
                part.setCompanyName(company.getText());

                Inventory.addPart(part);
            } else {
                InHouse part = new InHouse();

                part.setId(partIDGen);
                part.setName(name.getText());
                part.setPrice(Double.parseDouble(price.getText()));
                part.setStock(Integer.parseInt(stock.getText()));
                part.setMin(Integer.parseInt(min.getText()));
                part.setMax(Integer.parseInt(max.getText()));
                part.setMachineID(Integer.parseInt(machineID.getText()));

                Inventory.addPart(part);
            }
            count = count + 2;


            Parent root = FXMLLoader.load(getClass().getResource("/sample/mainScene.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene mainScene = new Scene(root, 767, 452);
            stage.setTitle("Inventory Management System");
            stage.setScene(mainScene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Please check your values!");
            alert.setHeaderText(null);
            alert.setContentText("Stock must be less than Max and greater than Min. Min must be greater than zero");

            alert.showAndWait();
        }
    }
}
