package dev.konstantin.service;

import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import dev.konstantin.MyOwnException;
import dev.konstantin.models.City;
import dev.konstantin.models.DailyForecast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeatherManager {

  private static final String API_KEY = "900ea254740cee343805d46750147cdc";
  private static final String METRIC = "metric";
  private static final String DAILY = "daily";

  public List<DailyForecast> getSevenDaysForecast(String cityName) throws MyOwnException {

    List<DailyForecast> dailyForecasts = new ArrayList<>();

    var city = getCityInfo(cityName);

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
        var sevenDays = json.getJSONArray(DAILY);

        for (var index = 0; index < sevenDays.length(); index++) {
          var timeStamp = sevenDays.getJSONObject(index).getLong("dt");
          var sunrise = sevenDays.getJSONObject(index).getLong("sunrise");
          var sunset = sevenDays.getJSONObject(index).getLong("sunset");
          var dayTemperature =
              String.valueOf(sevenDays.getJSONObject(index).getJSONObject("temp").getInt("day"));
          var nightTemperature =
              String.valueOf(sevenDays.getJSONObject(index).getJSONObject("temp").getInt("night"));
          var min =
              String.valueOf(sevenDays.getJSONObject(index).getJSONObject("temp").getInt("min"));
          var max =
              String.valueOf(sevenDays.getJSONObject(index).getJSONObject("temp").getInt("max"));
          var feelsLike =
              String.valueOf(
                  sevenDays.getJSONObject(index).getJSONObject("feels_like").getInt("day"));
          var humidity = String.valueOf(sevenDays.getJSONObject(index).getInt("humidity"));
          var windSpeed = String.valueOf(sevenDays.getJSONObject(index).getFloat("wind_speed"));
          var iconCode =
              String.valueOf(
                  sevenDays
                      .getJSONObject(index)
                      .getJSONArray("weather")
                      .getJSONObject(0)
                      .getString("icon"));

          var uvIndex = String.valueOf(sevenDays.getJSONObject(index).getFloat("uvi"));
          var description =
              sevenDays
                  .getJSONObject(index)
                  .getJSONArray("weather")
                  .getJSONObject(0)
                  .getString("description");

          var icon = new Image(getImageUrl(iconCode));
          var dailyForecast =
              new DailyForecast(
                  extractDayOfWeek(timeStamp),
                  city,
                  humidity,
                  timeStampConvert(sunrise),
                  timeStampConvert(sunset),
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
          dailyForecasts.add(index, dailyForecast);
        }
      }
    }

    return dailyForecasts;
  }

  public String extractDayOfWeek(long timeStamp) {

    var date = new java.util.Date(timeStamp * 1000);

    var simpleDateformat = new SimpleDateFormat("E", Locale.ENGLISH);

    return simpleDateformat.format(date);
  }

  private String timeStampConvert(long timeStamp) {
    var date = new java.util.Date(timeStamp * 1000);
    var simpleDateformat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    return simpleDateformat.format(date);
  }

  public City getCityInfo(String cityName) throws MyOwnException {
    String output =
        getUrlContent(
            "http://api.openweathermap.org/geo/1.0/direct?q="
                + cityName
                + "&limit="
                + 5
                + "&appid="
                + API_KEY);

    if (output.isEmpty()) {
      System.out.println("output is empty");
      throw new MyOwnException("output is empty");
    }

    var json = new JSONArray(output);

    return new City(
        json.getJSONObject(0).getString("name"),
        json.getJSONObject(0).getFloat("lon"),
        json.getJSONObject(0).getFloat("lat"),
        json.getJSONObject(0).getString("country"));
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

  private String getImageUrl(String iconCode) {
    return "https://openweathermap.org/img/w/" + iconCode + ".png";
  }

  public String getCurrentDayTime() {
    var formatter = new SimpleDateFormat("HH:mm");
    var time = new Date(System.currentTimeMillis());
    return LocalDate.now().getDayOfWeek().name() + ", " + formatter.format(time);
  }
}
