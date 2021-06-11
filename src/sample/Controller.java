package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Controller {

  private static final String API_KEY = "900ea254740cee343805d46750147cdc";

  @FXML private Button getData;

  @FXML private TextField city;

  @FXML private Text temp_info;

  @FXML private Text temp_feels;

  @FXML private Text temp_max;

  @FXML private Text temp_min;

  @FXML private Text temp_pressure;

  @FXML
  void initialize() {
    getData.setOnAction(
        event -> {
          String getUserCity = city.getText().trim();
          if (!getUserCity.equals("")) {
            String output =
                getUrlContent(
                    "https://api.openweathermap.org/data/2.5/weather?q="
                        + getUserCity
                        + "&appid="
                        + API_KEY
                        + "&units=metric");

            if (!output.isEmpty()) {
              JSONObject json = new JSONObject(output);
              temp_info.setText("Temperature: " + json.getJSONObject("main").getDouble("temp"));
              temp_feels.setText(
                  "Feels like: " + json.getJSONObject("main").getDouble("feels_like"));
              temp_max.setText("Maximum: " + json.getJSONObject("main").getDouble("temp_max"));
              temp_min.setText("Minimum: " + json.getJSONObject("main").getDouble("temp_min"));
              temp_pressure.setText(
                  "Pressure: " + json.getJSONObject("main").getDouble("pressure"));
            }
            System.out.println(output);
          }
        });
  }

  private static String getUrlContent(String urlAdress) {
    StringBuffer content = new StringBuffer();

    try {
      URL url = new URL(urlAdress);
      URLConnection urlConn = url.openConnection();

      BufferedReader bufferedReader =
          new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
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
