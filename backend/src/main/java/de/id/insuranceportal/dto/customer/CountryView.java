package de.id.insuranceportal.dto.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CountryView {

    private UUID id;
    private String name;

}
