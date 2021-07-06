package dev.konstantin.controllers;

import dev.konstantin.Units;
import dev.konstantin.exceptions.WeatherManagerException;
import dev.konstantin.models.DailyForecast;
import dev.konstantin.service.WeatherManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import org.jetbrains.annotations.NotNull;

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
    } catch (WeatherManagerException ex) {
      ex.printStackTrace();
    }

    getData.setDefaultButton(true);
    getData.setOnAction(
        event -> {
          String userCity = city.getText().trim();
          city.clear();
          try {
            getContent(userCity);
          } catch (WeatherManagerException ex) {
            ex.printStackTrace();
          }
        });
  }

  public void getContent(@NotNull String city) throws WeatherManagerException {

    if (city.isEmpty()) {
     throw new WeatherManagerException("string is empty.");
    }

    var weatherManager = new WeatherManager();
    List<DailyForecast> map = weatherManager.getSevenDaysForecast(city);

    DailyForecast currentDayForecast = map.get(0);

    currentDayTime.setText(weatherManager.getCurrentDayTime());

    tempInfo.setText(currentDayForecast.getDayTemp() + Units.CELSIUS);
    tempFeels.setText(currentDayForecast.getTempFeelsLike() + Units.CELSIUS);
    tempMax.setText(currentDayForecast.getTempMax() + Units.CELSIUS);
    tempMin.setText(currentDayForecast.getTempMin() + Units.CELSIUS);
    tempCity.setText(
        currentDayForecast.getCity().cityFormat()
            + ", "
            + currentDayForecast.getCity().getCountryCode());

    weatherCondition.setImage(currentDayForecast.getIcon());

    windSpeed.setText(currentDayForecast.getWindSpeed() + Units.SPEED);
    sunrise.setText(currentDayForecast.getSunrise());
    sunset.setText(currentDayForecast.getSunset());
    humidity.setText(currentDayForecast.getHumidity() + Units.PERCENT);
    uvIndex.setText(currentDayForecast.getUvIndex());
    description.setText(currentDayForecast.getDescription());

    var webEngine = webView.getEngine();
    var url = this.getClass().getResource("/views/OpenWeatherMapLayer.html");
    assert url != null;
    webEngine.load(url.toString());

    DailyForecast firstDayForecast = map.get(1);

    firstDayOfWeek.setText(firstDayForecast.getDayOfWeek());
    firstDayImg.setImage(firstDayForecast.getIcon());
    firstDayMin.setText(firstDayForecast.getTempMin() + Units.CELSIUS);
    firstDayMax.setText(firstDayForecast.getTempMax() + Units.CELSIUS);

    DailyForecast secondDayForecast = map.get(2);

    secondDayOfWeek.setText(secondDayForecast.getDayOfWeek());
    secondDayImg.setImage(secondDayForecast.getIcon());
    secondDayMin.setText(secondDayForecast.getTempMin() + Units.CELSIUS);
    secondDayMax.setText(secondDayForecast.getTempMax() + Units.CELSIUS);

    DailyForecast thirdDayForecast = map.get(3);

    thirdDayOfWeek.setText(thirdDayForecast.getDayOfWeek());
    thirdDayImg.setImage(thirdDayForecast.getIcon());
    thirdDayMin.setText(thirdDayForecast.getTempMin() + Units.CELSIUS);
    thirdDayMax.setText(thirdDayForecast.getTempMax() + Units.CELSIUS);

    DailyForecast fourthDayForecast = map.get(4);

    fourthDayOfWeek.setText(fourthDayForecast.getDayOfWeek());
    fourthDayImg.setImage(fourthDayForecast.getIcon());
    fourthDayMin.setText(fourthDayForecast.getTempMin() + Units.CELSIUS);
    fourthDayMax.setText(fourthDayForecast.getTempMax() + Units.CELSIUS);

    DailyForecast fifthDayForecast = map.get(5);

    fifthDayOfWeek.setText(fifthDayForecast.getDayOfWeek());
    fifthDayImg.setImage(fifthDayForecast.getIcon());
    fifthDayMin.setText(fifthDayForecast.getTempMin() + Units.CELSIUS);
    fifthDayMax.setText(fifthDayForecast.getTempMax() + Units.CELSIUS);

    DailyForecast sixDayForecast = map.get(6);

    sixDayOfWeek.setText(sixDayForecast.getDayOfWeek());
    sixDayImg.setImage(sixDayForecast.getIcon());
    sixDayMin.setText(sixDayForecast.getTempMin() + Units.CELSIUS);
    sixDayMax.setText(sixDayForecast.getTempMax() + Units.CELSIUS);

    DailyForecast sevenDayForecast = map.get(7);

    sevenDayOfWeek.setText(sevenDayForecast.getDayOfWeek());
    sevenDayImg.setImage(sevenDayForecast.getIcon());
    sevenDayMin.setText(sevenDayForecast.getTempMin() + Units.CELSIUS);
    sevenDayMax.setText(sevenDayForecast.getTempMax() + Units.CELSIUS);
  }
}
