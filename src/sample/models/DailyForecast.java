package sample.models;

import javafx.scene.image.Image;

public class DailyForecast {

  private String dayOfWeek;

  private City city;

  private String humidity;

  private String sunrise;

  private String sunset;

  private String windSpeed;

  private String tempMin;

  private String tempMax;

  private String dayTemp;

  private String nightTemp;

  private String tempFeelsLike;

  private Image icon;

  private String uvIndex;

  private String description;

  public DailyForecast(
      String dayOfWeek,
      City city,
      String humidity,
      String sunrise,
      String sunset,
      String windSpeed,
      String tempMin,
      String tempMax,
      String dayTemp,
      String nightTemp,
      String tempFeelsLike,
      Image icon,
      String uvIndex,
      String description) {
    this.dayOfWeek = dayOfWeek;
    this.city = city;
    this.humidity = humidity;
    this.sunrise = sunrise;
    this.sunset = sunset;
    this.windSpeed = windSpeed;
    this.tempMin = tempMin;
    this.tempMax = tempMax;
    this.dayTemp = dayTemp;
    this.nightTemp = nightTemp;
    this.tempFeelsLike = tempFeelsLike;
    this.icon = icon;
    this.uvIndex = uvIndex;
    this.description = description;
  }

  public String getDayOfWeek() {
    return dayOfWeek;
  }

  public City getCity() {
    return city;
  }

  public String getHumidity() {
    return humidity;
  }

  public String getSunrise() {
    return sunrise;
  }

  public String getSunset() {
    return sunset;
  }

  public String getWindSpeed() {
    return windSpeed;
  }

  public String getTempMin() {
    return tempMin;
  }

  public String getTempMax() {
    return tempMax;
  }

  public String getDayTemp() {
    return dayTemp;
  }

  public String getNightTemp() {
    return nightTemp;
  }

  public String getTempFeelsLike() {
    return tempFeelsLike;
  }

  public Image getIcon() {
    return icon;
  }

  public String getUvIndex() {
    return uvIndex;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "DailyForecast{"
        + "timeStamp='"
        + dayOfWeek
        + '\''
        + ", city="
        + city
        + ", humidity='"
        + humidity
        + '\''
        + ", sunrise='"
        + sunrise
        + '\''
        + ", sunset='"
        + sunset
        + '\''
        + ", windSpeed='"
        + windSpeed
        + '\''
        + ", tempMin='"
        + tempMin
        + '\''
        + ", tempMax='"
        + tempMax
        + '\''
        + ", dayTemp='"
        + dayTemp
        + '\''
        + ", nightTemp='"
        + nightTemp
        + '\''
        + ", tempFeelsLike='"
        + tempFeelsLike
        + '\''
        + ", icon='"
        + icon
        + '\''
        + ", uvIndex='"
        + uvIndex
        + '\''
        + ", description='"
        + description
        + '\''
        + '}';
  }
}
