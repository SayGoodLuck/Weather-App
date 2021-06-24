package sample.models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalTime;

public class WeatherManager {

  private static final String API_KEY = "900ea254740cee343805d46750147cdc";

  private City city;

  private String description;

  private float humidity;

  private float visibility;

  private LocalTime sunrise;

  private LocalTime sunset;

  private int windSpeed;

  private float tempMax;

  private float tempMin;

  private float tempCurrent;

  private float tempFeelsLike;

  public String getCurrentWeather(City city) {
    StringBuilder weather = new StringBuilder("1");

    if (!city.equals("")) {
      var output =
          getUrlContent(
              "https://api.openweathermap.org/data/2.5/weather?q="
                  + city
                  + "&appid="
                  + API_KEY
                  + "&units="
                  + METRIC);


    }
    return weather.toString();
  }

  public StringBuilder[] getSevenDaysForecast(long lat, long lon) {
    StringBuilder[] sevenDaysForecast = new StringBuilder[7];

    return sevenDaysForecast;
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
}
