package cloud.uwu.realestatebackend.repositories.property.neighbourhood;

import cloud.uwu.realestatebackend.entities.property.neighbourhood.PropertyNeighbourhood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyNeighbourhoodRepository extends JpaRepository<PropertyNeighbourhood, UUID> {
}
