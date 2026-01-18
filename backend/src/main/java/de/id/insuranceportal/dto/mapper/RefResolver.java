package de.id.insuranceportal.dto.mapper;

import de.id.insuranceportal.entity.City;
import de.id.insuranceportal.entity.Country;
import de.id.insuranceportal.entity.State;
import de.id.insuranceportal.repository.CityRepository;
import de.id.insuranceportal.repository.CountryRepository;
import de.id.insuranceportal.repository.StateRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RefResolver {
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    public RefResolver(CountryRepository countryRepository, CityRepository cityRepository, StateRepository stateRepository) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    public Country country(UUID id) { return countryRepository.getReferenceById(id); }
    public City city(UUID id) { return cityRepository.getReferenceById(id); }
    public State state(UUID id) { return stateRepository.getReferenceById(id); }

}
