package GUI;

import Model.Cursist;
import Datastorage.DatabaseConnection;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CursistController extends Application {
    private DatabaseConnection dbConnection = new DatabaseConnection();

    private TableView<Cursist> table = new TableView<Cursist>();

    // Create column UserName (Data type of String).
    private TableColumn<Cursist, String> emailCol = new TableColumn<>("email");

    // Create 2 columns for Name.
    private TableColumn<Cursist, String> nameCol = new TableColumn<>("name");

    // Create column birthdate (Data type of Date)
    private TableColumn<Cursist, Date> birthDateCol = new TableColumn<>("birthDate");

    // Create column Sex
    private TableColumn<Cursist, String> sexCol = new TableColumn<>("sex");


    public CursistController() {
        dbConnection.openConnection();

        emailCol.setCellValueFactory(new PropertyValueFactory<Cursist, String>("email"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Cursist, String>("name"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<Cursist, Date>("birthDay"));
        sexCol.setCellValueFactory(new PropertyValueFactory<Cursist, String>("sex"));

        table.getColumns().addAll(emailCol, nameCol, birthDateCol, sexCol);
    }

    public Scene Cursists(){
        BorderPane layout = new BorderPane();
        Scene cursists = new Scene(layout);

        try {
            ResultSet resultSet = dbConnection.executeSQLSelectStatement("SELECT * FROM Student");

            while (resultSet.next()) {

                String email = resultSet.getString("Email");
                String name = resultSet.getString("Name");
                Date birthDate = resultSet.getDate("Birthday");
                String sex = resultSet.getString("Sex");
                String adress = resultSet.getString("Adress");
                String country = resultSet.getString("Country");

                Cursist temp = new Cursist(email, name, birthDate, sex, adress, country);
                table.getItems().add(temp);
            }


            layout.setLeft(table);

            Button add = new Button("Add");
            Button delete = new Button("Delete");
            Button Back = new Button("Back");
            Button view = new Button("View");
            HBox buttons = new HBox();
            buttons.getChildren().addAll(Back, add, view, delete);
            layout.setTop(buttons);

        } catch (SQLException e) {
            e.printStackTrace();
        }

       return cursists;
    }

    @Override
    public void start(Stage stage) throws Exception {
    }
}
