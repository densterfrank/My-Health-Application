package  controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Model;
import model.User;

import java.io.IOException;
import java.sql.SQLException;


//This class is used as controller to show about application and its version
public class About_My_Health {
    private Model model;
    private Stage stage;
    private Stage parentStage;


    public About_My_Health (Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }

    // Add your code to complete the functionality of the program






    public void showStage(Pane root) {


        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("About");
        stage.show();
    }
}

