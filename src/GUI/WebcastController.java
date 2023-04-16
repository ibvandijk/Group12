package GUI;

import Model.Course;
import Model.WebCast;
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

public class WebcastController {
    private DatabaseConnection dbConnection = new DatabaseConnection();

    private TableView<WebCast> table = new TableView<WebCast>();

    private TableColumn<WebCast, String> titleCol = new TableColumn<>("Title");

    private TableColumn<WebCast, String> descriptionCol = new TableColumn<>("Description");

    private TableColumn<WebCast, String> speakerCol = new TableColumn<>("Speaker");

    private TableColumn<WebCast, String> organisationCol = new TableColumn<>("organisation");

    private TableColumn<WebCast, Integer> viewCountCol = new TableColumn<>("ViewCount");

    public WebcastController() {
        dbConnection.openConnection();

        titleCol.setCellValueFactory(new PropertyValueFactory<WebCast, String>("Title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<WebCast, String>("Description"));
        speakerCol.setCellValueFactory(new PropertyValueFactory<WebCast, String>("speaker"));
        organisationCol.setCellValueFactory(new PropertyValueFactory<WebCast, String>("organisation"));
        viewCountCol.setCellValueFactory(new PropertyValueFactory<WebCast, Integer>("viewCount"));

        table.getColumns().addAll(titleCol, descriptionCol, speakerCol, organisationCol, viewCountCol);
    }

    // Creates the scene where the user can see the top 3 webcasts
    public Scene viewWebcast() {
        BorderPane layout = new BorderPane();
        Scene webcastScene = new Scene(layout, 1000, 250);

        try {

            ResultSet resultSet = dbConnection.executeSQLSelectStatement(

                    "SELECT TOP 3 Webcast.*  FROM Webcast ORDER BY viewCount DESC;");
            while (resultSet.next()) {

                String title = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                String speaker = resultSet.getString("Speaker");
                String organisation = resultSet.getString("organisation");
                int viewCount = resultSet.getInt("ViewCount");

                WebCast temp = new WebCast(title, description, speaker, organisation, viewCount);
                table.getItems().add(temp);
            }

            layout.setCenter(table);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return webcastScene;
    }
}
