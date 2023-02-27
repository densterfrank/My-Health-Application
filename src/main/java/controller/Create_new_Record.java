package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import validation.Validation;

import javax.xml.validation.Validator;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

//This class is used as controller to create new record with all type of validation
public class Create_new_Record {
    public static List<Records> record_list=new ArrayList<Records>();
    private Model model;
    private Stage stage;
    private Stage parentStage;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;
    @FXML
    private TextField date;
    @FXML
    private TextField temperature;
    @FXML
    private TextField weight;
    @FXML
    private TextField low;
    @FXML
    private TextField high;
    @FXML
    private TextField notes;
    @FXML
    private Label message;
    @FXML
    private Label status;

    public Create_new_Record(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }

    // Add your code to complete the functionality of the program

    @FXML
    public void initialize() {

        Records records =new Records(null,null,null,null,null,null);

        ok.setOnAction(event -> {

            if((!temperature.getText().isEmpty())||(!weight.getText().isEmpty())||(!notes.getText().isEmpty())||((!low.getText().isEmpty())&&(!high.getText().isEmpty()))){

                try {
                    if (!date.getText().isEmpty()){
                        DateTimeFormatter date_format=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate pick_date=LocalDate.parse(date.getText(), date_format);
                        if (Validation.date_Validator(pick_date))
                        {
                            records.setDate(String.valueOf(pick_date));
                            message.setText("");
                        }
                        else{
                            message.setText("");
                            message.setText("Date should be greater or equal to present day");
                            message.setTextFill(Color.RED);
                        }
                        if (!weight.getText().isEmpty()){
                            if(Validation.only_number_Validator(weight.getText())) {
                                records.setWeight(String.valueOf(Float.valueOf(weight.getText())));
                                message.setText("");
                            }
                            else{
                                message.setText("");
                                message.setText("Weight should be number ot Integer");
                                message.setTextFill(Color.RED);
                            }
                        }
                    }


                    if (!temperature.getText().isEmpty()){
                        if(Validation.only_number_Validator(temperature.getText())) {
                            records.setTemperature(String.valueOf(Float.valueOf(temperature.getText())));
                            message.setText("");
                        }
                        else{
                            message.setText("");
                            message.setText("Temperature should be number ot Integer");
                            message.setTextFill(Color.RED);
                        }
                    }
                    if ((!low.getText().isEmpty())&&(!high.getText().isEmpty())){
                        if ((Float.valueOf(high.getText())>Float.valueOf(low.getText()))){
                            if (!low.getText().isEmpty()){
                                if(Validation.only_number_Validator(low.getText())) {
                                    records.setLow_bp(String.valueOf(Float.valueOf(low.getText())));
                                    message.setText("");
                                }
                                else{
                                    message.setText("");
                                    message.setText("Low Blood Pressure should be number ot Integer");
                                    message.setTextFill(Color.RED);
                                }
                            }
                            if (!high.getText().isEmpty()){
                                if(Validation.only_number_Validator(high.getText())) {
                                    records.setHigh_bp(String.valueOf(Float.valueOf(high.getText())));
                                    message.setText("");
                                }
                                else{
                                    message.setText("");
                                    message.setText("High Blood Pressure should be number ot Integer");
                                    message.setTextFill(Color.RED);

                                }
                            }


                        }
                        else{
                            message.setText("");
                            message.setText("High Blood Pressure should be greater than   Low Blood pressure");
                            message.setTextFill(Color.RED);
                        }


                }
                    if (!notes.getText().isEmpty()){

                        records.setNotes(notes.getText());
                        message.setText("");

                    }
                    Records rec;
                    rec = model.getUserDao().createRecord(model.getCurrentUser().getUsername(),records.getDate(),records.getWeight(),records.getTemperature(), records.getLow_bp(),  records.getHigh_bp(), records.getNotes());
                    if (rec != null  && message.getText().isEmpty()) {
                        status.setText("Created record for " + model.getCurrentUser().getUsername());
                        record_list.add(records);
                        model.getCurrentUser().setRecords((ArrayList<Records>) record_list);
                        status.setTextFill(Color.GREEN);
                    } else {
                        status.setText("Cannot create Record");
                        status.setTextFill(Color.RED);
                    }




            } catch(DateTimeParseException e){
                    message.setText("");
                    message.setText("Incorrect Date Format");
                    message.setTextFill(Color.RED);
                }
                catch (SQLException e) {
                    status.setText(e.getMessage());
                    status.setTextFill(Color.RED);
                }

            }
            else{
                message.setText("");
                message.setText("Record should be filled with atleast one data");
                message.setTextFill(Color.RED);
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
        stage.setTitle("Create New Record");
        stage.show();

    }
}
