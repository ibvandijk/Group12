package GUI;

import Model.Course;
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

public class CourseController extends Application {
    private DatabaseConnection dbConnection = new DatabaseConnection();

    private TableView<Course> table = new TableView<Course>();

    private TableColumn<Course, String> nameCol = new TableColumn<>("courseName");

    private TableColumn<Course, String> subjectCol = new TableColumn<>("subject");

    private TableColumn<Course, String> introductionCol = new TableColumn<>("introductionText");

    private TableColumn<Course, Integer> difficultyCol = new TableColumn<>("difficulty");

    private TableColumn<Course, Integer> finishCountCol = new TableColumn<>("numStudentsWithFullProgress");

    public CourseController() {
        dbConnection.openConnection();

        nameCol.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<Course, String>("subject"));
        introductionCol.setCellValueFactory(new PropertyValueFactory<Course, String>("introductionText"));
        difficultyCol.setCellValueFactory(new PropertyValueFactory<Course, Integer>("difficulty"));
        finishCountCol.setCellValueFactory(new PropertyValueFactory<Course, Integer>("numStudentsWithFullProgress"));

        table.getColumns().addAll(nameCol, subjectCol, introductionCol, difficultyCol, finishCountCol);
    }

    public Scene Courses() {
        BorderPane layout = new BorderPane();
        Scene Courses = new Scene(layout, 500, 500);

        try {
            ResultSet resultSet = dbConnection.executeSQLSelectStatement(
                    "SELECT Course.CourseName, Course.Subject, Course.IntroductionText, Course.Difficulty, COALESCE(NumStudentsWithFullProgress, 0) AS NumStudentsWithFullProgress FROM Course LEFT JOIN (SELECT ContentItem.CourseName, COUNT(DISTINCT StudentContentItemProgress.StudentEmail) AS NumStudentsWithFullProgress FROM ContentItem JOIN StudentContentItemProgress ON ContentItem.ContentItemID = StudentContentItemProgress.ContentItemID WHERE StudentContentItemProgress.Progress = 100 GROUP BY ContentItem.CourseName) AS ProgressSummary ON Course.CourseName = ProgressSummary.CourseName;");

            while (resultSet.next()) {

                String name = resultSet.getString("CourseName");
                String subject = resultSet.getString("Subject");
                String introduction = resultSet.getString("IntroductionText");
                int difficulty = resultSet.getInt("Difficulty");
                int numStudentsWithFullProgress = resultSet.getInt("NumStudentsWithFullProgress");

                Course temp = new Course(subject, name, introduction, difficulty, numStudentsWithFullProgress);
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
                stage.setScene(addCourse());
                stage.show();

            });

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Courses;
    }

    @Override
    public void start(Stage stage) throws Exception {
    }

    public Scene addCourse() {
        GridPane grid = new GridPane();

        Text scenetitle = new Text("Nieuwe Cursus toevoegen:");
        grid.add(scenetitle, 0, 0, 2, 1);

        // email input form
        Label name = new Label("Naam Cursus:");
        grid.add(name, 0, 1);
        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 1);

        // naam input form
        Label subject = new Label("Onderwerp Course:");
        grid.add(subject, 0, 2);
        TextField subjectTextField = new TextField();
        grid.add(subjectTextField, 1, 2);

        // sex input form
        Label introduction = new Label("Introductie: ");
        grid.add(introduction, 0, 4);
        TextField IntroductionTextField = new TextField();
        grid.add(IntroductionTextField, 1, 4);

        // adress input form
        Label difficulty = new Label("Moeilijkheid met nummer: ");
        grid.add(difficulty, 0, 5);
        TextField difficultyTextField = new TextField();
        grid.add(difficultyTextField, 1, 5);

        Button submit = new Button("Voeg toe");
        grid.getChildren().add(submit);

        submit.setOnAction((EventHandler) -> {

            dbConnection.executeSQLUpdateStatement(String.format(
                    "INSERT INTO Course (CourseName, Subject, IntroductionText, Difficulty) VALUES ( '%1$s' , '%2$s' , '%3$s' , '%4$s')",
                    nameTextField.getText(), subjectTextField.getText(), IntroductionTextField.getText(),
                    difficultyTextField.getText()));

            Node node = (Node) EventHandler.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();

            Stage stage = new Stage();
            stage.setScene(Courses());
            stage.show();
        });

        return new Scene(grid);
    }

}
