package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("weather-app.fxml")));
    primaryStage.setTitle("Weather");
    var scene = new Scene(root, 1280, 720);
    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.setMaximized(false);
    primaryStage.getIcons().add(new Image("/sample/assets/icon.png"));

    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
