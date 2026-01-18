package de.id.insuranceportal.dto.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CustomerView {

    private UUID id;
    private String name;
    private String surname;
    private String email;
    private AddressView address;

}
