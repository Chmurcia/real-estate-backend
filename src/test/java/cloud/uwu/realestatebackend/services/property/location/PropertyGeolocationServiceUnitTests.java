package cloud.uwu.realestatebackend.services.property.location;

import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationDTO;
import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationPatchDTO;
import cloud.uwu.realestatebackend.entities.property.location.PropertyGeolocation;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.property.location.PropertyGeolocationMapper;
import cloud.uwu.realestatebackend.repositories.property.location.PropertyGeolocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyGeolocationServiceUnitTests {
    @Mock
    private PropertyGeolocationRepository propertyGeolocationRepository;

    @Mock
    private PropertyGeolocationMapper propertyGeolocationMapper;

    @InjectMocks
    private PropertyGeolocationService propertyGeolocationService;

    @Test
    void createPropertyGeolocation() {
        PropertyGeolocationDTO propertyGeolocationDTO = PropertyGeolocationDTO.builder()
                .latitude(54.6)
                .longitude(65.1)
                .build();

        PropertyGeolocation propertyGeolocation = PropertyGeolocation.builder()
                .latitude(54.6)
                .longitude(65.1)
                .build();

        when(propertyGeolocationMapper
                .propertyGeolocationDTOToPropertyGeolocation(propertyGeolocationDTO))
                .thenReturn(propertyGeolocation);

        when(propertyGeolocationRepository
                .save(any(PropertyGeolocation.class)))
                .thenReturn(propertyGeolocation);

        PropertyGeolocation createdPropertyGeolocation = propertyGeolocationService
                .createPropertyGeolocation(propertyGeolocationDTO);

        verify(propertyGeolocationRepository)
                .save(any(PropertyGeolocation.class));

        assertEquals(createdPropertyGeolocation.getLatitude(),
                propertyGeolocationDTO.getLatitude());
        assertEquals(createdPropertyGeolocation.getLongitude(),
                propertyGeolocationDTO.getLongitude());
    }

    @Test
    void updatePropertyGeolocation() {
        UUID id = UUID.randomUUID();
        PropertyGeolocationDTO propertyGeolocationDTO = PropertyGeolocationDTO.builder()
                .latitude(54.6)
                .longitude(67.1)
                .build();

        PropertyGeolocation propertyGeolocation = PropertyGeolocation.builder()
                .latitude(54.6)
                .longitude(65.1)
                .build();

        when(propertyGeolocationRepository.findById(id))
                .thenReturn(Optional.of(propertyGeolocation));

        propertyGeolocationService.updatePropertyGeolocation(id, propertyGeolocationDTO);

        verify(propertyGeolocationRepository)
                .save(any(PropertyGeolocation.class));

        assertEquals(propertyGeolocation.getLongitude(),
                propertyGeolocationDTO.getLongitude());
        assertEquals(propertyGeolocation.getLatitude(),
                propertyGeolocationDTO.getLatitude());
    }

    @Test
    void updatePropertyGeolocation_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        when(propertyGeolocationRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> propertyGeolocationService.
                updatePropertyGeolocation(id, PropertyGeolocationDTO.builder().build()));
    }

    @Test
    void updatePropertyGeolocation_NoLatitude_ShouldThrowNullException() {
        UUID id = UUID.randomUUID();
        PropertyGeolocationDTO propertyGeolocationDTO = PropertyGeolocationDTO.builder()
                .longitude(67.1)
                .build();

        PropertyGeolocation propertyGeolocation = PropertyGeolocation.builder()
                .latitude(54.6)
                .longitude(65.1)
                .build();

        when(propertyGeolocationRepository.findById(id))
                .thenReturn(Optional.of(propertyGeolocation));

        assertThrows(NullException.class, () -> propertyGeolocationService.
                updatePropertyGeolocation(id, propertyGeolocationDTO));
    }

    @Test
    void updatePropertyGeolocation_NoLongitude_ShouldThrowNullException() {
        UUID id = UUID.randomUUID();
        PropertyGeolocationDTO propertyGeolocationDTO = PropertyGeolocationDTO.builder()
                .latitude(54.6)
                .build();

        PropertyGeolocation propertyGeolocation = PropertyGeolocation.builder()
                .latitude(54.6)
                .longitude(65.1)
                .build();

        when(propertyGeolocationRepository.findById(id))
                .thenReturn(Optional.of(propertyGeolocation));

        assertThrows(NullException.class, () -> propertyGeolocationService.
                updatePropertyGeolocation(id, propertyGeolocationDTO));
    }

    @Test
    void patchPropertyGeolocation() {
        UUID id = UUID.randomUUID();
        PropertyGeolocationPatchDTO propertyGeolocationPatchDTO = PropertyGeolocationPatchDTO
                .builder()
                .longitude(67.1)
                .build();

        PropertyGeolocation propertyGeolocation = PropertyGeolocation.builder()
                .latitude(54.6)
                .longitude(65.1)
                .build();

        when(propertyGeolocationRepository.findById(id))
                .thenReturn(Optional.of(propertyGeolocation));

        propertyGeolocationService.patchPropertyGeolocation(id, propertyGeolocationPatchDTO);

        verify(propertyGeolocationRepository)
                .save(any(PropertyGeolocation.class));

        assertEquals(propertyGeolocation.getLongitude(),
                propertyGeolocationPatchDTO.getLongitude());
        assertEquals(propertyGeolocation.getLatitude(), 54.6);
    }

    @Test
    void patchPropertyGeolocation_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class,
                () -> propertyGeolocationService.patchPropertyGeolocation(id,
                        PropertyGeolocationPatchDTO.builder().build()));
    }
}