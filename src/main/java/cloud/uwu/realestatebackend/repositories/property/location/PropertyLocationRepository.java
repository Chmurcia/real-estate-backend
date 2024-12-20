package cloud.uwu.realestatebackend.repositories.property.location;

import cloud.uwu.realestatebackend.entities.property.location.PropertyLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyLocationRepository extends JpaRepository<PropertyLocation, UUID> {
}
