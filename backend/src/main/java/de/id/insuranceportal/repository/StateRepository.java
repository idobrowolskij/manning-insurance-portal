package de.id.insuranceportal.repository;

import de.id.insuranceportal.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StateRepository extends JpaRepository<State, UUID> {
}
