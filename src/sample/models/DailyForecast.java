package sample.models;

public class DailyForecast {

  private String timeStamp;

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

  private String icon;

  private String uvIndex;

  private String description;

  public DailyForecast(
      String timeStamp,
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
      String icon,
      String uvIndex,
      String description) {
    this.timeStamp = timeStamp;
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

  @Override
  public String toString() {
    return "DailyForecast{"
        + "timeStamp='"
        + timeStamp
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
