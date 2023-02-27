package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import model.Records;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

//This class is used as controller to Update record with all type of validation
public class Update_Record {
    private Model model;
    private Stage stage;
    private Stage parentStage;
    @FXML
    private TableView<Records> table;
    @FXML
    private TableColumn<Records, LocalDate> date;

    @FXML
    private TableColumn<Records, Float> weight;

    @FXML
    private TableColumn<Records, Float> temperature;
    @FXML
    private Button cancel;

    @FXML
    private TableColumn<Records, Float> high;
    @FXML
    private TableColumn<Records, String> notes;

    @FXML
    private TableColumn<Records, Float> low;
    int index=-1;

    public Update_Record(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }

    // Add your code to complete the functionality of the program
    @FXML
    public void initialize() throws SQLException {
        cancel.setOnAction(event -> {
            stage.close();
            parentStage.show();
        });
       date.setCellValueFactory(new PropertyValueFactory<Records, LocalDate>("Date"));
        weight.setCellValueFactory(new PropertyValueFactory<Records, Float>("Weight"));
        temperature.setCellValueFactory(new PropertyValueFactory<Records, Float>("Temperature"));
        low.setCellValueFactory(new PropertyValueFactory<Records, Float>("Low_bp"));
        high.setCellValueFactory(new PropertyValueFactory<Records, Float>("High_bp"));
        notes.setCellValueFactory(new PropertyValueFactory<Records, String>("Notes"));

        ObservableList<Records> rec_list= FXCollections.observableList(model.getUserDao().getRecords(model.getCurrentUser().getUsername()));
        table.setItems(rec_list);


        table.setOnMouseClicked(event -> {
            index=table.getSelectionModel().getSelectedIndex();
            if (index<=-1){
                return;
            }


        });
    }






    public void showStage(Pane root) {


        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Edit Records");
        stage.show();
    }
}

