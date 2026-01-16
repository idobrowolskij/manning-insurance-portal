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
@Table(name = "customers")
public class Customer extends UuidEntity {

    @Id
    @Column(name = "customer_id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "surname", nullable = false, length = 64)
    private String surname;

    @Column(name = "email", nullable = false, length = 320, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @Embedded
    private TimeStamps auditTimeStamps = new TimeStamps();

}
