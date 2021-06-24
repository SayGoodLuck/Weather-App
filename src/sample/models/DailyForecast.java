package sample.models;

import javafx.scene.image.Image;

public class DailyForecast {
  private City city;

  private String humidity;

  private String visibility;

  private String sunrise;

  private String sunset;

  private String windSpeed;

  private String tempMax;

  private String tempMin;

  private String tempCurrent;

  private String tempFeelsLike;

  private Image icon;

  private String currentDayOfWeek;

  private String currentTime;

  private String uvIndex;

  public DailyForecast(
      City city,
      String humidity,
      String visibility,
      String sunrise,
      String sunset,
      String windSpeed,
      String tempMax,
      String tempMin,
      String tempCurrent,
      String tempFeelsLike,
      Image icon,
      String currentDayOfWeek,
      String uvIndex) {
    this.city = city;
    this.humidity = humidity;
    this.visibility = visibility;
    this.sunrise = sunrise;
    this.sunset = sunset;
    this.windSpeed = windSpeed;
    this.tempMax = tempMax;
    this.tempMin = tempMin;
    this.tempCurrent = tempCurrent;
    this.tempFeelsLike = tempFeelsLike;
    this.icon = icon;
    this.currentDayOfWeek = currentDayOfWeek;
    this.uvIndex = uvIndex;
  }

}
