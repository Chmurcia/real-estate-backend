package cloud.uwu.realestatebackend.services.property.multimedia;

import cloud.uwu.realestatebackend.dtos.property.multimedia.PropertyMultimediaResponseDTO;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimedia;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.multimedia.PropertyMultimediaMapper;
import cloud.uwu.realestatebackend.repositories.property.multimedia.PropertyMultimediaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyMultimediaServiceUnitTests {
    @Mock
    private PropertyMultimediaRepository propertyMultimediaRepository;

    @Mock
    private PropertyMultimediaMapper propertyMultimediaMapper;

    @InjectMocks
    private PropertyMultimediaService propertyMultimediaService;

    @Test
    void getPropertyMultimediaById() {
        UUID id = UUID.randomUUID();
        PropertyMultimedia propertyMultimedia = PropertyMultimedia.builder().build();

        when(propertyMultimediaRepository.findById(id))
                .thenReturn(Optional.of(propertyMultimedia));

        when(propertyMultimediaMapper
                .propertyMultimediaToPropertyMultimediaResponseDTO(propertyMultimedia))
                .thenReturn(PropertyMultimediaResponseDTO.builder().build());

        PropertyMultimediaResponseDTO foundPropertyMultimedia = propertyMultimediaService.
                getPropertyMultimediaById(id);

        verify(propertyMultimediaRepository).findById(id);

        assertNotNull(foundPropertyMultimedia);
    }

    @Test
    void getPropertyMultimediaById_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyMultimediaService.getPropertyMultimediaById(id));
    }
}