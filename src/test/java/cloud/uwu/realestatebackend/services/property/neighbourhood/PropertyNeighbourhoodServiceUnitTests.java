package cloud.uwu.realestatebackend.services.property.neighbourhood;

import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodResponseDTO;
import cloud.uwu.realestatebackend.entities.property.neighbourhood.PropertyNeighbourhood;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.neighbourhood.PropertyNeighbourhoodMapper;
import cloud.uwu.realestatebackend.repositories.property.neighbourhood.PropertyNeighbourhoodRepository;
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
class PropertyNeighbourhoodServiceUnitTests {
    @Mock
    private PropertyNeighbourhoodRepository propertyNeighbourhoodRepository;

    @Mock
    private PropertyNeighbourhoodMapper propertyNeighbourhoodMapper;

    @InjectMocks
    private PropertyNeighbourhoodService propertyNeighbourhoodService;

    @Test
    void getPropertyNeighbourhoodById() {
        UUID id = UUID.randomUUID();
        PropertyNeighbourhood propertyNeighbourhood = PropertyNeighbourhood.builder().build();

        when(propertyNeighbourhoodRepository.findById(id))
                .thenReturn(Optional.of(propertyNeighbourhood));

        when(propertyNeighbourhoodMapper
                .propertyNeighbourhoodToPropertyNeighbourhoodResponseDTO(propertyNeighbourhood))
                .thenReturn(PropertyNeighbourhoodResponseDTO.builder().build());

        PropertyNeighbourhoodResponseDTO foundPropertyNeighbourhood =
                propertyNeighbourhoodService.getPropertyNeighbourhoodById(id);

        verify(propertyNeighbourhoodRepository).findById(id);

        assertNotNull(foundPropertyNeighbourhood);
    }

    @Test
    void getPropertyNeighbourhoodById_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyNeighbourhoodService.getPropertyNeighbourhoodById(id));
    }

    @Test
    void createPropertyNeighbourhood() {
        PropertyNeighbourhoodDTO propertyNeighbourhoodDTO = PropertyNeighbourhoodDTO
                .builder()
                .noiseLevel(9)
                .qualityLevel(8)
                .safetyLevel(7)
                .build();

        PropertyNeighbourhood propertyNeighbourhood = PropertyNeighbourhood
                .builder()
                .noiseLevel(9)
                .qualityLevel(8)
                .safetyLevel(7)
                .build();

        when(propertyNeighbourhoodRepository.save(any(PropertyNeighbourhood.class)))
                .thenReturn(propertyNeighbourhood);

        PropertyNeighbourhood createdPropertyNeighbourhood = propertyNeighbourhoodService
                .createPropertyNeighbourhood(propertyNeighbourhoodDTO);

        assertNotNull(createdPropertyNeighbourhood);
        assertEquals(createdPropertyNeighbourhood.getNoiseLevel(),
                propertyNeighbourhoodDTO.getNoiseLevel());
        assertEquals(createdPropertyNeighbourhood.getQualityLevel(),
                propertyNeighbourhoodDTO.getQualityLevel());
        assertEquals(createdPropertyNeighbourhood.getSafetyLevel(),
                propertyNeighbourhoodDTO.getSafetyLevel());
    }

    @Test
    void updatePropertyNeighbourhood() {
        UUID id = UUID.randomUUID();
        PropertyNeighbourhoodDTO propertyNeighbourhoodPatchDTO =
                PropertyNeighbourhoodDTO.builder()
                        .noiseLevel(9)
                        .qualityLevel(8)
                        .safetyLevel(7)
                        .build();

        PropertyNeighbourhood propertyNeighbourhood =
                PropertyNeighbourhood.builder()
                        .noiseLevel(8)
                        .qualityLevel(3)
                        .safetyLevel(10)
                        .build();

        when(propertyNeighbourhoodRepository.findById(id))
                .thenReturn(Optional.of(propertyNeighbourhood));

        propertyNeighbourhoodService
                .updatePropertyNeighbourhood(id, propertyNeighbourhoodPatchDTO);

        verify(propertyNeighbourhoodRepository).save(any(PropertyNeighbourhood.class));

        assertEquals(propertyNeighbourhood.getNoiseLevel(),
                propertyNeighbourhoodPatchDTO.getNoiseLevel());
        assertEquals(propertyNeighbourhood.getQualityLevel(),
                propertyNeighbourhoodPatchDTO.getQualityLevel());
        assertEquals(propertyNeighbourhood.getSafetyLevel(),
                propertyNeighbourhoodPatchDTO.getSafetyLevel());
    }

    @Test
    void updatePropertyNeighbourhood_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyNeighbourhoodService.updatePropertyNeighbourhood(id,
                        PropertyNeighbourhoodDTO.builder().build()));
    }

    @Test
    void patchPropertyNeighbourhood() {
        UUID id = UUID.randomUUID();
        PropertyNeighbourhoodPatchDTO propertyNeighbourhoodPatchDTO =
                PropertyNeighbourhoodPatchDTO.builder()
                        .qualityLevel(8)
                        .build();

        PropertyNeighbourhood propertyNeighbourhood =
                PropertyNeighbourhood.builder()
                        .noiseLevel(8)
                        .qualityLevel(3)
                        .safetyLevel(10)
                        .build();

        when(propertyNeighbourhoodRepository.findById(id))
                .thenReturn(Optional.of(propertyNeighbourhood));

        propertyNeighbourhoodService
                .patchPropertyNeighbourhood(id, propertyNeighbourhoodPatchDTO);

        verify(propertyNeighbourhoodRepository).save(any(PropertyNeighbourhood.class));

        assertEquals(propertyNeighbourhood.getNoiseLevel(),
                8);
        assertEquals(propertyNeighbourhood.getQualityLevel(),
                propertyNeighbourhoodPatchDTO.getQualityLevel());
        assertEquals(propertyNeighbourhood.getSafetyLevel(),
                10);
    }

    @Test
    void patchPropertyNeighbourhood_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyNeighbourhoodService.patchPropertyNeighbourhood(id,
                        PropertyNeighbourhoodPatchDTO.builder().build()));
    }

    @Test
    void deletePropertyNeighbourhood() {
        UUID id = UUID.randomUUID();
        when(propertyNeighbourhoodRepository.findById(id))
                .thenReturn(Optional.of(PropertyNeighbourhood.builder().build()));

        propertyNeighbourhoodService.deletePropertyNeighbourhood(id);

        verify(propertyNeighbourhoodRepository).deleteById(id);
    }

    @Test
    void deletePropertyNeighbourhood_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyNeighbourhoodService.deletePropertyNeighbourhood(id));
    }
}