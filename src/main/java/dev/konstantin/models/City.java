package dev.konstantin.models;

import java.util.Objects;

public class City {

  private String name;

  private double lon;

  private double lat;

  private String countryCode;

  public City(String name, double lon, double lat, String countryCode) {
    this.name = name;
    this.lon = lon;
    this.lat = lat;
    this.countryCode = countryCode;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public String getName() {
    return name;
  }

  public double getLon() {
    return lon;
  }

  public double getLat() {
    return lat;
  }

  public String cityFormat() {
    return name.toUpperCase();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    City city = (City) o;
    return Double.compare(city.lon, lon) == 0
        && Double.compare(city.lat, lat) == 0
        && Objects.equals(name, city.name)
        && Objects.equals(countryCode, city.countryCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, lon, lat, countryCode);
  }

  @Override
  public String toString() {
    return "City{"
        + "name='"
        + name
        + '\''
        + ", lon="
        + lon
        + ", lat="
        + lat
        + ", countryCode='"
        + countryCode
        + '\''
        + '}';
  }
}
