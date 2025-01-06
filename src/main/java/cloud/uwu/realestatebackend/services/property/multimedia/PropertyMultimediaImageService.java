package cloud.uwu.realestatebackend.services.property.multimedia;

import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaImage.PropertyMultimediaImageDTO;
import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaImage.PropertyMultimediaImageResponseDTO;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimedia;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimediaImage;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.multimedia.PropertyMultimediaImageMapper;
import cloud.uwu.realestatebackend.repositories.property.multimedia.PropertyMultimediaImageRepository;
import cloud.uwu.realestatebackend.repositories.property.multimedia.PropertyMultimediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyMultimediaImageService {
    private final PropertyMultimediaImageRepository propertyMultimediaImageRepository;
    private final PropertyMultimediaRepository propertyMultimediaRepository;
    private final PropertyMultimediaImageMapper propertyMultimediaImageMapper;

    public PropertyMultimediaImageResponseDTO createPropertyMultimediaImage(PropertyMultimediaImageDTO propertyMultimediaImageDTO) {
        PropertyMultimedia propertyMultimedia =
                getPropertyMultimedia(propertyMultimediaImageDTO.getPropertyMultimediaId());

        PropertyMultimediaImage propertyMultimediaImage = PropertyMultimediaImage.builder()
                .imageTitle(propertyMultimediaImageDTO.getImageTitle())
                .imageURL(propertyMultimediaImageDTO.getImageURL())
                .propertyMultimedia(propertyMultimedia)
                .build();

        PropertyMultimediaImage savedPropertyMultimediaImage =
                propertyMultimediaImageRepository.save(propertyMultimediaImage);

        propertyMultimedia.getImages().add(savedPropertyMultimediaImage);

        propertyMultimediaRepository.save(propertyMultimedia);

        return propertyMultimediaImageMapper
                .propertyMultimediaImageToPropertyMultimediaImageResponseDTO(savedPropertyMultimediaImage);
    }

    public void deletePropertyMultimediaImage(UUID id) {
        getPropertyMultimediaImage(id);

        propertyMultimediaImageRepository.deleteById(id);
    }

    //

    private void getPropertyMultimediaImage(UUID id) {
        propertyMultimediaImageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyMultimediaImage not found"));
    }

    private PropertyMultimedia getPropertyMultimedia(UUID id) {
        return propertyMultimediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyMultimedia not found"));
    }
}
