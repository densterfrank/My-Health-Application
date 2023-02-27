package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Model;
import model.User;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.IOException;
import java.sql.SQLException;


//This class is used as controller to create Home page with all type of validation
public class HomeController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	@FXML
	private MenuItem viewProfile; // Corresponds to the Menu item "viewProfile" in HomeView.fxml
	@FXML
	private MenuItem updateProfile; // // Corresponds to the Menu item "updateProfile" in HomeView.fxml
	@FXML
	private MenuItem newRecord;
	@FXML
	private MenuItem deleteRecord;
	@FXML
	private MenuItem viewRecord;
	@FXML
	private MenuItem editRecord;
	@FXML
	private MenuItem logOut;
	@FXML
	private MenuItem aboutmyhealth;
	@FXML
	private Label nam;
	@FXML
	private MenuItem exp;
	
	public HomeController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	

	@FXML
	public void initialize() {
		nam.setText(model.getCurrentUser().getFirst_name()+" "+model.getCurrentUser().getLast_name());
		newRecord.setOnAction(event -> {


					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Create_new_health_record.fxml"));
						Create_new_Record homeController = new Create_new_Record(stage, model);
						loader.setController(homeController);
						AnchorPane root = loader.load();

						homeController.showStage(root);
						stage.close();
					} catch (IOException e) {
						//message.setText(e.getMessage());
					}

				});
		deleteRecord.setOnAction(event -> {


			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeleteRecord.fxml"));
				Delete_Record homeController = new Delete_Record(stage, model);
				loader.setController(homeController);
				AnchorPane root = loader.load();

				homeController.showStage(root);
				stage.close();
			} catch (IOException e) {
				//message.setText(e.getMessage());
			}

		});
		editRecord.setOnAction(event -> {


			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeleteRecord.fxml"));
				Update_Record up_recController = new Update_Record(stage, model);
				loader.setController(up_recController);
				AnchorPane root = loader.load();

				up_recController.showStage(root);
				stage.close();
			} catch (IOException e) {
				//message.setText(e.getMessage());
			}

		});
		viewRecord.setOnAction(event -> {


			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/view_all_existing_records.fxml"));
				View_Records RecordController = new View_Records(stage, model);
				loader.setController(RecordController);
				AnchorPane root = loader.load();

				RecordController.showStage(root);
				stage.close();
			} catch (IOException e) {
				//message.setText(e.getMessage());
			}

		});
		viewProfile.setOnAction(event -> {


			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/view_profile.fxml"));
				View_my_Profile viewController = new View_my_Profile(stage, model);
				loader.setController(viewController);
				AnchorPane root = loader.load();

				viewController.showStage(root);
				stage.close();
			} catch (IOException e) {
				//message.setText(e.getMessage());
			}

		});
		exp.setOnAction(event -> {


			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/view_all_existing_records.fxml"));
				Export export = new Export(stage, model);
				loader.setController(export);
				AnchorPane root = loader.load();

				export.showStage(root);
				stage.close();
			} catch (IOException e) {
				//message.setText(e.getMessage());
			}

		});
		updateProfile.setOnAction(event -> {


			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/update_profile.fxml"));
				updateProfile upController = new updateProfile(stage, model);
				loader.setController(upController);
				AnchorPane root = loader.load();

				upController.showStage(root);
				stage.close();
			} catch (IOException e) {
				//message.setText(e.getMessage());
			}

		});
		aboutmyhealth.setOnAction(event -> {


			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/about_my_profile.fxml"));
				About_My_Health aboutController = new About_My_Health(stage, model);
				loader.setController(aboutController);
				AnchorPane root = loader.load();

				aboutController.showStage(root);
				//stage.close();
			} catch (IOException e) {
				//message.setText(e.getMessage());
			}

		});
		logOut.setOnAction(event -> {
			stage.close();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));

			// Customize controller instance
			LoginController loginController = new LoginController(stage, model);

			loader.setController(loginController);

			GridPane root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			loginController.showStage(root);
		});
	}


	public void showStage(Pane root) {


		Scene scene = new Scene(root, 500, 300);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("My Health");
		stage.show();
	}
}
