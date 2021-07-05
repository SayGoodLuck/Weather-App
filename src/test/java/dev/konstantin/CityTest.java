package dev.konstantin;

import dev.konstantin.models.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CityTest {
  private City city = new City("kyiv", 30.5167, 50.4333, "UA");

  @Test
  void cityFormatTest() {
    String cityName = city.cityFormat();
    Assertions.assertEquals("KYIV", cityName);
  }

}
