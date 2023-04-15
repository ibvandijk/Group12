package GUI;

import Model.Cursist;
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
        Scene cursists = new Scene(layout, 500,500);

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


            layout.setCenter(table);

            Button add = new Button("Add");
            Button delete = new Button("Delete");
            Button Back = new Button("Back");
            Button view = new Button("View");
            HBox buttons = new HBox();
            buttons.getChildren().addAll(Back, add, view, delete);
            layout.setTop(buttons);

            add.setOnAction((EventHandler) -> {
                Stage stage = new Stage();
                stage.setScene(addCursist());
                stage.show();

            });

        } catch (SQLException e) {
            e.printStackTrace();
        }

       return cursists;
    }

    @Override
    public void start(Stage stage) throws Exception {
    }

    public Scene addCursist(){
        GridPane grid = new GridPane();

        Text scenetitle = new Text("Nieuw Student toevoegen:");
        grid.add(scenetitle, 0, 0, 2, 1);


        // email input form
        Label email = new Label("E-mail Student:");
        grid.add(email, 0, 1);
        TextField emailTextField = new TextField();
        grid.add(emailTextField, 1, 1);

        // naam input form
        Label name = new Label("Naam Student:");
        grid.add(name, 0, 2);
        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 2);

        // geboortedatum input form
        Label birthDay = new Label("geboortedatum Student: ");
        grid.add(birthDay, 0, 3);
        TextField birthDayTextField = new TextField();
        grid.add(birthDayTextField, 1, 3);

        // sex input form
        Label Sex = new Label("Sex Student: ");
        grid.add(Sex, 0, 4);
        TextField SexTextField = new TextField();
        grid.add(SexTextField, 1, 4);

        // adress input form
        Label adress = new Label("adress Student: ");
        grid.add(adress, 0, 5);
        TextField adressTextField = new TextField();
        grid.add(adressTextField, 1, 5);

        // country input form
        Label country = new Label("country Student: ");
        grid.add(country, 0, 6);
        TextField countryTextField = new TextField();
        grid.add(countryTextField, 1, 6);

        
        

        return new Scene(grid);
    }

}


