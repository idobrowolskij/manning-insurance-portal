package de.id.insuranceportal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "policies")
public class Policy extends UuidEntity {

    @Id
    @Column(name = "policy_id", nullable = false, updatable = false, insertable = false)
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false, length = 16)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false, precision = 8, scale = 2)
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "policies_coverages",
            joinColumns = @JoinColumn(name = "policy_id"),
            inverseJoinColumns = @JoinColumn(name = "coverage_id")
    )
    private Set<Coverage> coverages = new HashSet<>();

    @Embedded
    private TimeStamps timeStamps = new TimeStamps();

}
