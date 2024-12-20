package cloud.uwu.realestatebackend.repositories.property.multimedia;

import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimediaVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyMultimediaVideoRepository extends JpaRepository<PropertyMultimediaVideo, UUID> {
}
