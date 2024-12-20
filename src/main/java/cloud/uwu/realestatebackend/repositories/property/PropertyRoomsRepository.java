package cloud.uwu.realestatebackend.repositories.property;

import cloud.uwu.realestatebackend.entities.property.PropertyRooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyRoomsRepository extends JpaRepository<PropertyRooms, UUID> {
}
