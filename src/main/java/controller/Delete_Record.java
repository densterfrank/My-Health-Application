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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

//This class is used as controller to Delete record
public class Delete_Record {
    private Model model;
    private Stage stage;
    private Stage parentStage;
    @FXML
    private TableView<Records> table;
    @FXML
    private TableColumn<Records, String> date;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;
    @FXML
    private TableColumn<Records, String> weight;

    @FXML
    private TableColumn<Records, String> temperature;


    @FXML
    private TableColumn<Records, String> high;
    @FXML
    private TableColumn<Records, String> notes;

    @FXML
    private TableColumn<Records, String> low;

    int index=-1;
    public Delete_Record(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }


    @FXML
    public void initialize() throws SQLException {
        date.setCellValueFactory(new PropertyValueFactory<Records, String>("Date"));
        weight.setCellValueFactory(new PropertyValueFactory<Records, String>("Weight"));
        temperature.setCellValueFactory(new PropertyValueFactory<Records, String>("Temperature"));
        low.setCellValueFactory(new PropertyValueFactory<Records, String>("Low_bp"));
        high.setCellValueFactory(new PropertyValueFactory<Records, String>("High_bp"));
        notes.setCellValueFactory(new PropertyValueFactory<Records, String>("Notes"));

        ObservableList<Records> rec_list= FXCollections.observableList(model.getUserDao().getRecords(model.getCurrentUser().getUsername()));
        table.setItems(rec_list);
        cancel.setOnAction(event -> {
            stage.close();
            parentStage.show();
        });
        ok.setOnAction(event -> {
            stage.close();
            parentStage.show();
        });

        table.setOnMouseClicked(event -> {
            index=table.getSelectionModel().getSelectedIndex();
            if (index<=-1){
                return;
            }


            try {
                Records re=new Records(weight.getCellData(index),temperature.getCellData(index),low.getCellData(index),high.getCellData(index),notes.getCellData(index), date.getCellData(index));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeleteRecord_small.fxml"));
                Delete_Records_Small homeController = new Delete_Records_Small(stage, model,table);
                loader.setController(homeController);
                AnchorPane root = loader.load();

                homeController.showStage(root);
                stage.close();
            } catch (IOException e) {
                //message.setText(e.getMessage());
            }
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

