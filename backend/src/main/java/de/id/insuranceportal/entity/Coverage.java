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
@Table(name = "coverages")
public class Coverage extends UuidEntity {

    @Id
    @Column(name = "coverage_id", nullable = false, insertable = false)
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false, length = 16)
    private String name;

    @Column(name = "description")
    private String description;

}
