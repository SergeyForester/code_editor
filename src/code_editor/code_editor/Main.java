package code_editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/static/main.fxml"));
        primaryStage.setTitle("CodeRush");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setHeight(800);
        primaryStage.setWidth(1200);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
