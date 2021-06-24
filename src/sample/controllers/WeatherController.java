package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.models.WeatherManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class WeatherController {

  private static final String API_KEY = "900ea254740cee343805d46750147cdc";
  private static final String DEFAULT_CITY = "Kyiv";
  private static final String UNITS = "\u2103";
  private static final String CELSIUS = "\u00B0";
  private static final String METRIC = "metric";
  private static final String SPEED = "km/h";

  @FXML private Text description;

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


  WeatherManager weatherManager;

  @FXML
  void initialize() {

    getContent(DEFAULT_CITY);

    getData.setOnAction(
        event -> {
          String userCity = city.getText().trim();
          getContent(userCity);
          //weatherManager.getCurrentWeather(userCity);
        });
  }

//  public void getDayTime() {
//    var formatter = new SimpleDateFormat("HH:mm");
//    var time = new Date(System.currentTimeMillis());
//
//    currentDayOfWeek.setText(LocalDate.now().getDayOfWeek().name() + ", ");
//    currentTime.setText(formatter.format(time));
//  }

  public void getContent(String city) {
    weatherManager = new WeatherManager();
    weatherManager.getCurrentWeather(city);

    //getDayTime();

    currentDayOfWeek.setText(weatherManager.getCurrentDayOfWeek());
    currentTime.setText(weatherManager.getCurrentTime());

    tempInfo.setText(String.valueOf(weatherManager.getTempCurrent()));
    tempFeels.setText("Feels like" + weatherManager.getTempFeelsLike());
    tempMax.setText("Max: " + weatherManager.getTempMax());
    tempMin.setText("Min: " + weatherManager.getTempMin());
    tempCity.setText(city + ", " + weatherManager.getCity().getCountryCode());

    weatherCondition.setImage(weatherManager.getIcon());

    windSpeed.setText(weatherManager.getWindSpeed());
    sunrise.setText(weatherManager.getSunrise());
    sunset.setText(weatherManager.getSunset());
    visibility.setText(weatherManager.getVisibility());
    humidity.setText(weatherManager.getHumidity());
    description.setText(weatherManager.getUvIndex());

//        var webEngine = webView.getEngine();
//        var url = this.getClass().getResource("OpenWeatherMapLayer.html");
//        webEngine.load(url.toString());


      }

      //System.out.println(output);
    //}
  //}

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

    var date = new java.util.Date(timeStamp * 1000);

    var simpleDateformat = new SimpleDateFormat("E", Locale.ENGLISH);

    return simpleDateformat.format(date);
  }
}
