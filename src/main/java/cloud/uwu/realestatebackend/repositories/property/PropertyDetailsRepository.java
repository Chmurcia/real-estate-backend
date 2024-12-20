package cloud.uwu.realestatebackend.repositories.property;

import cloud.uwu.realestatebackend.entities.property.PropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyDetailsRepository extends JpaRepository<PropertyDetails, UUID> {
}
