//THIS CONTROLLER IS FOR MAIN SCENE

package sample;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public TableView<Part> partTable;
    public TableColumn<Part, Integer> partIDCol;
    public TableColumn<Part, String> partNameCol;
    public TableColumn<Part, Integer> partInventoryCol;
    public TableColumn<Part, Double> partCostCol;
    public TableView<Product> productTable;
    public TableColumn<Product, Integer> productIDCol;
    public TableColumn<Product, String> productNameCol;
    public TableColumn<Product, Integer> productInventoryCol;
    public TableColumn<Product, Double> productCostCol;
    public Button delPartButton;
    public Button delProdButton;
    public TextField prodSearchBox;
    public TextField partSearchBox;

    //delete ModifyPart and uncomment the original code below
    /**
    *When this method is called, it will pass the selectedPart object to the ModifyPart view
    * */
    public void ModifyPart(ActionEvent actionEvent) throws IOException { //opens "Modify Part" form

        if(partTable.getSelectionModel().getSelectedItem() != null) {
            Part part = partTable.getSelectionModel().getSelectedItem();

            if(part.getCompanyName() == null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/ModifyPart.fxml"));
                Parent partTableParent = loader.load();

                Scene partTableScene = new Scene(partTableParent);

                //access the controller and call a method
                thirdController controller = loader.getController();
                controller.initData(part);

                //This line gets the stage information
                Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

                window.setScene(partTableScene);
                window.show();
            }
            else {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/ModifyPartOutsourced.fxml"));
                Parent partTableParent = loader.load();

                Scene partTableScene = new Scene(partTableParent);

                //access the controller and call a method
                thirdController controller = loader.getController();
                controller.initData(part);

                //This line gets the stage information
                Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

                window.setScene(partTableScene);
                window.show();
            }
        }

        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to modify.");

            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partTable.setItems(Inventory.getAllParts());
        productTable.setItems(Inventory.getAllProducts());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productIDCol.setCellValueFactory(new PropertyValueFactory<>(""));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>(""));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>(""));
        productCostCol.setCellValueFactory(new PropertyValueFactory<>(""));

        FilteredList<Part> filteredData = new FilteredList<>(Inventory.getAllParts(), b -> true);

        partSearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(part -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (part.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else return String.valueOf(part.getId()).indexOf(lowerCaseFilter) != -1;

            });
        });

        SortedList<Part> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(partTable.comparatorProperty());

        partTable.setItems(sortedData);

    }


    public void OnAddPartAction(ActionEvent actionEvent) throws IOException { //This opens the addPart form from "Add" button on main
        Parent root = FXMLLoader.load(getClass().getResource("/sample/addPart.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene addPart = new Scene(root, 600, 453); //change window dimensions to match addPart form
        stage.setTitle("Add Part");
        stage.setScene(addPart);
        stage.show();
    }
    /*
    public void ModifyPart(ActionEvent actionEvent) throws IOException { //opens "Modify Part" form

        if(partTable.getSelectionModel().getSelectedItem() != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/modifyPart.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene modifyPart = new Scene(root, 600, 453);
            stage.setTitle("Modify Part");
            stage.setScene(modifyPart);
            stage.show();

            //put in Initialize?
            Part part = partTable.getSelectionModel().getSelectedItem();
             String partName = part.getName();
             int partInventory = part.getStock();
             double partCost = part.getPrice();
             int partMax = part.getMax();
             int partMin = part.getMin();
             int partMachineID = part.getMachineID();
             String partCompanyName = part.getCompanyName();
        }

        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to modify.");

            alert.showAndWait();
        }
    }*/

    public void AddProduct(ActionEvent actionEvent) throws IOException { //Opens "Add Product" form
        Parent root = FXMLLoader.load(getClass().getResource("/sample/addProduct.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene addProduct = new Scene(root, 900, 550);
        stage.setTitle("Add Product");
        stage.setScene(addProduct);
        stage.show();
    }

    public void ModifyProduct(ActionEvent actionEvent) throws IOException { //Opens "Modify Product" form
        Parent root = FXMLLoader.load(getClass().getResource("/sample/modifyProduct.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene modifyProduct = new Scene(root, 900, 550);
        stage.setTitle("Modify Product");
        stage.setScene(modifyProduct);
        stage.show();
    }

    public void OnExit() { //Exits the program
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("If you're sure you want to exit, click OK.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {

        }

    }

    public void OnDelete(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("If you're sure you want to delete this item, click OK.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Part part = partTable.getSelectionModel().getSelectedItem();
            if (Inventory.getAllParts().contains(part)) {
                Inventory.getAllParts().remove(part);
            }
            else {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Error");
                alert2.setHeaderText(null);
                alert2.setContentText("Select an item to delete.");

                alert2.showAndWait();
            }

        }
        else {

        }
    }


}

