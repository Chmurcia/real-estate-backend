package cloud.uwu.realestatebackend.services.property.multimedia;

import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaVideo.PropertyMultimediaVideoDTO;
import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaVideo.PropertyMultimediaVideoResponseDTO;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimedia;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimediaVideo;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.multimedia.PropertyMultimediaVideoMapper;
import cloud.uwu.realestatebackend.repositories.property.multimedia.PropertyMultimediaRepository;
import cloud.uwu.realestatebackend.repositories.property.multimedia.PropertyMultimediaVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyMultimediaVideoService {
    private final PropertyMultimediaVideoRepository propertyMultimediaVideoRepository;
    private final PropertyMultimediaRepository propertyMultimediaRepository;
    private final PropertyMultimediaVideoMapper propertyMultimediaVideoMapper;

    public PropertyMultimediaVideoResponseDTO createPropertyMultimediaVideo(
            UUID propertyMultimediaId,
            PropertyMultimediaVideoDTO propertyMultimediaVideoDTO) {

        PropertyMultimedia propertyMultimedia =
                getPropertyMultimedia(propertyMultimediaId);

        PropertyMultimediaVideo propertyMultimediaVideo = PropertyMultimediaVideo.builder()
                .videoTitle(propertyMultimediaVideoDTO.getVideoTitle())
                .videoURL(propertyMultimediaVideoDTO.getVideoURL())
                .propertyMultimedia(propertyMultimedia)
                .build();

        PropertyMultimediaVideo savedPropertyMultimediaVideo =
                propertyMultimediaVideoRepository.saveAndFlush(propertyMultimediaVideo);

        propertyMultimedia.getVideos().add(savedPropertyMultimediaVideo);

        propertyMultimediaRepository.save(propertyMultimedia);

        return propertyMultimediaVideoMapper
                .propertyMultimediaVideoToPropertyMultimediaVideoResponseDTO(savedPropertyMultimediaVideo);
    }

    public void deletePropertyMultimediaVideo(UUID id) {
        getPropertyMultimediaVideo(id);

        propertyMultimediaVideoRepository.deleteById(id);
    }

    //

    private void getPropertyMultimediaVideo(UUID id) {
        propertyMultimediaVideoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyMultimediaVideo not found"));
    }

    private PropertyMultimedia getPropertyMultimedia(UUID id) {
        return propertyMultimediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyMultimedia not found"));
    }
}
