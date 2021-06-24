package sample.models;

import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class WeatherManager {

  private static final String API_KEY = "900ea254740cee343805d46750147cdc";
  private static final String METRIC = "metric";
  private static final String UNITS = "\u2103";
  private static final String SPEED = "km/h";
  private static final String CURRENT = "current";
  private static final String DAILY = "daily";

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

  private DailyForecast dailyForecast;

  public void getCurrentWeather(String cityName) {

    setCityInfo(cityName);

    if (!cityName.equals("")) {
      String output =
          getUrlContent(
              "https://api.openweathermap.org/data/2.5/onecall?lat="
                  + city.getLat()
                  + "&lon="
                  + city.getLon()
                  + "&exclude=minutely,hourly,alerts&appid="
                  + API_KEY
                  + "&units="
                  + METRIC);

      if (!output.isEmpty()) {

        var json = new JSONObject(output);
        var jsonArray = json.getJSONArray(DAILY);

        setTimeInfo();

        setTempCurrent(String.valueOf(json.getJSONObject(CURRENT).getInt("temp")));
        setTempFeelsLike(json.getJSONObject(CURRENT).getFloat("feels_like") + UNITS);

        setTempMax(jsonArray.getJSONObject(0).getJSONObject("temp").getFloat("max") + UNITS);
        setTempMin(jsonArray.getJSONObject(0).getJSONObject("temp").getFloat("min") + UNITS);

        setIcon(new Image(getImageUrl(json)));

        setWindSpeed(json.getJSONObject(CURRENT).getFloat("wind_speed") + SPEED);
        setSunrise(timeStampConvert(json.getJSONObject(CURRENT).getLong("sunrise")));
        setSunset(timeStampConvert(json.getJSONObject(CURRENT).getLong("sunset")));

        var visionRange = json.getJSONObject(CURRENT).getFloat("visibility") / 1000;
        setVisibility(visionRange + " km");

        setHumidity(json.getJSONObject(CURRENT).getInt("humidity") + "%");
        setUvIndex(String.valueOf(json.getJSONObject(CURRENT).getFloat("uvi")));

      }
    }
  }

//  public Map getSevenDaysForecast() {
//    Map<Object, ArrayList<Object>> multiMap = new HashMap<>();
//
//
//  }

  private String timeStampConvert(long timeStamp) {
    var date = new java.util.Date(timeStamp * 1000);

    var simpleDateformat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

    return simpleDateformat.format(date);
  }

  public void setCityInfo(String cityName) {
    String output =
        getUrlContent(
            "http://api.openweathermap.org/geo/1.0/direct?q="
                + cityName
                + "&limit="
                + 5
                + "&appid="
                + API_KEY);

    if (!output.isEmpty()) {
      var json = new JSONArray(output);

      setCity(
          new City(
              json.getJSONObject(0).getString("name"),
              json.getJSONObject(0).getFloat("lon"),
              json.getJSONObject(0).getFloat("lat"),
              json.getJSONObject(0).getString("country")));
    }
  }

  public String getUrlContent(String urlAddress) {
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

  private String getImageUrl(JSONObject json) {
    var result = json.getJSONObject(CURRENT).getJSONArray("weather");
    var iconCode = result.getJSONObject(0).getString("icon");
    return "https://openweathermap.org/img/w/" + iconCode + ".png";
  }

  public void setTimeInfo() {
    var formatter = new SimpleDateFormat("HH:mm");
    var time = new Date(System.currentTimeMillis());

    setCurrentDayOfWeek(LocalDate.now().getDayOfWeek().name() + ", ");
    setCurrentTime(formatter.format(time));
  }

  public City getCity() {
    return city;
  }

  public String getHumidity() {
    return humidity;
  }

  public String getVisibility() {
    return visibility;
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

  public String getTempMax() {
    return tempMax;
  }

  public String getTempMin() {
    return tempMin;
  }

  public String getTempCurrent() {
    return tempCurrent;
  }

  public String getTempFeelsLike() {
    return tempFeelsLike;
  }

  public Image getIcon() {
    return icon;
  }

  public String getCurrentDayOfWeek() {
    return currentDayOfWeek;
  }

  public String getCurrentTime() {
    return currentTime;
  }

  public String getUvIndex() {
    return uvIndex;
  }

  public void setHumidity(String humidity) {
    this.humidity = humidity;
  }

  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }

  public void setSunrise(String sunrise) {
    this.sunrise = sunrise;
  }

  public void setSunset(String sunset) {
    this.sunset = sunset;
  }

  public void setWindSpeed(String windSpeed) {
    this.windSpeed = windSpeed;
  }

  public void setTempMax(String tempMax) {
    this.tempMax = tempMax;
  }

  public void setTempMin(String tempMin) {
    this.tempMin = tempMin;
  }

  public void setTempCurrent(String tempCurrent) {
    this.tempCurrent = tempCurrent;
  }

  public void setTempFeelsLike(String tempFeelsLike) {
    this.tempFeelsLike = tempFeelsLike;
  }

  public void setIcon(Image icon) {
    this.icon = icon;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public void setCurrentDayOfWeek(String currentDayOfWeek) {
    this.currentDayOfWeek = currentDayOfWeek;
  }

  public void setCurrentTime(String currentTime) {
    this.currentTime = currentTime;
  }

  public void setUvIndex(String uvIndex) {
    this.uvIndex = uvIndex;
  }
}
