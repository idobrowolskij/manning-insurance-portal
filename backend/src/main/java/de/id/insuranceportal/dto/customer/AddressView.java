package de.id.insuranceportal.dto.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AddressView {

    private UUID id;
    private String address;
    private String postalCode;
    private CountryView country;
    private StateView state;
    private CityView city;

}
