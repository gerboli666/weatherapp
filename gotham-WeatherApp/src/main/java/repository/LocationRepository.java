package repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.Location;
import model.Weather;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class LocationRepository implements Repository {
    private SessionFactory sessionFactory;

    @Override
    public Location addLocation(Location location) {
        Transaction transaction = null;
        try {
            transaction = sessionFactory.beginTransaction();
            session.save(location);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return location;
    }

    @Override
    public List<Weather> displayWeather(String name) {
        List<Weather> res = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            res = session.createQuery(" FROM Weather").getResultList();
            transaction.commit();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return res;
    }
    public Integer addWather(Weather weather) {
        Transaction transaction = null;
        Integer id = null;
        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(weather);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return id;
    }
//    public void deletWather(Integer id){
//
//    }

}