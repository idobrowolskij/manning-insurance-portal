package de.id.insuranceportal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Embeddable
@EntityListeners(AuditingEntityListener.class)
public class TimeStamps {

    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    private Instant created;

    @LastModifiedDate
    @Column(name = "updated", nullable = false)
    private Instant updated;

}
