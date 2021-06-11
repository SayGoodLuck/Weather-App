package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Controller {

  private static final String API_KEY = "900ea254740cee343805d46750147cdc";

  private static final String defaultCity = "Kyiv";
  private static final String units = "\u2103";

  @FXML private Button getData;

  @FXML private TextField city;

  @FXML private Text temp_info;

  @FXML private Text temp_feels;

  @FXML private Text temp_max;

  @FXML private Text temp_min;

  @FXML private Text temp_pressure;

  @FXML private Text temp_city;

  @FXML public ImageView weather_condition;

  @FXML
  void initialize() {

    getContent(defaultCity);

    getData.setOnAction(
        event -> {
          String userCity = city.getText().trim();
          getContent(userCity);
        });
  }

  public void getContent(String city) {

    if (!city.equals("")) {
      String output =
          getUrlContent(
              "https://api.openweathermap.org/data/2.5/weather?q="
                  + city
                  + "&appid="
                  + API_KEY
                  + "&units=metric");

      if (!output.isEmpty()) {
        var json = new JSONObject(output);
        temp_info.setText(json.getJSONObject("main").getInt("temp") + units);
        temp_feels.setText("Feels like: " + json.getJSONObject("main").getDouble("feels_like"));
        temp_max.setText("Max: " + json.getJSONObject("main").getInt("temp_max") + units);
        temp_min.setText("Min: " + json.getJSONObject("main").getInt("temp_min") + units);
        temp_pressure.setText("Pressure: " + json.getJSONObject("main").getInt("pressure"));
        temp_city.setText(city + ", " + json.getJSONObject("sys").getString("country"));

        if (json.has("weather")) {
          var result = json.getJSONArray("weather");
          var iconCode = result.getJSONObject(0).getString("icon");
          var image = new Image("https://openweathermap.org/img/w/" + iconCode + ".png");
          weather_condition.setImage(image);
        }
      }
      System.out.println(output);
    }
  }

  private static String getUrlContent(String urlAddress) {
    var content = new StringBuilder();

    try {
      var url = new URL(urlAddress);
      URLConnection urlConn = url.openConnection();

      var bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        content.append(line + "\n");
      }

      bufferedReader.close();
    } catch (Exception ex) {
      System.out.println("City not found");
      ex.printStackTrace();
    }
    return content.toString();
  }
}
