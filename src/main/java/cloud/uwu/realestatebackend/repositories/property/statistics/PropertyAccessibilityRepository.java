package cloud.uwu.realestatebackend.repositories.property.statistics;

import cloud.uwu.realestatebackend.entities.property.statistics.PropertyAccessibility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyAccessibilityRepository extends JpaRepository<PropertyAccessibility, UUID> {
}
