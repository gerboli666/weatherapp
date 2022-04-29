import bean.WeatherResponse;
import com.sun.istack.NotNull;
import model.Location;
import model.Weather;
import model.Wind;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repository.LocationRepository;
import service.LocationService;
import service.WeatherService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;

import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        /// session
        Session session = getCurrentSessionFromConfig();
        Session sessionCo = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();
        EntityManager entityManager = Session.sessiom();
        // insert locations 10
        LocationService locationService = new LocationService(new LocationRepository(sessionFactory));
        Location res = locationService.addNewLocation(41.1, 9.1, "USA", "DC");
        // array locations find all
        //for 10 locations :  select one by one
        //find by name
        // exec getLiveWeatherValues
        String lat = "41.327";
        String lon = "19.818";
        //call external service in order to get weather datas
        WeatherResponse entity = new WeatherService().getLiveWeatherValues(res.getLatitude(), res.getLongtitude());
        System.out.println(entity);

        Wind wind = new Wind();
        wind.setWindSpeed(entity.getWind().getSpeed());
        wind.setDirection(String.valueOf(entity.getWind().getDeg()));

        Weather weather = new Weather();
        weather.setTemperature(entity.getMain().getTemp());

        weather.setWind(wind);
        weather.setLocation(res);


        locationService = new LocationService(new LocationRepository(getCurrentSessionFromConfig()));
        Integer id= locationService.addWeather(weather);


        locationService = new LocationService(new LocationRepository(getCurrentSessionFromConfig()));
     List<Weather> list = locationService.getWeather("Tirana");
     list.stream().forEach(System.out::println);

        LocationRepository locationRepository = new LocationRepository();

    }

    public static Session getCurrentSessionFromConfig() {
        // SessionFactory in Hibernate 5 example
        Configuration config = new Configuration();
        config.configure();

        // local SessionFactory bean created
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
    public void persistWeather(Weather weatherParametersEntity, @NotNull SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(weatherParametersEntity);
        transaction.commit();
        session.close();
    }

}

