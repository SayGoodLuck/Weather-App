package dev.konstantin;

import dev.konstantin.exceptions.WeatherManagerException;
import dev.konstantin.models.City;
import dev.konstantin.models.DailyForecast;
import dev.konstantin.service.WeatherManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WeatherManagerTest {

  WeatherManager weatherManager = new WeatherManager();

  private static Stream<Arguments> provideArgsToGetSevenDaysForecastTest() {
    return Stream.of(
        Arguments.of("kyiv"),
        Arguments.of("london"),
        Arguments.of("warsaw"),
        Arguments.of("moscow"),
        Arguments.of("paris"),
        Arguments.of("new york"),
        Arguments.of("madrid"),
        Arguments.of("berlin"));
  }

  @Test
  @MethodSource("provideArgsToGetSevenDaysForecastTest")
  void getSevenDaysForecastTest(String cityName) throws WeatherManagerException {
    List<DailyForecast> sevenDaysForecast = weatherManager.getSevenDaysForecast(cityName);


  }

  private static Stream<Arguments> provideArgsToGetCityInfoTest() {
    return Stream.of(
        Arguments.of("kyiv"), Arguments.of("london", "London", -0.1257, 51.5085, "GB"));
  }

  @Test
  void getCityInfoTest() throws WeatherManagerException {
    City expectedCity = new City("Kyiv", 30.5167, 50.4333, "UA");
    City newCity = weatherManager.getCityInfo("kyiv");

    Assertions.assertEquals(expectedCity.getName(), newCity.getName());
    Assertions.assertEquals(expectedCity.getLat(), newCity.getLat());
    Assertions.assertEquals(expectedCity.getLon(), newCity.getLon());
    Assertions.assertEquals(expectedCity.getCountryCode(), newCity.getCountryCode());
  }

  @Test
  void getSevenDaysForecastArgIsEmptyTest() {

    String city = "";

    assertThatThrownBy(() -> weatherManager.getSevenDaysForecast(city))
        .isInstanceOf(WeatherManagerException.class)
        .hasMessageContaining("String is empty");
  }

  @Test
  void getSevenDaysForecastNonExistentCityTest() {

    String city = "bababa";

    assertThatThrownBy(
            () -> weatherManager.getSevenDaysForecast(city))
            .isInstanceOf(org.json.JSONException.class)
            .hasMessageContaining("JSONArray[0] not found.");
  }

  @Test
  void getSevenDaysForecastArgIsNumericTest() {

    String city = "wars1w";

    assertThatThrownBy(
            () -> weatherManager.getSevenDaysForecast(city))
            .isInstanceOf(org.json.JSONException.class)
            .hasMessageContaining("JSONArray[0] not found.");
  }

  @Test
  void getSevenDaysForecastArgIsCyrillicTest() {

    String city = "варшава";

    assertThatThrownBy(
            () -> weatherManager.getSevenDaysForecast(city))
            .isInstanceOf(org.json.JSONException.class)
            .hasMessageContaining("JSONArray[0] not found.");
  }

  @Test
  void timeStampConvertTest() {

    long unixStamp = 1625519813;

    String expectedTime = "23:16";

    Assertions.assertEquals(expectedTime, weatherManager.timeStampConvert(unixStamp));
  }

  @Test
  void getImageUrlTest() {

  }

  @Test
  void getCurrentDayTimeTest() {

  }
}
