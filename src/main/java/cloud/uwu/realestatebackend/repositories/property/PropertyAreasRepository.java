package cloud.uwu.realestatebackend.repositories.property;

import cloud.uwu.realestatebackend.entities.property.PropertyAreas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyAreasRepository extends JpaRepository<PropertyAreas, UUID> {
}
