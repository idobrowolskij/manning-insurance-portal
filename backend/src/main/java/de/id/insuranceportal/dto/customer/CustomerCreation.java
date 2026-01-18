package de.id.insuranceportal.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CustomerCreation {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    private String postalCode;

    @NotBlank
    private UUID idCountry;

    @NotBlank
    private UUID idState;

    @NotBlank
    private UUID idCity;

}
