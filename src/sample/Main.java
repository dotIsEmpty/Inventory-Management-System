package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 767, 452)); //orig values = v:300, v1:275
        primaryStage.show();
        //primaryStage.setResizable(false); //uncomment to fix stage size
    }



    public static void main(String[] args) {

        InHouse part1 = new InHouse(1,"screw",0.50,300,1,4000,2);
        OutSourced part2 = new OutSourced(2,"nail",0.30,250,1,2500,"NailsCo");
        Inventory.addPart(part1);
        Inventory.addPart(part2);

        launch(args);
    }
}


