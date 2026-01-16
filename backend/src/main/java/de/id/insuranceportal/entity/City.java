package de.id.insuranceportal.entity;

import de.id.insuranceportal.jpa.listener.UuidListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(UuidListener.class)
@Table(name = "cities")
public class City extends UuidEntity {

    @Id
    @Column(name = "city_id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "state_id", insertable = false, updatable = false)
    private State state;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "population")
    private Integer population;

}
