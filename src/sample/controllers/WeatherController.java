package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.json.JSONArray;
import sample.models.DailyForecast;
import sample.models.WeatherManager;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class WeatherController {

  private static final String DEFAULT_CITY = "kyiv";
  private static final String CELSIUS = "\u00B0";

  @FXML private Text uvIndex;

  @FXML private Text humidity;

  @FXML private Text currentTime;

  @FXML private Text currentDayOfWeek;

  @FXML private Text visibility;

  @FXML private Text sunset;

  @FXML private Text sunrise;

  @FXML private Text windSpeed;

  @FXML private WebView webView;

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

  @FXML private Text tempInfo;

  @FXML private Text tempFeels;

  @FXML private Text tempMax;

  @FXML private Text tempMin;

  @FXML private Text tempCity;

  @FXML private ImageView weatherCondition;

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

    if (city.isEmpty()) {
      System.out.println("city is null");
      return;
    }

    WeatherManager weatherManager = new WeatherManager();
    Map<Integer, DailyForecast> map = weatherManager.getSevenDaysForecast(city);

    DailyForecast dailyForecast = map.get(0);

    currentTime.setText(weatherManager.getCurrentDateTime());

    //todo
    //currentDayOfWeek.setText(dailyForecast.getDayOfWeek());


    tempInfo.setText(dailyForecast.getDayTemp());
    tempFeels.setText("Feels like: " + dailyForecast.getTempFeelsLike());
    tempMax.setText("Max: " + dailyForecast.getTempMax());
    tempMin.setText("Min: " + dailyForecast.getTempMin());
    tempCity.setText(city + ", " + dailyForecast.getCity().getCountryCode());

    weatherCondition.setImage(dailyForecast.getIcon());

    windSpeed.setText(dailyForecast.getWindSpeed());
    sunrise.setText(dailyForecast.getSunrise());
    sunset.setText(dailyForecast.getSunset());
//    visibility.setText(weatherManager.getVisibility());
    humidity.setText(dailyForecast.getHumidity());
    uvIndex.setText(dailyForecast.getUvIndex());

//    WebEngine webEngine = webView.getEngine();
//    URL url = this.getClass().getResource("OpenWeatherMapLayer.html");
//    assert url != null;
//    webEngine.load(url.toString());
  }

  public void getSevenDaysContent(float lat, float lon) {

    //    String output =
    //        getUrlContent(
    //            "https://api.openweathermap.org/data/2.5/onecall?lat="
    //                + lat
    //                + "&lon="
    //                + lon
    //                + "&exclude=current,minutely,hourly,alerts&appid="
    //                + API_KEY
    //                + "&units="
    //                + METRIC);

    //    var json = new JSONObject(output);
    //    var jsonArray = json.getJSONArray("daily");
    //
    //    for (var i = 0; i < jsonArray.length(); i++) {
    //      System.out.println(jsonArray.getJSONObject(i));
    //    }
    //
    //    firstDayMinMax.setText(getMinMaxValues(jsonArray, 0));
    //    var image = new Image(getImageUrl(jsonArray.getJSONObject(0)));
    //    firstDayImg.setImage(image);
    //    var timeStamp = jsonArray.getJSONObject(0).getLong("dt");
    //    firstDayOfWeek.setText(extractDayOfWeek(timeStamp));
    //
    //    secondDayMinMax.setText(getMinMaxValues(jsonArray, 1));
    //    image = new Image(getImageUrl(jsonArray.getJSONObject(1)));
    //    secondDayImg.setImage(image);
    //    timeStamp = jsonArray.getJSONObject(1).getLong("dt");
    //    secondDayOfWeek.setText(extractDayOfWeek(timeStamp));
    //
    //    thirdDayMinMax.setText(getMinMaxValues(jsonArray, 2));
    //    image = new Image(getImageUrl(jsonArray.getJSONObject(2)));
    //    thirdDayImg.setImage(image);
    //    timeStamp = jsonArray.getJSONObject(2).getLong("dt");
    //    thirdDayOfWeek.setText(extractDayOfWeek(timeStamp));
    //
    //    fourthDayMinMax.setText(getMinMaxValues(jsonArray, 3));
    //    image = new Image(getImageUrl(jsonArray.getJSONObject(3)));
    //    fourthDayImg.setImage(image);
    //    timeStamp = jsonArray.getJSONObject(3).getLong("dt");
    //    fourthDayOfWeek.setText(extractDayOfWeek(timeStamp));
    //
    //    fifthDayMinMax.setText(getMinMaxValues(jsonArray, 4));
    //    image = new Image(getImageUrl(jsonArray.getJSONObject(4)));
    //    fifthDayImg.setImage(image);
    //    timeStamp = jsonArray.getJSONObject(4).getLong("dt");
    //    fifthDayOfWeek.setText(extractDayOfWeek(timeStamp));
    //
    //    sixDayMinMax.setText(getMinMaxValues(jsonArray, 5));
    //    image = new Image(getImageUrl(jsonArray.getJSONObject(5)));
    //    sixDayImg.setImage(image);
    //    timeStamp = jsonArray.getJSONObject(5).getLong("dt");
    //    sixDayOfWeek.setText(extractDayOfWeek(timeStamp));
    //
    //    sevenDayMinMax.setText(getMinMaxValues(jsonArray, 6));
    //    image = new Image(getImageUrl(jsonArray.getJSONObject(6)));
    //    sevenDayImg.setImage(image);
    //    timeStamp = jsonArray.getJSONObject(6).getLong("dt");
    //    sevenDayOfWeek.setText(extractDayOfWeek(timeStamp));
  }

  private String getMinMaxValues(JSONArray jsonArray, int index) {
    return jsonArray.getJSONObject(index).getJSONObject("temp").getInt("min")
        + CELSIUS
        + " "
        + jsonArray.getJSONObject(index).getJSONObject("temp").getInt("max")
        + CELSIUS;
  }

  private String extractDayOfWeek(long timeStamp) {

    Date date = new java.util.Date(timeStamp * 1000);

    SimpleDateFormat simpleDateformat = new SimpleDateFormat("E", Locale.ENGLISH);

    return simpleDateformat.format(date);
  }
}
