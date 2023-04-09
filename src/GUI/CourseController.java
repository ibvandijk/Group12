package GUI;

import Model.Cursist;

import javafx.application.Application;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



public class CourseController extends Application {
    private TableView<Cursist> table = new TableView<Cursist>();

    // Create column UserName (Data type of String).
    private TableColumn<Cursist, String> emailCol = new TableColumn<Cursist, String>("E-mail");

    // Create 2 columns for Name.
    private TableColumn<Cursist, String> firstNameCol = new TableColumn<Cursist, String>("First Name");
    private TableColumn<Cursist, String> lastNameCol = new TableColumn<Cursist, String>("Last Name");

    // Create column birthdate (Data type of Date)
    private TableColumn<Cursist, String> birthDateCol = new TableColumn<Cursist, String>("Birth Date");

    // Create column Gender
    private TableColumn<Cursist, String> genderCol = new TableColumn<Cursist, String>("Sex");

    @Override
    public void start(Stage stage) throws Exception {
    }

    public CourseController() {
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("first-name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("last-name"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birth-date"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        table.getColumns().addAll(emailCol, firstNameCol, lastNameCol, birthDateCol, genderCol);
        
    }
}
