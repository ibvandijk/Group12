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



public class CursistController extends Application {
    private DatabaseConnection dbConnection = new DatabaseConnection();


    private TableView<Cursist> table = new TableView<Cursist>();

    // Create column UserName (Data type of String).
    private TableColumn<Cursist, String> emailCol = new TableColumn<Cursist, String>("E-mail");

    // Create 2 columns for Name.
    private TableColumn<Cursist, String> nameCol = new TableColumn<Cursist, String>("Name");

    // Create column birthdate (Data type of Date)
    private TableColumn<Cursist, String> birthDateCol = new TableColumn<Cursist, String>("Birth Date");

    // Create column Gender
    private TableColumn<Cursist, String> genderCol = new TableColumn<Cursist, String>("Sex");

    @Override
    public void start(Stage stage) throws Exception {
    }

    public CursistController() {
        dbConnection.openConnection();

        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birth-date"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        table.getColumns().addAll(emailCol, nameCol, birthDateCol, genderCol);

        
        ResultSet resultSet = dbConnection.executeSQLSelectStatement("SELECT * FROM Student");


        try {
            while (resultSet.next()) {

                String email = resultSet.getString("Email");
                String name = resultSet.getString("Name");
                Date birthDate = resultSet.getDate("Birthday");
                String sex = resultSet.getString("Sex");
                String adress = resultSet.getString("Adress");
                String country = resultSet.getString("Country");

                int id = resultSet.getInt("id");
                table.getItems()
                        .add(new Cursist(email, name, birthDate, sex, adress, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Scene Cursists(){
        BorderPane layout = new BorderPane();
        Scene Cursists = new Scene(layout);
        layout.setLeft(table);

            Button add = new Button("Add");
            Button delete = new Button("Delete");
            Button Back = new Button("Back");
            Button view = new Button("View");
            HBox buttons = new HBox();
            buttons.getChildren().addAll(Back, add, view, delete);
            layout.setTop(buttons);
            return Cursists;
    }
}
