package GUI;

import Model.Course;
import Datastorage.DatabaseConnection;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.sql.ResultSet;
import java.sql.SQLException;


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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Courses;
    }

    @Override
    public void start(Stage stage) throws Exception {
    }

}
