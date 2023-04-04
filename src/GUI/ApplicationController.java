package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;




public class ApplicationController extends Application {
    Scene index;

    Stage window;

    @Override
    public void start(Stage window) throws Exception {
        this.window = window;
        BorderPane layout = new BorderPane();
        VBox list = new VBox();

        //add more scenes 

        list.getChildren().add(new Label("Group 12"));
        list.getChildren().add(new Label("Ivan van Dijk & Tido Koldenhof"));
        HBox buttons = new HBox();
        Button Courses = new Button("Test Button");
       
        buttons.getChildren().addAll(Courses /* add the new scenes */);
        list.getChildren().add(buttons);
        layout.setCenter(list);
        this.index = new Scene(layout);
        window.setScene(index);
        window.show();
    }

}
