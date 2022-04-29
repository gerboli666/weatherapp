package model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Table(name="location")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="longtitude")
    private Double longtitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name="region")
    private String region;

    @Column(name = "country")
    private String countryName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_id")
    private Set<Weather> weather= new HashSet<>();

}
