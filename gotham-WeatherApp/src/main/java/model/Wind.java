package model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "wind")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Wind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "speed")
    private Double windSpeed;

    @Column(name = "direction")
    private String direction;

    @OneToOne
    @JoinColumn(name = "weather_id")
    private Weather weather;


}
