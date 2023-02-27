package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

//This class is used as controller to confirm the delete of item from record
public class Delete_Records_Small {
    private Model model;
    private Stage stage;
    private Stage parentStage;
    @FXML
    private TableView<Records> table;
    @FXML
    private TableColumn<Records, LocalDate> date;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;
    @FXML
    private TableColumn<Records, Float> weight;

    @FXML
    private TableColumn<Records, Float> temperature;


    @FXML
    private TableColumn<Records, Float> high;
    @FXML
    private TableColumn<Records, String> notes;

    @FXML
    private TableColumn<Records, Float> low;



    Records index;
    public Delete_Records_Small(Stage parentStage, Model model,TableView<Records> index) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
        this.table=index;
    }

    // Add your code to complete the functionality of the program
    @FXML
    public void initialize() throws SQLException {

        cancel.setOnAction(event -> {
            stage.close();
            parentStage.show();
        });
        ok.setOnAction(event -> {
            ObservableList<Records> allrecord,singleproduct;
            allrecord=table.getItems();
            singleproduct=table.getSelectionModel().getSelectedItems();
            singleproduct.forEach(allrecord::remove);
            stage.close();
            parentStage.show();
        });





    }









    public void showStage(Pane root) {


        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Delete Record");
        stage.show();
    }
}

