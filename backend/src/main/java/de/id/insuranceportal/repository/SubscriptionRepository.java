package de.id.insuranceportal.repository;

import de.id.insuranceportal.entity.Subscription;
import de.id.insuranceportal.entity.SubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {
}
