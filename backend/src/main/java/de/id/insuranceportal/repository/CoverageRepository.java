package de.id.insuranceportal.repository;

import de.id.insuranceportal.entity.Coverage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoverageRepository extends JpaRepository<Coverage, UUID> {
}
