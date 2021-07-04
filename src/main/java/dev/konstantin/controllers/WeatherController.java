package dev.konstantin.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import dev.konstantin.Metrics;
import dev.konstantin.MyOwnException;
import dev.konstantin.models.DailyForecast;
import dev.konstantin.service.WeatherManager;

import java.util.List;

public class WeatherController {

  private static final String DEFAULT_CITY = "kyiv";

  @FXML private Text description;

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

    try {
      getContent(DEFAULT_CITY);
    } catch (MyOwnException ex) {
      ex.printStackTrace();
    }

    getData.setOnAction(
        event -> {
          String userCity = city.getText().trim();
          try {
            getContent(userCity);
          } catch (MyOwnException ex) {
            ex.printStackTrace();
          }
        });
  }

  public void getContent(String city) throws MyOwnException {

    if (city.isEmpty()) {
      System.out.println("city is null");
      return;
    }

    var weatherManager = new WeatherManager();
    List<DailyForecast> map = weatherManager.getSevenDaysForecast(city);

    DailyForecast currentDayForecast = map.get(0);

    currentDayTime.setText(weatherManager.getCurrentDayTime());

    tempInfo.setText(currentDayForecast.getDayTemp());
    tempFeels.setText("Feels like: " + currentDayForecast.getTempFeelsLike());
    tempMax.setText("Max: " + currentDayForecast.getTempMax() + Metrics.CELSIUS);
    tempMin.setText("Min: " + currentDayForecast.getTempMin() + Metrics.CELSIUS);
    tempCity.setText(
        currentDayForecast.getCity().cityFormat()
            + ", "
            + currentDayForecast.getCity().getCountryCode());

    weatherCondition.setImage(currentDayForecast.getIcon());

    windSpeed.setText(currentDayForecast.getWindSpeed() + Metrics.SPEED);
    sunrise.setText(currentDayForecast.getSunrise());
    sunset.setText(currentDayForecast.getSunset());
    humidity.setText(currentDayForecast.getHumidity() + Metrics.PERCENT);
    uvIndex.setText(currentDayForecast.getUvIndex());
    description.setText(currentDayForecast.getDescription());


    var webEngine = webView.getEngine();
    var url = this.getClass().getResource("/views/OpenWeatherMapLayer.html");
    assert url != null;
    webEngine.load(url.toString());

    DailyForecast firstDayForecast = map.get(1);

    firstDayOfWeek.setText(firstDayForecast.getDayOfWeek());
    firstDayImg.setImage(firstDayForecast.getIcon());
    firstDayMin.setText(firstDayForecast.getTempMin() + Metrics.CELSIUS);
    firstDayMax.setText(firstDayForecast.getTempMax() + Metrics.CELSIUS);

    DailyForecast secondDayForecast = map.get(2);

    secondDayOfWeek.setText(secondDayForecast.getDayOfWeek());
    secondDayImg.setImage(secondDayForecast.getIcon());
    secondDayMin.setText(secondDayForecast.getTempMin() + Metrics.CELSIUS);
    secondDayMax.setText(secondDayForecast.getTempMax() + Metrics.CELSIUS);

    DailyForecast thirdDayForecast = map.get(3);

    thirdDayOfWeek.setText(thirdDayForecast.getDayOfWeek());
    thirdDayImg.setImage(thirdDayForecast.getIcon());
    thirdDayMin.setText(thirdDayForecast.getTempMin() + Metrics.CELSIUS);
    thirdDayMax.setText(thirdDayForecast.getTempMax() + Metrics.CELSIUS);

    DailyForecast fourthDayForecast = map.get(4);

    fourthDayOfWeek.setText(fourthDayForecast.getDayOfWeek());
    fourthDayImg.setImage(fourthDayForecast.getIcon());
    fourthDayMin.setText(fourthDayForecast.getTempMin() + Metrics.CELSIUS);
    fourthDayMax.setText(fourthDayForecast.getTempMax() + Metrics.CELSIUS);

    DailyForecast fifthDayForecast = map.get(5);

    fifthDayOfWeek.setText(fifthDayForecast.getDayOfWeek());
    fifthDayImg.setImage(fifthDayForecast.getIcon());
    fifthDayMin.setText(fifthDayForecast.getTempMin() + Metrics.CELSIUS);
    fifthDayMax.setText(fifthDayForecast.getTempMax() + Metrics.CELSIUS);

    DailyForecast sixDayForecast = map.get(6);

    sixDayOfWeek.setText(sixDayForecast.getDayOfWeek());
    sixDayImg.setImage(sixDayForecast.getIcon());
    sixDayMin.setText(sixDayForecast.getTempMin() + Metrics.CELSIUS);
    sixDayMax.setText(sixDayForecast.getTempMax() + Metrics.CELSIUS);

    DailyForecast sevenDayForecast = map.get(7);

    sevenDayOfWeek.setText(sevenDayForecast.getDayOfWeek());
    sevenDayImg.setImage(sevenDayForecast.getIcon());
    sevenDayMin.setText(sevenDayForecast.getTempMin() + Metrics.CELSIUS);
    sevenDayMax.setText(sevenDayForecast.getTempMax() + Metrics.CELSIUS);
  }
}
