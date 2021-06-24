package sample.models;

public class City {

  private String name;

  private String lon;

  private String lat;

  public City(String name, String lon, String lat) {
    this.name = name;
    this.lon = lon;
    this.lat = lat;
  }

  public String getName() {
    return name;
  }

  public String getLon() {
    return lon;
  }

  public String getLat() {
    return lat;
  }
}
