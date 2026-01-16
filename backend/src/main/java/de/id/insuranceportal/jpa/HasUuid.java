package de.id.insuranceportal.jpa;

import java.util.UUID;

public interface HasUuid {
    UUID getId();
    void setId(UUID id);
}
