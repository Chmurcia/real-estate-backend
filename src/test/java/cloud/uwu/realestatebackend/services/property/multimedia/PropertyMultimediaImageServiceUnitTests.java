package cloud.uwu.realestatebackend.services.property.multimedia;

import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaImage.PropertyMultimediaImageDTO;
import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaImage.PropertyMultimediaImageResponseDTO;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimedia;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimediaImage;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.multimedia.PropertyMultimediaImageMapper;
import cloud.uwu.realestatebackend.repositories.property.multimedia.PropertyMultimediaImageRepository;
import cloud.uwu.realestatebackend.repositories.property.multimedia.PropertyMultimediaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyMultimediaImageServiceUnitTests {

    @Mock
    private PropertyMultimediaImageRepository propertyMultimediaImageRepository;

    @Mock
    private PropertyMultimediaRepository propertyMultimediaRepository;

    @Mock
    private PropertyMultimediaImageMapper propertyMultimediaImageMapper;

    @InjectMocks
    private PropertyMultimediaImageService propertyMultimediaImageService;

    @Test
    void createPropertyMultimediaImage() {
        PropertyMultimedia propertyMultimedia = PropertyMultimedia.builder()
                .build();

        PropertyMultimediaImageDTO propertyMultimediaImageDTO = PropertyMultimediaImageDTO
                .builder()
                .propertyMultimediaId(UUID.randomUUID())
                .imageTitle("Title")
                .imageURL("url")
                .build();

        PropertyMultimediaImage propertyMultimediaImage = PropertyMultimediaImage.builder()
                .imageTitle(propertyMultimediaImageDTO.getImageTitle())
                .imageURL(propertyMultimediaImageDTO.getImageURL())
                .propertyMultimedia(propertyMultimedia)
                .build();

        PropertyMultimediaImageResponseDTO propertyMultimediaImageResponseDTO =
                PropertyMultimediaImageResponseDTO.builder()
                        .imageTitle(propertyMultimediaImage.getImageTitle())
                        .imageURL(propertyMultimediaImage.getImageURL())
                        .build();

        when(propertyMultimediaRepository.findById(propertyMultimediaImageDTO
                .getPropertyMultimediaId())).thenReturn(Optional.of(propertyMultimedia));

        when(propertyMultimediaImageRepository.save(any(PropertyMultimediaImage.class)))
                .thenReturn(propertyMultimediaImage);

        when(propertyMultimediaImageMapper
                .propertyMultimediaImageToPropertyMultimediaImageResponseDTO(any(PropertyMultimediaImage
                        .class))).thenReturn(propertyMultimediaImageResponseDTO);

        PropertyMultimediaImageResponseDTO createdPropertyMultimediaImage =
                propertyMultimediaImageService
                        .createPropertyMultimediaImage(propertyMultimediaImageDTO);

        verify(propertyMultimediaImageRepository).save(any(PropertyMultimediaImage.class));
        verify(propertyMultimediaRepository).save(any(PropertyMultimedia.class));

        assertEquals(createdPropertyMultimediaImage.getImageTitle(),
                propertyMultimediaImageDTO.getImageTitle());
        assertEquals(createdPropertyMultimediaImage.getImageURL(),
                propertyMultimediaImageDTO.getImageURL());
    }

    @Test
    void createPropertyMultimediaImage_ShouldThrowNotFoundException() {
        PropertyMultimediaImageDTO propertyMultimediaImageDTO = PropertyMultimediaImageDTO.builder()
                .propertyMultimediaId(UUID.randomUUID())
                .build();

        assertThrows(NotFoundException.class, () ->
                propertyMultimediaImageService.createPropertyMultimediaImage(propertyMultimediaImageDTO));
    }

    @Test
    void deletePropertyMultimediaImage() {
        UUID id = UUID.randomUUID();

        when(propertyMultimediaImageRepository.findById(id))
                .thenReturn(Optional.of(PropertyMultimediaImage.builder().build()));

        propertyMultimediaImageService.deletePropertyMultimediaImage(id);

        verify(propertyMultimediaImageRepository).deleteById(id);
    }

    @Test
    void deletePropertyMultimediaImage_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyMultimediaImageService.deletePropertyMultimediaImage(id));
    }
}