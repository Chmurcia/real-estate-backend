package cloud.uwu.realestatebackend.repositories.property.multimedia;

import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimediaImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyMultimediaImageRepository extends JpaRepository<PropertyMultimediaImage, UUID> {
}
