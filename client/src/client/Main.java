package client;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends AbstractApp {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/login.fxml"));
        primaryStage.setTitle(getTitle());
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        primaryStage.show();
    }

    @Override
    public String getTitle() {
        return "Вход";
    }

    public static void main(String[] args) {
        launch(args);
    }
}