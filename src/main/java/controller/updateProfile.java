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

public class updateProfile {
    private Model model;
    private Stage stage;
    private Stage parentStage;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private Label username;

    //This class is used as controller to Update Profile with all type of validation
    public updateProfile (Stage parentStage, Model model) {
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
            if((first_name.getText()!=model.getCurrentUser().getFirst_name())||(last_name.getText()!=model.getCurrentUser().getLast_name())){

                try {
                    model.getUserDao().UpdateUser(first_name.getText(),last_name.getText(),model.getCurrentUser().getUsername());
                    model.getCurrentUser().setFirst_name(first_name.getText());
                    model.getCurrentUser().setLast_name(last_name.getText());
                    stage.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {

                    stage.close();
                    parentStage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeView.fxml"));
                    HomeController homeController = new HomeController(stage, model);

                    loader.setController(homeController);
                    VBox root = loader.load();

                    homeController.showStage(root);
                    stage.close();
                }catch (IOException e) {
                    //message.setText(e.getMessage());
                }
            }

        });


        cancel.setOnAction(event -> {
            stage.close();
            parentStage.show();

        });
    }




    public void showStage(Pane root) {


        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Update Profile");
        stage.show();
    }
}

