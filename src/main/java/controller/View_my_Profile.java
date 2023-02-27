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

//This class is used as controller to Vie Profile with all type of validation
public class View_my_Profile {
    private Model model;
    private Stage stage;
    private Stage parentStage;
    @FXML
    private Button ok;
    @FXML
    private Label first_name;
    @FXML
    private Label last_name;
    @FXML
    private Label username;

    public View_my_Profile (Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }

    // Add your code to complete the functionality of the program

    @FXML
    public void initialize() {
        username.setText(model.getCurrentUser().getUsername());
        first_name.setText(model.getCurrentUser().getFirst_name());
        last_name.setText(model.getCurrentUser().getLast_name());
        ok.setOnAction(event -> {
            stage.close();
            parentStage.show();
        });
    }




    public void showStage(Pane root) {


        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("View Profile");
        stage.show();
    }
}

