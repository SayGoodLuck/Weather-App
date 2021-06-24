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


  //  public void getCurrentWeather(String cityName) {
  //
  //    getCityInfo(cityName);
  //
  //    if (!cityName.equals("")) {
  //      String output =
  //          getUrlContent(
  //              "https://api.openweathermap.org/data/2.5/onecall?lat="
  //                  + city.getLat()
  //                  + "&lon="
  //                  + city.getLon()
  //                  + "&exclude=minutely,hourly,alerts&appid="
  //                  + API_KEY
  //                  + "&units="
  //                  + METRIC);
  //
  //      if (!output.isEmpty()) {
  //
  //        JSONObject json = new JSONObject(output);
  //        JSONArray jsonArray = json.getJSONArray(DAILY);
  //
  //        getCurrentDateTime();
  //
  //        setTempCurrent(String.valueOf(json.getJSONObject(CURRENT).getInt("temp")));
  //        setTempFeelsLike(json.getJSONObject(CURRENT).getFloat("feels_like") + UNITS);
  //
  //        setTempMax(jsonArray.getJSONObject(0).getJSONObject("temp").getFloat("max") + UNITS);
  //        setTempMin(jsonArray.getJSONObject(0).getJSONObject("temp").getFloat("min") + UNITS);
  //
  //        setIcon(new Image(getImageUrl(json)));
  //
  //        setWindSpeed(json.getJSONObject(CURRENT).getFloat("wind_speed") + SPEED);
  //        setSunrise(timeStampConvert(json.getJSONObject(CURRENT).getLong("sunrise")));
  //        setSunset(timeStampConvert(json.getJSONObject(CURRENT).getLong("sunset")));
  //
  //        float visionRange = json.getJSONObject(CURRENT).getFloat("visibility") / 1000;
  //        setVisibility(visionRange + " km");
  //
  //        setHumidity(json.getJSONObject(CURRENT).getInt("humidity") + "%");
  //        setUvIndex(String.valueOf(json.getJSONObject(CURRENT).getFloat("uvi")));
  //      }
  //    }
  //  }

  public Map<Integer, DailyForecast> getSevenDaysForecast(String cityName) {

    //todo add metrics

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

        for (int index = 0; index < sevenDays.length(); index++) {
          long timeStamp = sevenDays.getJSONObject(index).getLong("dt");
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
          String iconCode =
              String.valueOf(
                  sevenDays
                      .getJSONObject(index)
                      .getJSONArray("weather")
                      .getJSONObject(0)
                      .getString("icon"));

          String uvIndex = String.valueOf(sevenDays.getJSONObject(index).getFloat("uvi"));
          String description =
              sevenDays
                  .getJSONObject(index)
                  .getJSONArray("weather")
                  .getJSONObject(0)
                  .getString("description");

          Image icon = new Image(getImageUrl(iconCode));
          DailyForecast dailyForecast =
              new DailyForecast(
                  extractDayOfWeek(timeStamp),
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

  public String extractDayOfWeek(long timeStamp) {

    Date date = new java.util.Date(timeStamp * 1000);

    SimpleDateFormat simpleDateformat = new SimpleDateFormat("E", Locale.ENGLISH);

    return simpleDateformat.format(date);
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

  private String getImageUrl(String iconCode) {
    //    JSONArray result = json.getJSONObject(CURRENT).getJSONArray("weather");
    //    String iconCode = result.getJSONObject(0).getString("icon");
    return "https://openweathermap.org/img/w/" + iconCode + ".png";
  }

  public String getCurrentDateTime() {
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    Date time = new Date(System.currentTimeMillis());
    return LocalDate.now().getDayOfWeek().name() + ", " + formatter.format(time);
  }
}
