package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import sample.models.DailyForecast;
import sample.service.WeatherManager;

import java.util.Map;

public class WeatherController {

  private static final String DEFAULT_CITY = "kyiv";
  private static final String CELSIUS = "\u00B0";

  @FXML private Text uvIndex;

  @FXML private Text humidity;

  @FXML private Text currentDayTime;

  @FXML private Text sunset;

  @FXML private Text sunrise;

  @FXML private Text windSpeed;

  @FXML private WebView webView;

  @FXML private Text firstDayMax;

  @FXML private Text firstDayMin;

  @FXML private ImageView firstDayImg;

  @FXML private Text firstDayOfWeek;

  @FXML private Text secondDayMin;

  @FXML private Text secondDayMax;

  @FXML private ImageView secondDayImg;

  @FXML private Text secondDayOfWeek;

  @FXML private Text thirdDayMin;

  @FXML private Text thirdDayMax;

  @FXML private ImageView thirdDayImg;

  @FXML private Text thirdDayOfWeek;

  @FXML private Text fourthDayMin;

  @FXML private Text fourthDayMax;

  @FXML private ImageView fourthDayImg;

  @FXML private Text fourthDayOfWeek;

  @FXML private Text fifthDayMax;

  @FXML private Text fifthDayMin;

  @FXML private ImageView fifthDayImg;

  @FXML private Text fifthDayOfWeek;

  @FXML private Text sixDayMin;

  @FXML private Text sixDayMax;

  @FXML private ImageView sixDayImg;

  @FXML private Text sixDayOfWeek;

  @FXML private Text sevenDayMin;

  @FXML private Text sevenDayMax;

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

    DailyForecast currentDayForecast = map.get(0);

    currentDayTime.setText(weatherManager.getCurrentDayTime());

    tempInfo.setText(currentDayForecast.getDayTemp());
    tempFeels.setText("Feels like: " + currentDayForecast.getTempFeelsLike());
    tempMax.setText("Max: " + currentDayForecast.getTempMax());
    tempMin.setText("Min: " + currentDayForecast.getTempMin());
    tempCity.setText(currentDayForecast.getCity().cityFormat() + ", " + currentDayForecast.getCity().getCountryCode());

    weatherCondition.setImage(currentDayForecast.getIcon());

    windSpeed.setText(currentDayForecast.getWindSpeed());
    sunrise.setText(currentDayForecast.getSunrise());
    sunset.setText(currentDayForecast.getSunset());
    humidity.setText(currentDayForecast.getHumidity());
    uvIndex.setText(currentDayForecast.getUvIndex());

//    var webEngine = webView.getEngine();
//    var url = this.getClass().getResource("OpenWeatherMapLayer.html");
//    assert url != null;
//    webEngine.load(url.toString());

    DailyForecast firstDayForecast = map.get(1);

    firstDayOfWeek.setText(firstDayForecast.getDayOfWeek());
    firstDayImg.setImage(firstDayForecast.getIcon());
    firstDayMin.setText(firstDayForecast.getTempMin());
    firstDayMax.setText(firstDayForecast.getTempMax());

    DailyForecast secondDayForecast = map.get(2);

    secondDayOfWeek.setText(secondDayForecast.getDayOfWeek());
    secondDayImg.setImage(secondDayForecast.getIcon());
    secondDayMin.setText(secondDayForecast.getTempMin());
    secondDayMax.setText(secondDayForecast.getTempMax());

    DailyForecast thirdDayForecast = map.get(3);

    thirdDayOfWeek.setText(thirdDayForecast.getDayOfWeek());
    thirdDayImg.setImage(thirdDayForecast.getIcon());
    thirdDayMin.setText(thirdDayForecast.getTempMin());
    thirdDayMax.setText(thirdDayForecast.getTempMax());

    DailyForecast fourthDayForecast = map.get(4);

    fourthDayOfWeek.setText(fourthDayForecast.getDayOfWeek());
    fourthDayImg.setImage(fourthDayForecast.getIcon());
    fourthDayMin.setText(fourthDayForecast.getTempMin());
    fourthDayMax.setText(fourthDayForecast.getTempMax());

    DailyForecast fifthDayForecast = map.get(5);

    fifthDayOfWeek.setText(fifthDayForecast.getDayOfWeek());
    fifthDayImg.setImage(fifthDayForecast.getIcon());
    fifthDayMin.setText(fifthDayForecast.getTempMin());
    fifthDayMax.setText(fifthDayForecast.getTempMax());

    DailyForecast sixDayForecast = map.get(6);

    sixDayOfWeek.setText(sixDayForecast.getDayOfWeek());
    sixDayImg.setImage(sixDayForecast.getIcon());
    sixDayMin.setText(sixDayForecast.getTempMin());
    sixDayMax.setText(sixDayForecast.getTempMax());

    DailyForecast sevenDayForecast = map.get(7);

    sevenDayOfWeek.setText(sevenDayForecast.getDayOfWeek());
    sevenDayImg.setImage(sevenDayForecast.getIcon());
    sevenDayMin.setText(sevenDayForecast.getTempMin());
    sevenDayMax.setText(sevenDayForecast.getTempMax());
  }
}
