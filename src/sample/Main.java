package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Objects;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("Weather");
    primaryStage.setScene(new Scene(root, 1920, 1080));
    primaryStage.setResizable(false);
    primaryStage.setMaximized(true);
    primaryStage.getIcons().add(new Image("/sample/assets/icon.png"));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
