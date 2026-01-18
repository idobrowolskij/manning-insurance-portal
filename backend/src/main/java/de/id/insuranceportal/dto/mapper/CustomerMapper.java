package de.id.insuranceportal.dto.mapper;

import de.id.insuranceportal.dto.customer.CustomerCreation;
import de.id.insuranceportal.dto.customer.CustomerUpdate;
import de.id.insuranceportal.dto.customer.CustomerView;
import de.id.insuranceportal.entity.Address;
import de.id.insuranceportal.entity.Customer;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // CREATE: DTO -> CUSTOMER
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", expression = "java(toAddress(dto, resolver))")
    Customer fromCreation(CustomerCreation dto, @Context RefResolver resolver);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "address", ignore = true) // aftermapping
    void applyUpdate(CustomerUpdate dto, @MappingTarget Customer existing, @Context RefResolver resolver);

    CustomerView toView(Customer customer);

    @AfterMapping
    default void patchAddress(CustomerUpdate dto, @MappingTarget Customer existing, @Context RefResolver resolver) {
        Address a = existing.getAddress();
        if (a == null) {
            a = new Address();
            existing.setAddress(a);
        }

        a.setAddressLine(dto.getAddress());
        a.setPostalCode(dto.getPostalCode());
        a.setCountry(resolver.country(dto.getIdCountry()));
        a.setCity(resolver.city(dto.getIdCity()));
        a.setState(dto.getIdState() != null ? resolver.state(dto.getIdState()) : null);
    }

    default Address toAddress(CustomerCreation dto, @Context RefResolver resolver) {
        Address a = new Address();
        a.setAddressLine(dto.getAddress());
        a.setPostalCode(dto.getPostalCode());
        a.setCountry(resolver.country(dto.getIdCountry()));
        a.setCity(resolver.city(dto.getIdCity()));
        a.setState(dto.getIdState() != null ? resolver.state(dto.getIdState()) : null);
        return a;
    }

}
