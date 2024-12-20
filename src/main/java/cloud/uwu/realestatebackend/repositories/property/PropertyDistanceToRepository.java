package cloud.uwu.realestatebackend.repositories.property;

import cloud.uwu.realestatebackend.entities.property.PropertyDistanceTo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyDistanceToRepository extends JpaRepository<PropertyDistanceTo, UUID> {
}
