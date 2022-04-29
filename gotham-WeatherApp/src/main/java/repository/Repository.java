package repository;


import model.Location;
import model.Weather;

import java.util.List;

public interface Repository {


    Location addLocation(Location location);

    List<Weather> displayWeather(String name);








}
