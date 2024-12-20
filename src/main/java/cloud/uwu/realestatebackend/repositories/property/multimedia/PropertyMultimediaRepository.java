package cloud.uwu.realestatebackend.repositories.property.multimedia;

import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyMultimediaRepository extends JpaRepository<PropertyMultimedia, UUID> {
}
