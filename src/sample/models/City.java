package sample.models;

public class City {

  private String name;

  private float lon;

  private float lat;

  private String countryCode;

  public City(String name, float lon, float lat, String countryCode) {
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

  public float getLon() {
    return lon;
  }

  public float getLat() {
    return lat;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setLon(float lon) {
    this.lon = lon;
  }

  public void setLat(float lat) {
    this.lat = lat;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }
}
