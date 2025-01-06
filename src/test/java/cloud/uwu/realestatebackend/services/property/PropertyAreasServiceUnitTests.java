package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyAreas;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.PropertyAreasMapper;
import cloud.uwu.realestatebackend.repositories.property.PropertyAreasRepository;
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
class PropertyAreasServiceUnitTests {

    @Mock
    private PropertyAreasRepository propertyAreasRepository;

    @Mock
    private PropertyAreasMapper propertyAreasMapper;

    @InjectMocks
    private PropertyAreasService propertyAreasService;

    @Test
    void getPropertyAreasById() {
        UUID id = UUID.randomUUID();

        PropertyAreas propertyAreas = PropertyAreas.builder()
                .totalArea(317.96)
                .buildingArea(130.12)
                .livingArea(70.98)
                .gardenArea(13.45)
                .garageArea(45.0)
                .basementArea(39.3)
                .atticArea(19.2)
                .poolArea(0.0)
                .build();

        PropertyAreasResponseDTO propertyAreasResponseDTO = PropertyAreasResponseDTO
                .builder()
                .totalArea(317.96)
                .buildingArea(130.12)
                .livingArea(70.98)
                .gardenArea(13.45)
                .garageArea(45.0)
                .basementArea(39.3)
                .atticArea(19.2)
                .poolArea(0.0)
                .build();

        when(propertyAreasRepository.findById(id))
                .thenReturn(Optional.of(propertyAreas));

        when(propertyAreasMapper
                .propertyAreasToPropertyAreasResponseDTO(propertyAreas))
                .thenReturn(propertyAreasResponseDTO);

        PropertyAreasResponseDTO foundPropertyAreas = propertyAreasService
                .getPropertyAreasById(id);

        verify(propertyAreasRepository).findById(id);

        assertNotNull(foundPropertyAreas);
    }

    @Test
    void getPropertyAreasById_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyAreasService.getPropertyAreasById(id));
    }

    @Test
    void createPropertyAreas() {
        PropertyAreasDTO propertyAreasDTO = PropertyAreasDTO
                .builder()
                .totalArea(317.96)
                .buildingArea(130.12)
                .livingArea(70.98)
                .gardenArea(13.45)
                .garageArea(45.0)
                .basementArea(39.3)
                .atticArea(19.2)
                .poolArea(0.0)
                .build();

        PropertyAreas propertyAreas = PropertyAreas.builder()
                .totalArea(317.96)
                .buildingArea(130.12)
                .livingArea(70.98)
                .gardenArea(13.45)
                .garageArea(45.0)
                .basementArea(39.3)
                .atticArea(19.2)
                .poolArea(0.0)
                .build();

        when(propertyAreasRepository.save(any(PropertyAreas.class)))
                .thenReturn(propertyAreas);

        PropertyAreas createdPropertyAreas = propertyAreasService
                .createPropertyAreas(propertyAreasDTO);

        verify(propertyAreasRepository).save(any(PropertyAreas.class));

        assertNotNull(createdPropertyAreas);
    }

    @Test
    void updatePropertyAreas() {
        UUID id = UUID.randomUUID();

        PropertyAreasDTO propertyAreasDTO = PropertyAreasDTO
                .builder()
                .totalArea(317.96)
                .buildingArea(130.12)
                .livingArea(70.98)
                .gardenArea(13.45)
                .garageArea(45.0)
                .basementArea(39.3)
                .atticArea(19.2)
                .poolArea(0.0)
                .build();

        PropertyAreas propertyAreas = PropertyAreas.builder()
                .totalArea(370.00)
                .buildingArea(150.00)
                .livingArea(80.00)
                .gardenArea(20.00)
                .garageArea(50.00)
                .basementArea(40.00)
                .atticArea(25.00)
                .poolArea(5.00)
                .build();

        when(propertyAreasRepository.findById(id))
                .thenReturn(Optional.of(propertyAreas));

        propertyAreasService.updatePropertyAreas(id, propertyAreasDTO);

        verify(propertyAreasRepository).save(propertyAreas);

        assertEquals(propertyAreas.getTotalArea(),
                propertyAreasDTO.getTotalArea());
        assertEquals(propertyAreas.getBuildingArea(),
                propertyAreasDTO.getBuildingArea());
        assertEquals(propertyAreas.getLivingArea(),
                propertyAreasDTO.getLivingArea());
        assertEquals(propertyAreas.getGarageArea(),
                propertyAreasDTO.getGarageArea());
        assertEquals(propertyAreas.getGarageArea(),
                propertyAreasDTO.getGarageArea());
        assertEquals(propertyAreas.getBasementArea(),
                propertyAreasDTO.getBasementArea());
        assertEquals(propertyAreas.getAtticArea(),
                propertyAreasDTO.getAtticArea());
        assertEquals(propertyAreas.getPoolArea(),
                propertyAreasDTO.getPoolArea());
    }

    @Test
    void updatePropertyAreas_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyAreasService.updatePropertyAreas(id,
                        PropertyAreasDTO.builder().build()));
    }

    @Test
    void patchPropertyAreas() {
        UUID id = UUID.randomUUID();

        PropertyAreasPatchDTO propertyAreasPatchDTO = PropertyAreasPatchDTO
                .builder()
                .totalArea(329.55)
                .buildingArea(130.12)
                .livingArea(70.98)
                .gardenArea(13.45)
                .garageArea(45.0)
                .build();

        PropertyAreas propertyAreas = PropertyAreas.builder()
                .totalArea(370.00)
                .buildingArea(150.00)
                .livingArea(80.00)
                .gardenArea(20.00)
                .garageArea(50.00)
                .basementArea(40.00)
                .atticArea(25.00)
                .poolArea(5.00)
                .build();

        when(propertyAreasRepository.findById(id))
                .thenReturn(Optional.of(propertyAreas));

        propertyAreasService.patchPropertyAreas(id, propertyAreasPatchDTO);

        verify(propertyAreasRepository).save(propertyAreas);

        assertEquals(propertyAreas.getTotalArea(),
                propertyAreasPatchDTO.getTotalArea());
        assertEquals(propertyAreas.getBuildingArea(),
                propertyAreasPatchDTO.getBuildingArea());
        assertEquals(propertyAreas.getLivingArea(),
                propertyAreasPatchDTO.getLivingArea());
        assertEquals(propertyAreas.getGarageArea(),
                propertyAreasPatchDTO.getGarageArea());
        assertEquals(propertyAreas.getGarageArea(),
                propertyAreasPatchDTO.getGarageArea());
        assertEquals(propertyAreas.getBasementArea(),
                40.00);
        assertEquals(propertyAreas.getAtticArea(),
                25.00);
        assertEquals(propertyAreas.getPoolArea(),
                5.00);
    }

    @Test
    void patchPropertyAreas_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyAreasService.patchPropertyAreas(id,
                        PropertyAreasPatchDTO.builder().build()));
    }

    @Test
    void deletePropertyAreas() {
        UUID id = UUID.randomUUID();

        when(propertyAreasRepository.findById(id))
                .thenReturn(Optional.of(PropertyAreas.builder().build()));

        propertyAreasService.deletePropertyAreas(id);

        verify(propertyAreasRepository).deleteById(id);
    }

    @Test
    void deletePropertyAreas_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyAreasService.deletePropertyAreas(id));
    }
}