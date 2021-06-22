package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class WeatherController {

  private static final String API_KEY = "900ea254740cee343805d46750147cdc";
  private static final String DEFAULT_CITY = "Kyiv";
  private static final String UNITS = "\u2103";
  private static final String CELSIUS = "\u00B0";

  @FXML private Text firstDayMinMax;

  @FXML private ImageView firstDayImg;

  @FXML private Text firstDayOfWeek;

  @FXML private Text secondDayMinMax;

  @FXML private ImageView secondDayImg;

  @FXML private Text secondDayOfWeek;

  @FXML private Text thirdDayMinMax;

  @FXML private ImageView thirdDayImg;

  @FXML private Text thirdDayOfWeek;

  @FXML private Text fourthDayMinMax;

  @FXML private ImageView fourthDayImg;

  @FXML private Text fourthDayOfWeek;

  @FXML private Text fifthDayMinMax;

  @FXML private ImageView fifthDayImg;

  @FXML private Text fifthDayOfWeek;

  @FXML private Text sixDayMinMax;

  @FXML private ImageView sixDayImg;

  @FXML private Text sixDayOfWeek;

  @FXML private Text sevenDayMinMax;

  @FXML private ImageView sevenDayImg;

  @FXML private Text sevenDayOfWeek;

  @FXML private Button getData;

  @FXML private TextField city;

  @FXML private Text temp_info;

  @FXML private Text temp_feels;

  @FXML private Text temp_max;

  @FXML private Text temp_min;

  @FXML private Text temp_pressure;

  @FXML private Text temp_city;

  @FXML private ImageView weather_condition;

  @FXML private WebView map;

  @FXML
  void initialize() {
    getContent(DEFAULT_CITY);

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

        float lat = json.getJSONObject("coord").getFloat("lat");
        float lon = json.getJSONObject("coord").getFloat("lon");

        getSevenDaysContent(lat, lon);

        temp_info.setText(json.getJSONObject("main").getInt("temp") + UNITS);
        temp_feels.setText(
            "Feels like: " + json.getJSONObject("main").getInt("feels_like") + UNITS);
        temp_max.setText("Max: " + json.getJSONObject("main").getInt("temp_max") + UNITS);
        temp_min.setText("Min: " + json.getJSONObject("main").getInt("temp_min") + UNITS);
        temp_pressure.setText("Pressure: " + json.getJSONObject("main").getInt("pressure"));
        temp_city.setText(city + ", " + json.getJSONObject("sys").getString("country"));

        // var result = json.getJSONArray("weather");
        // var iconCode = result.getJSONObject(0).getString("icon");
        // var image = new Image("https://openweathermap.org/img/w/" + iconCode + ".png");
        var image = new Image(getImageUrl(json));
        weather_condition.setImage(image);
      }
      // WebEngine webEngine = new
      // WebEngine(getClass().getResource("OpenWeatherMapLayer.html").toString());
      // map.getEngine(webEngine);
      System.out.println(output);
    }
  }

  public void getSevenDaysContent(float lat, float lon) {

    String output =
        getUrlContent(
            "https://api.openweathermap.org/data/2.5/onecall?lat="
                + lat
                + "&lon="
                + lon
                + "&exclude=current,minutely,hourly,alerts&appid="
                + API_KEY
                + "&units=metric");

    var json = new JSONObject(output);
    var jsonArray = json.getJSONArray("daily");

    for (int i = 0; i < jsonArray.length(); i++) {
      System.out.println(jsonArray.getJSONObject(i));
    }

    firstDayMinMax.setText(getMinMaxValues(jsonArray, 0));
    var image = new Image(getImageUrl(jsonArray.getJSONObject(0)));
    firstDayImg.setImage(image);
    long timeStamp = jsonArray.getJSONObject(0).getLong("dt");
    firstDayOfWeek.setText(extractDayOfWeek(timeStamp));

    secondDayMinMax.setText(getMinMaxValues(jsonArray, 1));
    image = new Image(getImageUrl(jsonArray.getJSONObject(1)));
    secondDayImg.setImage(image);
    timeStamp = jsonArray.getJSONObject(1).getLong("dt");
    secondDayOfWeek.setText(extractDayOfWeek(timeStamp));

    thirdDayMinMax.setText(getMinMaxValues(jsonArray, 2));
    image = new Image(getImageUrl(jsonArray.getJSONObject(2)));
    thirdDayImg.setImage(image);
    timeStamp = jsonArray.getJSONObject(2).getLong("dt");
    thirdDayOfWeek.setText(extractDayOfWeek(timeStamp));

    fourthDayMinMax.setText(getMinMaxValues(jsonArray, 3));
    image = new Image(getImageUrl(jsonArray.getJSONObject(3)));
    fourthDayImg.setImage(image);
    timeStamp = jsonArray.getJSONObject(3).getLong("dt");
    fourthDayOfWeek.setText(extractDayOfWeek(timeStamp));

    fifthDayMinMax.setText(getMinMaxValues(jsonArray, 4));
    image = new Image(getImageUrl(jsonArray.getJSONObject(4)));
    fifthDayImg.setImage(image);
    timeStamp = jsonArray.getJSONObject(4).getLong("dt");
    fifthDayOfWeek.setText(extractDayOfWeek(timeStamp));

    sixDayMinMax.setText(getMinMaxValues(jsonArray, 5));
    image = new Image(getImageUrl(jsonArray.getJSONObject(5)));
    sixDayImg.setImage(image);
    timeStamp = jsonArray.getJSONObject(5).getLong("dt");
    sixDayOfWeek.setText(extractDayOfWeek(timeStamp));

    sevenDayMinMax.setText(getMinMaxValues(jsonArray, 6));
    image = new Image(getImageUrl(jsonArray.getJSONObject(6)));
    sevenDayImg.setImage(image);
    timeStamp = jsonArray.getJSONObject(6).getLong("dt");
    sevenDayOfWeek.setText(extractDayOfWeek(timeStamp));
  }

  private String getMinMaxValues(JSONArray jsonArray, int index) {
    return jsonArray.getJSONObject(index).getJSONObject("temp").getInt("min")
        + CELSIUS
        + " "
        + jsonArray.getJSONObject(index).getJSONObject("temp").getInt("max")
        + CELSIUS;
  }

  private String extractDayOfWeek(long timeStamp) {

    var date = new java.util.Date(timeStamp * 1000);

    var simpleDateformat =
        new SimpleDateFormat("E", Locale.ENGLISH); // the day of the week abbreviated

    return simpleDateformat.format(date);
  }

  private String getImageUrl(JSONObject json) {
    var result = json.getJSONArray("weather");
    var iconCode = result.getJSONObject(0).getString("icon");
    return "https://openweathermap.org/img/w/" + iconCode + ".png";
  }

  private static String getUrlContent(String urlAddress) {
    var content = new StringBuilder();

    try {
      var url = new URL(urlAddress);
      URLConnection urlConn = url.openConnection();

      var bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        content.append(line).append("\n");
      }

      bufferedReader.close();
    } catch (Exception ex) {
      System.out.println("City not found");
      ex.printStackTrace();
    }
    return content.toString();
  }
}
