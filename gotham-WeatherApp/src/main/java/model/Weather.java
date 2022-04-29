package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "weather")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "pressure")
    private Double pressure;

    @Column(name = "humidity")
    private String humidity;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "weather",optional = false)
    private Wind wind;

    public void setWind(Wind wind) {
        if (wind == null) {
            if (this.wind != null) {
                this.wind.setWeather(null);
            }
        }
        else {
            wind.setWeather(this);
        }
        this.wind = wind;
    }

}