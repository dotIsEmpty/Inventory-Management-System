//THIS CONTROLLER IS FOR ADD PRODUCT SCENE

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class fourthController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void OnCancelButton(ActionEvent actionEvent) throws IOException { //Cancels "Add Product" action and returns to main scene
        Parent root = FXMLLoader.load(getClass().getResource("/sample/mainScene.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene mainScene = new Scene(root,767,452);
        stage.setTitle("Inventory Management System");
        stage.setScene(mainScene);
        stage.show();
    }
}
