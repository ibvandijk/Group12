package GUI;

import Model.Course;
import Model.Module;
import Datastorage.DatabaseConnection;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import java.util.Calendar;

import javafx.scene.Node;

public class StudentModuleProgressController {
    private DatabaseConnection dbConnection = new DatabaseConnection();

    private TableView<Module> table = new TableView<Module>();

    private TableColumn<Module, String> titleCol = new TableColumn<>("Title");

    private TableColumn<Module, String> versionCol = new TableColumn<>("Version");

    private TableColumn<Module, String> descriptionCol = new TableColumn<>("Description");

    private TableColumn<Module, String> contactNameCol = new TableColumn<>("ContactName");

    private TableColumn<Module, String> contactEmailCol = new TableColumn<>("ContactEmail");

    private TableColumn<Module, Integer> progressCol = new TableColumn<>("Average progress");

    public StudentModuleProgressController() {
        dbConnection.openConnection();

        titleCol.setCellValueFactory(new PropertyValueFactory<Module, String>("Title"));
        versionCol.setCellValueFactory(new PropertyValueFactory<Module, String>("Version"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Module, String>("Description"));
        contactNameCol.setCellValueFactory(new PropertyValueFactory<Module, String>("ContactName"));
        contactEmailCol.setCellValueFactory(new PropertyValueFactory<Module, String>("contactEmail"));
        progressCol.setCellValueFactory(new PropertyValueFactory<Module, Integer>("progress"));

        table.getColumns().addAll(titleCol, versionCol, descriptionCol, contactNameCol, contactEmailCol, progressCol);
    }

    public Scene viewModule() {
        BorderPane layout = new BorderPane();
        Scene studentModuleProgresScene = new Scene(layout, 1000, 250);

        try {

            ResultSet resultSet = dbConnection.executeSQLSelectStatement(
                    // JOIN ContentItem ON Module.ContentItemID = ContentItem.ContentItemID JOIN
                    // Course ON ContentItem.CourseName = Course.CourseName;
                    "SELECT Module.*, Progress FROM Module JOIN (SELECT ContentItemID, AVG(Progress) AS Progress FROM StudentContentItemProgress GROUP BY ContentItemID ) AS ProgressSummary ON Module.ContentItemID = ProgressSummary.ContentItemID ");
            while (resultSet.next()) {

                String title = resultSet.getString("Title");
                String version = resultSet.getString("Version");
                String description = resultSet.getString("Description");
                String contactName = resultSet.getString("ContactName");
                String contactEmail = resultSet.getString("ContactEmail");
                int progress = resultSet.getInt("Progress");

                Module temp = new Module(title, version, description, contactName, contactEmail, "", null, progress);
                table.getItems().add(temp);
            }

            layout.setCenter(table);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentModuleProgresScene;
    }
}
