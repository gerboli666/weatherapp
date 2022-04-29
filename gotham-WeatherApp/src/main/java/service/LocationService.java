package service;

import lombok.AllArgsConstructor;
import model.Location;
import model.Weather;

import repository.LocationRepository;

import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;


    public Location addNewLocation(Double latitude, Double longtitude, String region, String countryName) {
        Location location = locationRepository.addLocation(Location.builder()
                .latitude(latitude)
                .longtitude(longtitude)
                .region(region)
                .countryName(countryName)
                .build());
        return location;
    }

    public List<Weather> getWeather(String name) {
        return locationRepository.displayWeather(name);
    }

    public Integer addWeather(Weather weather) {
      return locationRepository.addWather(weather);
    }

    @Override
    public String toString() {
        return "LocationService{" +
                "locationRepository=" + locationRepository +
                '}';
    }
}
