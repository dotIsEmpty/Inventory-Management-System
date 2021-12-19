//THIS CONTROLLER IS FOR MODIFY PART SCENE (IN-HOUSE AND OUTSOURCED)

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class thirdController  implements Initializable {

    @FXML private RadioButton modPartOutSourcedRadio;
    @FXML private TextField id;
    @FXML private TextField name;
    @FXML private TextField stock;
    @FXML private TextField price;
    @FXML private TextField max;
    @FXML private TextField min;
    @FXML private TextField machineID;
    @FXML private TextField company;

    private Part selectedPart;

    /**
     * This method accepts a part to initialize the view
     * @param part
     * */
    public void initData(Part part) {
        selectedPart = part;
        id.setText(Integer.toString(selectedPart.getId()));
        name.setText(selectedPart.getName());
        stock.setText(Integer.toString(selectedPart.getStock()));
        price.setText(Double.toString(selectedPart.getPrice()));
        max.setText(Integer.toString(selectedPart.getMax()));
        min.setText(Integer.toString(selectedPart.getMin()));
        if(part.getCompanyName() == null) {
            machineID.setText(Integer.toString(selectedPart.getMachineID()));
        }
        else {
            company.setText(selectedPart.getCompanyName());
        }
    }

    public boolean isOutSourced() {
        if (modPartOutSourcedRadio.isSelected()) {
            return true;
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //This method returns to main scene without modifying any parts
    public void OnCancelButton(ActionEvent actionEvent) throws IOException {

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

    public void OnOutsourced(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load (getClass().getResource("/sample/modifyPartOutsourced.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene modifyPartOutsourced = new Scene(root,600,453);
        stage.setTitle("Modify Part");
        stage.setScene(modifyPartOutsourced);
        stage.show();
    }

    public void OnInHouse(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load (getClass().getResource("/sample/modifyPart.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene modifyPart = new Scene(root,600,453);
        stage.setTitle("Modify Part");
        stage.setScene(modifyPart);
        stage.show();
    }

    public void onSaveMod(ActionEvent actionEvent) throws IOException {

        boolean success = false;

            for(Part part : Inventory.getAllParts()) {

                if(part.getId() == Integer.parseInt(id.getText())) {
                    part.setName(name.getText());
                    part.setStock(Integer.parseInt(stock.getText()));
                    part.setPrice(Double.parseDouble(price.getText()));
                    part.setMax(Integer.parseInt(max.getText()));
                    part.setMin(Integer.parseInt(min.getText()));
                      if(isOutSourced()) {
                        part.setCompanyName(company.getText());
                      }
                      else {
                        part.setMachineID(Integer.parseInt(machineID.getText()));
                      }
                    success = true;
                }
                else {
                    success = false;
                }
            }
        if(success = true) {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/mainScene.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene mainScene = new Scene(root, 767, 452);
            stage.setTitle("Inventory Management System");
            stage.setScene(mainScene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Oops! Something went wrong.");

            alert.showAndWait();

        }
    }
}
