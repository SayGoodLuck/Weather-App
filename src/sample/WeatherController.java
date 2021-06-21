package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class WeatherController {

  private List<StackPane> sevenDaysForecast;

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

    //    sevenDaysForecast = new ArrayList<>();
    //
    //    sevenDaysForecast.add(firstDay);
    //    sevenDaysForecast.add(secondDay);
    //    sevenDaysForecast.add(thirdDay);

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
        var image = new Image(getImageUrl(json, "weather"));
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

    firstDayMinMax.setText(
        jsonArray.getJSONObject(0).getJSONObject("temp").getInt("min")
            + CELSIUS
            + " "
            + jsonArray.getJSONObject(0).getJSONObject("temp").getInt("max")
            + CELSIUS);

    var image = new Image(getImageUrl(jsonArray.getJSONObject(0), "weather"));

    firstDayImg.setImage(image);

    long timeStamp = jsonArray.getJSONObject(0).getLong("dt");

    firstDayOfWeek.setText(extractDayOfWeek(timeStamp));

    secondDayMinMax.setText(
            jsonArray.getJSONObject(1).getJSONObject("temp").getInt("min")
                    + CELSIUS
                    + " "
                    + jsonArray.getJSONObject(1).getJSONObject("temp").getInt("max")
                    + CELSIUS);

     image = new Image(getImageUrl(jsonArray.getJSONObject(1), "weather"));

    secondDayImg.setImage(image);

     timeStamp = jsonArray.getJSONObject(1).getLong("dt");

    secondDayOfWeek.setText(extractDayOfWeek(timeStamp));

    thirdDayMinMax.setText(
            jsonArray.getJSONObject(2).getJSONObject("temp").getInt("min")
                    + CELSIUS
                    + " "
                    + jsonArray.getJSONObject(2).getJSONObject("temp").getInt("max")
                    + CELSIUS);

    image = new Image(getImageUrl(jsonArray.getJSONObject(2), "weather"));

    thirdDayImg.setImage(image);

    timeStamp = jsonArray.getJSONObject(2).getLong("dt");

    thirdDayOfWeek.setText(extractDayOfWeek(timeStamp));

    fourthDayMinMax.setText(
            jsonArray.getJSONObject(3).getJSONObject("temp").getInt("min")
                    + CELSIUS
                    + " "
                    + jsonArray.getJSONObject(3).getJSONObject("temp").getInt("max")
                    + CELSIUS);

    image = new Image(getImageUrl(jsonArray.getJSONObject(3), "weather"));

    fourthDayImg.setImage(image);

    timeStamp = jsonArray.getJSONObject(3).getLong("dt");

    fourthDayOfWeek.setText(extractDayOfWeek(timeStamp));

    fifthDayMinMax.setText(
            jsonArray.getJSONObject(4).getJSONObject("temp").getInt("min")
                    + CELSIUS
                    + " "
                    + jsonArray.getJSONObject(4).getJSONObject("temp").getInt("max")
                    + CELSIUS);

    image = new Image(getImageUrl(jsonArray.getJSONObject(4), "weather"));

    fifthDayImg.setImage(image);

    timeStamp = jsonArray.getJSONObject(4).getLong("dt");

    fifthDayOfWeek.setText(extractDayOfWeek(timeStamp));

    sixDayMinMax.setText(
            jsonArray.getJSONObject(5).getJSONObject("temp").getInt("min")
                    + CELSIUS
                    + " "
                    + jsonArray.getJSONObject(5).getJSONObject("temp").getInt("max")
                    + CELSIUS);

    image = new Image(getImageUrl(jsonArray.getJSONObject(5), "weather"));

    sixDayImg.setImage(image);

    timeStamp = jsonArray.getJSONObject(5).getLong("dt");

    sixDayOfWeek.setText(extractDayOfWeek(timeStamp));

    sevenDayMinMax.setText(
            jsonArray.getJSONObject(6).getJSONObject("temp").getInt("min")
                    + CELSIUS
                    + " "
                    + jsonArray.getJSONObject(6).getJSONObject("temp").getInt("max")
                    + CELSIUS);

    image = new Image(getImageUrl(jsonArray.getJSONObject(6), "weather"));

    sevenDayImg.setImage(image);

    timeStamp = jsonArray.getJSONObject(6).getLong("dt");

    sevenDayOfWeek.setText(extractDayOfWeek(timeStamp));

  }

  private String extractDayOfWeek(long timeStamp) {

    java.util.Date date = new java.util.Date(timeStamp * 1000);

    SimpleDateFormat simpleDateformat =
        new SimpleDateFormat("E", Locale.ENGLISH); // the day of the week abbreviated

    return simpleDateformat.format(date);
  }

  private static String getImageUrl(JSONObject json, String key) {
    var result = json.getJSONArray(key);
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
