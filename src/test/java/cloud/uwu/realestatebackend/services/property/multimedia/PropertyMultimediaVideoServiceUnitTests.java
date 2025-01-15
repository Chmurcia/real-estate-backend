package cloud.uwu.realestatebackend.services.property.multimedia;

import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaVideo.PropertyMultimediaVideoDTO;
import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaVideo.PropertyMultimediaVideoResponseDTO;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimedia;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimediaVideo;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.multimedia.PropertyMultimediaVideoMapper;
import cloud.uwu.realestatebackend.repositories.property.multimedia.PropertyMultimediaRepository;
import cloud.uwu.realestatebackend.repositories.property.multimedia.PropertyMultimediaVideoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyMultimediaVideoServiceUnitTests {

    @Mock
    private PropertyMultimediaVideoRepository propertyMultimediaVideoRepository;

    @Mock
    private PropertyMultimediaRepository propertyMultimediaRepository;

    @Mock
    private PropertyMultimediaVideoMapper propertyMultimediaVideoMapper;

    @InjectMocks
    private PropertyMultimediaVideoService propertyMultimediaVideoService;

    @Test
    void createPropertyMultimediaVideo() {
        PropertyMultimedia propertyMultimedia = PropertyMultimedia.builder()
                .id(UUID.randomUUID())
                .build();

        PropertyMultimediaVideoDTO propertyMultimediaVideoDTO = PropertyMultimediaVideoDTO
                .builder()
                .videoTitle("Title")
                .videoURL("url")
                .build();

        PropertyMultimediaVideo propertyMultimediaVideo = PropertyMultimediaVideo.builder()
                .videoTitle(propertyMultimediaVideoDTO.getVideoTitle())
                .videoURL(propertyMultimediaVideoDTO.getVideoURL())
                .build();

        PropertyMultimediaVideoResponseDTO propertyMultimediaVideoResponseDTO =
                PropertyMultimediaVideoResponseDTO.builder()
                .videoTitle("Title")
                .videoURL("url")
                .build();

        when(propertyMultimediaRepository.findById(propertyMultimediaVideoDTO
                .getPropertyMultimediaId())).thenReturn(Optional.of(propertyMultimedia));

        when(propertyMultimediaVideoRepository.save(any(PropertyMultimediaVideo.class)))
                .thenReturn(propertyMultimediaVideo);

        when(propertyMultimediaVideoMapper
                .propertyMultimediaVideoToPropertyMultimediaVideoResponseDTO(any(PropertyMultimediaVideo
                        .class))).thenReturn(propertyMultimediaVideoResponseDTO);

        PropertyMultimediaVideoResponseDTO createdPropertyMultimediaVideo =
                propertyMultimediaVideoService
                        .createPropertyMultimediaVideo(propertyMultimedia.getId(),
                                propertyMultimediaVideoDTO);

        verify(propertyMultimediaRepository).save(any(PropertyMultimedia.class));
        verify(propertyMultimediaVideoRepository).save(any(PropertyMultimediaVideo.class));

        assertEquals(createdPropertyMultimediaVideo.getVideoTitle(),
                propertyMultimediaVideoDTO.getVideoTitle());
        assertEquals(createdPropertyMultimediaVideo.getVideoURL(),
                propertyMultimediaVideoDTO.getVideoURL());
    }

    @Test
    void createPropertyMultimediaVideo_ShouldThrowNotFoundException() {
        PropertyMultimediaVideoDTO propertyMultimediaVideoDTO = PropertyMultimediaVideoDTO
                .builder()
                .propertyMultimediaId(UUID.randomUUID())
                .build();

        assertThrows(NotFoundException.class, () ->
                propertyMultimediaVideoService
                        .createPropertyMultimediaVideo(UUID.randomUUID(),
                                propertyMultimediaVideoDTO));
    }

    @Test
    void deletePropertyMultimediaVideo() {
        UUID id = UUID.randomUUID();

        when(propertyMultimediaVideoRepository.findById(id))
                .thenReturn(Optional.of(PropertyMultimediaVideo.builder().build()));

        propertyMultimediaVideoService.deletePropertyMultimediaVideo(id);

        verify(propertyMultimediaVideoRepository).deleteById(id);
    }

    @Test
    void deletePropertyMultimediaVideo_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyMultimediaVideoService.deletePropertyMultimediaVideo(id));
    }
}