package GUI;

import Model.Cursist;
import Model.Module;
import Model.WebCast;
import Model.Course;
import Model.ContentItem;
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

import javax.swing.text.AbstractDocument.Content;

import org.junit.runner.Description;

import javafx.scene.Node;

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

    private TableColumn<Cursist, String> adressCol = new TableColumn<>("adress");

    private TableColumn<Cursist, String> countryCol = new TableColumn<>("country");

    public CursistController() {
        dbConnection.openConnection();
    }

    public Scene Cursists() {
        BorderPane layout = new BorderPane();
        Scene cursists = new Scene(layout, 500, 500);

        table = new TableView<Cursist>();
        emailCol.setCellValueFactory(new PropertyValueFactory<Cursist, String>("email"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Cursist, String>("name"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<Cursist, Date>("birthDay"));
        sexCol.setCellValueFactory(new PropertyValueFactory<Cursist, String>("sex"));
        adressCol.setCellValueFactory(new PropertyValueFactory<Cursist, String>("adress"));
        countryCol.setCellValueFactory(new PropertyValueFactory<Cursist, String>("country"));

        table.getColumns().addAll(emailCol, nameCol, birthDateCol, sexCol, adressCol, countryCol);

        try {
            ResultSet resultSet = dbConnection.executeSQLSelectStatement(
                    "SELECT Student.Email, Student.Name, Student.Birthday, Student.Sex, concat(Student.Adress, ' ', Student.Residence) AS Adress, Student.Country FROM Student");
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
            Button edit = new Button("Edit");
            Button delete = new Button("Delete");
            Button view = new Button("View");
            HBox buttons = new HBox();
            buttons.getChildren().addAll(add, edit, view, delete);
            layout.setTop(buttons);

            add.setOnAction((EventHandler) -> {
                Node node = (Node) EventHandler.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();

                Stage stage = new Stage();
                stage.setScene(addCursist());
                stage.show();

            });

            edit.setOnAction((EventHandler) -> {

                if (table.getSelectionModel().getSelectedItem() != null) {
                    Node node = (Node) EventHandler.getSource();
                    Stage thisStage = (Stage) node.getScene().getWindow();
                    thisStage.close();
    
                    Stage stage = new Stage();
                    stage.setScene(editCursist(table.getSelectionModel().getSelectedItem()));
                    stage.show(); 
                }
            });

            delete.setOnAction((EventHandler) -> {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    Node node = (Node) EventHandler.getSource();
                    Stage thisStage = (Stage) node.getScene().getWindow();
                    thisStage.close();

                    removeCursist();

                    Stage stage = new Stage();
                    stage.setScene(Cursists());
                    stage.show();
                }
            });

            view.setOnAction((EventHandler) -> {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    Node node = (Node) EventHandler.getSource();
                    Stage thisStage = (Stage) node.getScene().getWindow();
                    thisStage.close();

                    Stage stage = new Stage();
                    stage.setScene(viewCursist(table.getSelectionModel().getSelectedItem()));
                    stage.show();
                }

            });

        } catch (SQLException e) {
            e.printStackTrace();
        }

        layout.autosize();
        return cursists;
    }

    @Override
    public void start(Stage stage) throws Exception {
    }

    public Scene addCursist() {
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
        birthDayTextField.setPromptText("day-month-year");
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

        Button submit = new Button("Voeg toe");
        grid.add(submit, 1, 7);

        submit.setOnAction((EventHandler) -> {
            System.out.println(birthDayTextField.getText());

            dbConnection.executeSQLUpdateStatement(String.format(
                    "INSERT INTO Student (Email, Name, Birthday, Sex, Adress, Country) VALUES ( '%1$s' , '%2$s' , '%3$s' , '%4$s' , '%5$s' , '%6$s' )",
                    emailTextField.getText(), nameTextField.getText(), birthDayTextField.getText(),
                    SexTextField.getText(), adressTextField.getText(), countryTextField.getText()));

            Node node = (Node) EventHandler.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();

            Stage stage = new Stage();
            stage.setScene(Cursists());
            stage.show();
        });

        grid.autosize();
        return new Scene(grid);
    }

    public Scene editCursist(Cursist cursist) {
        GridPane grid = new GridPane();

        Text scenetitle = new Text("Student aanpassen:");
        grid.add(scenetitle, 0, 0, 2, 1);

        // email input form
        Label email = new Label("E-mail Student:");
        grid.add(email, 0, 1);
        TextField emailTextField = new TextField();
        emailTextField.setText(cursist.getEmail());
        emailTextField.setEditable(false);
        grid.add(emailTextField, 1, 1);

        // naam input form
        Label name = new Label("Naam Student:");
        grid.add(name, 0, 2);
        TextField nameTextField = new TextField();
        nameTextField.setText(cursist.getName());
        grid.add(nameTextField, 1, 2);

        // geboortedatum input form
        Label birthDay = new Label("geboortedatum Student: ");
        grid.add(birthDay, 0, 3);
        TextField birthDayTextField = new TextField();
        birthDayTextField.setText(String.valueOf(cursist.getBirthDay()));
        grid.add(birthDayTextField, 1, 3);

        // sex input form
        Label Sex = new Label("Sex Student: ");
        grid.add(Sex, 0, 4);
        TextField SexTextField = new TextField();
        SexTextField.setText(cursist.getSex());
        grid.add(SexTextField, 1, 4);

        // adress input form
        Label adress = new Label("adress Student: ");
        grid.add(adress, 0, 5);
        TextField adressTextField = new TextField();
        adressTextField.setText(cursist.getAdress());
        grid.add(adressTextField, 1, 5);

        // country input form
        Label country = new Label("country Student: ");
        grid.add(country, 0, 6);
        TextField countryTextField = new TextField();
        countryTextField.setText(cursist.getCountry());
        grid.add(countryTextField, 1, 6);

        Button submit = new Button("Pas aan");
        grid.getChildren().add(submit);

        submit.setOnAction((EventHandler) -> {
            System.out.println(birthDayTextField.getText());

            dbConnection.executeSQLUpdateStatement(String.format(
                    "UPDATE Student SET Name = '%1$s', Birthday = '%2$s', Sex = '%3$s', Adress = '%4$s', Country = '%5$s' WHERE Email = '%6$s'",
                    nameTextField.getText(), birthDayTextField.getText(),
                    SexTextField.getText(), adressTextField.getText(), countryTextField.getText(),
                    cursist.getEmail()));

            Node node = (Node) EventHandler.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();

            Stage stage = new Stage();
            stage.setScene(Cursists());
            stage.show();
        });

        grid.autosize();
        return new Scene(grid);
    }

    public void removeCursist() {
        Cursist toRemove;
        toRemove = table.getSelectionModel().getSelectedItem();

        dbConnection.executeSQLUpdateStatement(String.format(
                "DELETE FROM Student WHERE Email = '%1$s' AND Name = '%2$s' AND Birthday = '%3$s' AND Sex = '%4$s' AND Adress = '%5$s' AND Country = '%6$s';",
                toRemove.getEmail(), toRemove.getName(), toRemove.getBirthDay(), toRemove.getSex(),
                toRemove.getAdress(), toRemove.getCountry()));

        Cursists();
    }

    private TableView<Course> courseTable = new TableView<Course>();
    private TableColumn<Course, String> courseNameCol = new TableColumn<>("courseName");
    private TableColumn<Course, String> courseSubjectCol = new TableColumn<>("subject");
    private TableColumn<Course, String> courseIntroductionCol = new TableColumn<>("introductionText");
    private TableColumn<Course, Integer> courseDifficultyCol = new TableColumn<>("difficulty");

    public Scene viewCursist(Cursist cursist) {
        BorderPane layout = new BorderPane();

        Button back = new Button("Back");
        back.setOnAction((EventHandler) -> {
            Node node = (Node) EventHandler.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();

            Stage stage = new Stage();
            stage.setScene(Cursists());
            stage.show();
        });

        Button viewContentitems = new Button("view");
        viewContentitems.setOnAction((EventHandler) -> {
            if (courseTable.getSelectionModel().getSelectedItem() != null && cursist != null) {
                Node node = (Node) EventHandler.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();

                Stage stage = new Stage();
                stage.setScene(viewWebcast(cursist, courseTable.getSelectionModel().getSelectedItem()));
                stage.show();
            }
        });

        HBox buttons = new HBox();
        buttons.getChildren().addAll(back, viewContentitems);
        layout.setTop(buttons);

        courseTable = new TableView<Course>();
        courseNameCol.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
        courseSubjectCol.setCellValueFactory(new PropertyValueFactory<Course, String>("subject"));
        courseIntroductionCol.setCellValueFactory(new PropertyValueFactory<Course, String>("introductionText"));
        courseDifficultyCol.setCellValueFactory(new PropertyValueFactory<Course, Integer>("difficulty"));

        courseTable.getColumns().addAll(courseNameCol, courseSubjectCol, courseIntroductionCol, courseDifficultyCol);

        try {
            ResultSet resultSet = dbConnection.executeSQLSelectStatement(String.format(
                    "SELECT * FROM Course WHERE CourseName IN (SELECT CourseName FROM Enrollment WHERE StudentEmail = '%s');",
                    cursist.getEmail()));

            while (resultSet.next()) {

                String name = resultSet.getString("CourseName");
                String subject = resultSet.getString("Subject");
                String introduction = resultSet.getString("IntroductionText");
                int difficulty = resultSet.getInt("Difficulty");

                Course temp = new Course(name, subject, introduction, difficulty, 0);
                courseTable.getItems().add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        layout.setCenter(courseTable);
        layout.autosize();
        return new Scene(layout);
    }

    private TableView<WebCast> webcastTable = new TableView<WebCast>();
    private TableColumn<WebCast, String> webcastTitleCol = new TableColumn<>("Title");
    private TableColumn<WebCast, String> webcastDescriptionCol = new TableColumn<>("Description");
    private TableColumn<WebCast, String> webcastProgressCol = new TableColumn<>("Progress");

    public Scene viewWebcast(Cursist cursist, Course course) {

        BorderPane layout = new BorderPane();

        webcastTable = new TableView<WebCast>();
        webcastTitleCol.setCellValueFactory(new PropertyValueFactory<WebCast, String>("Title"));
        webcastDescriptionCol.setCellValueFactory(new PropertyValueFactory<WebCast, String>("Description"));
        webcastProgressCol.setCellValueFactory(new PropertyValueFactory<WebCast, String>("Progress"));

        webcastTable.getColumns().addAll(webcastTitleCol, webcastDescriptionCol, webcastProgressCol);

        Button back = new Button("Back");
        back.setOnAction((EventHandler) -> {
            Node node = (Node) EventHandler.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();

            Stage stage = new Stage();
            stage.setScene(viewCursist(cursist));
            stage.show();
        });
        HBox buttons = new HBox();
        buttons.getChildren().addAll(back);
        layout.setTop(buttons);

        // lookup webcasts
        try {
            ResultSet resultSet = dbConnection.executeSQLSelectStatement(String.format("SELECT Title, CAST(Webcast.Description AS NVARCHAR(MAX)) AS Description FROM Webcast WHERE ContentItemID IN (SELECT ContentItemID FROM ContentItem WHERE CourseName = '%1$s') UNION SELECT Title, CAST(Module.Description AS NVARCHAR(MAX)) AS Description FROM Module WHERE ContentItemID IN (SELECT ContentItemID FROM ContentItem WHERE CourseName = '%1$s') ORDER BY Title",
                course.getCourseName()));
            while (resultSet.next()) {

                String title = resultSet.getString("Title");
                String description = resultSet.getString("Description");

                WebCast temp = new WebCast();
                temp.setTitle(title);
                temp.setDescription(description);
                webcastTable.getItems().add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        layout.autosize();
        layout.setCenter(webcastTable);
        layout.autosize();
        return new Scene(layout);
    }

}
