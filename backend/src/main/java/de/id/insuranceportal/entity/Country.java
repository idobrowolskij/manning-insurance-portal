package de.id.insuranceportal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country extends UuidEntity {

    @Id
    @Column(name = "country_id", nullable = false, updatable = false, insertable = false)
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "population")
    private Integer population;

}
