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

        buttons.getChildren().addAll(Cursists /* add the new scene buttons */);
        list.getChildren().add(buttons);
        layout.setCenter(list);
        this.index = new Scene(layout);
        window.setScene(index);
        window.show();
    }

}
