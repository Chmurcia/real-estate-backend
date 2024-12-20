package cloud.uwu.realestatebackend.repositories.property.statistics;

import cloud.uwu.realestatebackend.entities.property.statistics.PropertyAmenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyAmenityRepository extends JpaRepository<PropertyAmenity, UUID> {
}
