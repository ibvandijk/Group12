package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import Datastorage.DatabaseConnection;

public class ApplicationController extends Application {
    Scene index;

    // Scene courses = new CourseController();
    Scene cursists = new CursistController().Cursists();
    Scene courses = new CourseController().Courses();
    Scene progressOnModules = new StudentModuleProgressController().viewModule();
    Scene webcasts = new WebcastController().viewWebcast();

    Stage window;

    @Override
    public void start(Stage window) throws Exception {
        this.window = window;
        BorderPane layout = new BorderPane();
        VBox list = new VBox();
        HBox buttons = new HBox();

        // add more scenes
        Button Cursists = new Button("Cursisten zien en wijzigen");
        Cursists.setOnAction((Action) -> {
            window.setScene(cursists);
        });

        Button Cursussen = new Button("Cursussen zien en wijzigen");
        Cursussen.setOnAction((Action) -> {
            window.setScene(courses);
        });

        Button progress = new Button("Gemiddeld progress bekijken per module");
        progress.setOnAction((Action) -> {
            window.setScene(progressOnModules);
        });

        Button webcast = new Button("Bekijk de top drie meest bekeken webcasts");
        webcast.setOnAction((Action) -> {
            window.setScene(webcasts);
        });

        buttons.getChildren().addAll(Cursists, Cursussen, progress, webcast /* add the new scene buttons */);
        list.getChildren().add(buttons);
        layout.setCenter(list);
        this.index = new Scene(layout);
        window.setScene(index);
        window.show();
    }

}
