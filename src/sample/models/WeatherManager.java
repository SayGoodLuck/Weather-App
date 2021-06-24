package sample.models;

import com.google.common.collect.Multimap;
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

  public void getCurrentWeather(String cityName) {

    getCityInfo(cityName);

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

        JSONObject json = new JSONObject(output);
        JSONArray jsonArray = json.getJSONArray(DAILY);

        setTimeInfo();

        setTempCurrent(String.valueOf(json.getJSONObject(CURRENT).getInt("temp")));
        setTempFeelsLike(json.getJSONObject(CURRENT).getFloat("feels_like") + UNITS);

        setTempMax(jsonArray.getJSONObject(0).getJSONObject("temp").getFloat("max") + UNITS);
        setTempMin(jsonArray.getJSONObject(0).getJSONObject("temp").getFloat("min") + UNITS);

        setIcon(new Image(getImageUrl(json)));

        setWindSpeed(json.getJSONObject(CURRENT).getFloat("wind_speed") + SPEED);
        setSunrise(timeStampConvert(json.getJSONObject(CURRENT).getLong("sunrise")));
        setSunset(timeStampConvert(json.getJSONObject(CURRENT).getLong("sunset")));

        float visionRange = json.getJSONObject(CURRENT).getFloat("visibility") / 1000;
        setVisibility(visionRange + " km");

        setHumidity(json.getJSONObject(CURRENT).getInt("humidity") + "%");
        setUvIndex(String.valueOf(json.getJSONObject(CURRENT).getFloat("uvi")));
      }
    }
  }

  public Map<Integer, DailyForecast> getSevenDaysForecast(String cityName) {

    // todo take from json 7 days forecast

    Map<Integer, DailyForecast> map = new HashMap<>();

    City city = getCityInfo(cityName);

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

        JSONObject json = new JSONObject(output);
        JSONArray sevenDays = json.getJSONArray(DAILY);

        for (int index = 0; index < 7; index++) {
          String timeStamp = String.valueOf(sevenDays.getJSONObject(index).getLong("dt"));
          String sunrise = String.valueOf(sevenDays.getJSONObject(index).getLong("sunrise"));
          String sunset = String.valueOf(sevenDays.getJSONObject(index).getLong("sunset"));
          String dayTemperature =
                  String.valueOf(sevenDays.getJSONObject(index).getJSONObject("temp").getInt("day"));
          String nightTemperature =
                  String.valueOf(sevenDays.getJSONObject(index).getJSONObject("temp").getInt("night"));
          String min =
                  String.valueOf(sevenDays.getJSONObject(index).getJSONObject("temp").getInt("min"));
          String max =
                  String.valueOf(sevenDays.getJSONObject(index).getJSONObject("temp").getInt("max"));
          String feelsLike =
                  String.valueOf(
                          sevenDays.getJSONObject(index).getJSONObject("feels_like").getInt("day"));
          String humidity = String.valueOf(sevenDays.getJSONObject(index).getInt("humidity"));
          String windSpeed = String.valueOf(sevenDays.getJSONObject(index).getFloat("wind_speed"));
          String icon =
                  String.valueOf(
                          sevenDays.getJSONObject(index).getJSONArray("weather").getJSONObject(0).getString("icon"));

          String uvIndex = String.valueOf(sevenDays.getJSONObject(index).getFloat("uvi"));
          String description =
                  sevenDays.getJSONObject(index).getJSONArray("weather").getJSONObject(0).getString("description");

          DailyForecast dailyForecast =
                  new DailyForecast(
                          timeStamp,
                          city,
                          humidity,
                          sunrise,
                          sunset,
                          windSpeed,
                          min,
                          max,
                          dayTemperature,
                          nightTemperature,
                          feelsLike,
                          icon,
                          uvIndex,
                          description);

          System.out.println(dailyForecast);
          map.put(index, dailyForecast);
        }
      }
    }


    return map;
  }

  private String timeStampConvert(long timeStamp) {
    Date date = new java.util.Date(timeStamp * 1000);

    SimpleDateFormat simpleDateformat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

    return simpleDateformat.format(date);
  }

  public City getCityInfo(String cityName) {
    String output =
        getUrlContent(
            "http://api.openweathermap.org/geo/1.0/direct?q="
                + cityName
                + "&limit="
                + 5
                + "&appid="
                + API_KEY);

    if (!output.isEmpty()) {
      JSONArray json = new JSONArray(output);

      return new City(
          json.getJSONObject(0).getString("name"),
          json.getJSONObject(0).getFloat("lon"),
          json.getJSONObject(0).getFloat("lat"),
          json.getJSONObject(0).getString("country"));
    }
    // todo
    return null;
  }

  public String getUrlContent(String urlAddress) {
    StringBuilder content = new StringBuilder();

    try {
      URL url = new URL(urlAddress);
      URLConnection urlConn = url.openConnection();

      BufferedReader bufferedReader =
          new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
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
    JSONArray result = json.getJSONObject(CURRENT).getJSONArray("weather");
    String iconCode = result.getJSONObject(0).getString("icon");
    return "https://openweathermap.org/img/w/" + iconCode + ".png";
  }

  public void setTimeInfo() {
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    Date time = new Date(System.currentTimeMillis());

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
