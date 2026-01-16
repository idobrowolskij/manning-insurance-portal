package de.id.insuranceportal.jpa.listener;

import de.id.insuranceportal.jpa.HasUuid;
import jakarta.persistence.PrePersist;

import java.util.UUID;

public class UuidListener {

    @PrePersist
    public void setUuid(Object entity) {
        if (entity instanceof HasUuid hasUuid) {
            if (hasUuid.getId() == null) {
                hasUuid.setId(UUID.randomUUID());
            }
        }
    }
}