package cloud.uwu.realestatebackend.services.property.multimedia;

import cloud.uwu.realestatebackend.dtos.property.multimedia.PropertyMultimediaResponseDTO;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimedia;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.multimedia.PropertyMultimediaMapper;
import cloud.uwu.realestatebackend.repositories.property.multimedia.PropertyMultimediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyMultimediaService {
    private final PropertyMultimediaRepository propertyMultimediaRepository;
    private final PropertyMultimediaMapper propertyMultimediaMapper;

    public PropertyMultimediaResponseDTO getPropertyMultimediaById(UUID id) {
        return propertyMultimediaMapper
                .propertyMultimediaToPropertyMultimediaResponseDTO(getPropertyMultimedia(id));
    }

    //

    private PropertyMultimedia getPropertyMultimedia(UUID id) {
        return propertyMultimediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyMultimedia not found"));
    }
}
