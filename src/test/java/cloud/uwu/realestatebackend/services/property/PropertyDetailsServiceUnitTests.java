package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.other.post.DetailsDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyAreas;
import cloud.uwu.realestatebackend.entities.property.PropertyDetails;
import cloud.uwu.realestatebackend.entities.property.PropertyRooms;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.ConditionStatus;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.PropertyDetailsMapper;
import cloud.uwu.realestatebackend.repositories.property.PropertyDetailsRepository;
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
class PropertyDetailsServiceUnitTests {

    @Mock
    private PropertyDetailsRepository propertyDetailsRepository;

    @Mock
    private PropertyDetailsMapper propertyDetailsMapper;

    @Mock
    private PropertyAreasService propertyAreasService;

    @Mock
    private PropertyRoomsService propertyRoomsService;

    @InjectMocks
    private PropertyDetailsService propertyDetailsService;

    @Test
    void getPropertyDetailsById() {
        UUID id = UUID.randomUUID();

        PropertyDetails propertyDetails = PropertyDetails.builder()
                .totalFloors(3)
                .yearBuilt(2025)
                .conditionStatus(ConditionStatus.NEW)
                .build();

        PropertyDetailsResponseDTO propertyDetailsResponseDTO = PropertyDetailsResponseDTO
                .builder()
                .totalFloors(3)
                .yearBuilt(2025)
                .conditionStatus(ConditionStatus.NEW)
                .build();

        when(propertyDetailsRepository.findById(id))
                .thenReturn(Optional.of(propertyDetails));

        when(propertyDetailsMapper
                .propertyDetailsToPropertyDetailsResponseDTO(propertyDetails))
                .thenReturn(propertyDetailsResponseDTO);

        PropertyDetailsResponseDTO foundPropertyDetails = propertyDetailsService
                .getPropertyDetailsById(id);

        verify(propertyDetailsRepository).findById(id);

        assertNotNull(foundPropertyDetails);

        assertEquals(propertyDetails.getTotalFloors(),
                foundPropertyDetails.getTotalFloors());
        assertEquals(propertyDetails.getYearBuilt(),
                foundPropertyDetails.getYearBuilt());
        assertEquals(propertyDetails.getConditionStatus(),
                foundPropertyDetails.getConditionStatus());
    }

    @Test
    void getPropertyDetailsById_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyDetailsService.getPropertyDetailsById(id));
    }

    @Test
    void createPropertyDetails() {
        PropertyDetailsDTO propertyDetailsDTO = PropertyDetailsDTO.builder()
                .totalFloors(3)
                .yearBuilt(2025)
                .conditionStatus(ConditionStatus.NEW)
                .build();

        DetailsDTO detailsDTO = DetailsDTO.builder()
                .propertyDetailsDTO(propertyDetailsDTO)
                .propertyAreasDTO(PropertyAreasDTO.builder().build())
                .propertyRoomsDTO(PropertyRoomsDTO.builder().build())
                .build();

        PropertyDetails propertyDetails = PropertyDetails.builder()
                .totalFloors(3)
                .yearBuilt(2025)
                .conditionStatus(ConditionStatus.NEW)
                .build();

        when(propertyAreasService.createPropertyAreas(detailsDTO.getPropertyAreasDTO()))
                .thenReturn(PropertyAreas.builder().build());

        when(propertyRoomsService.createPropertyRooms(detailsDTO.getPropertyRoomsDTO()))
                .thenReturn(PropertyRooms.builder().build());

        when(propertyDetailsRepository.saveAndFlush(any(PropertyDetails.class)))
                .thenReturn(propertyDetails);

        PropertyDetails createdPropertyDetails = propertyDetailsService
                .createPropertyDetails(detailsDTO);

        verify(propertyDetailsRepository).saveAndFlush(any(PropertyDetails.class));

        assertNotNull(createdPropertyDetails);
        assertEquals(createdPropertyDetails.getTotalFloors(),
                propertyDetailsDTO.getTotalFloors());
        assertEquals(createdPropertyDetails.getYearBuilt(),
                propertyDetailsDTO.getYearBuilt());
        assertEquals(createdPropertyDetails.getConditionStatus(),
                propertyDetailsDTO.getConditionStatus());
    }

    @Test
    void updatePropertyDetails() {
        UUID id = UUID.randomUUID();

        PropertyDetailsDTO propertyDetailsDTO = PropertyDetailsDTO.builder()
                .totalFloors(3)
                .yearBuilt(2025)
                .conditionStatus(ConditionStatus.NEW)
                .build();

        PropertyDetails propertyDetails = PropertyDetails.builder()
                .totalFloors(3)
                .yearBuilt(2025)
                .conditionStatus(ConditionStatus.NEW)
                .build();

        when(propertyDetailsRepository.findById(id))
                .thenReturn(Optional.of(propertyDetails));

        propertyDetailsService.updatePropertyDetails(id, propertyDetailsDTO);

        verify(propertyDetailsRepository).save(propertyDetails);

        assertEquals(propertyDetails.getTotalFloors(),
                propertyDetailsDTO.getTotalFloors());
        assertEquals(propertyDetails.getYearBuilt(),
                propertyDetailsDTO.getYearBuilt());
        assertEquals(propertyDetails.getConditionStatus(),
                propertyDetailsDTO.getConditionStatus());

    }

    @Test
    void updatePropertyDetails_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyDetailsService.updatePropertyDetails(id,
                        PropertyDetailsDTO.builder().build()));
    }

    @Test
    void patchPropertyDetails() {
        UUID id = UUID.randomUUID();

        PropertyDetailsPatchDTO propertyDetailsPatchDTO = PropertyDetailsPatchDTO
                .builder()
                .totalFloors(3)
                .yearBuilt(2025)
                .conditionStatus(ConditionStatus.NEW)
                .build();

        PropertyDetails propertyDetails = PropertyDetails.builder()
                .totalFloors(3)
                .yearBuilt(2025)
                .conditionStatus(ConditionStatus.NEW)
                .build();

        when(propertyDetailsRepository.findById(id))
                .thenReturn(Optional.of(propertyDetails));

        propertyDetailsService.patchPropertyDetails(id, propertyDetailsPatchDTO);

        verify(propertyDetailsRepository).save(propertyDetails);

        assertEquals(propertyDetails.getTotalFloors(),
                propertyDetailsPatchDTO.getTotalFloors());
        assertEquals(propertyDetails.getYearBuilt(),
                propertyDetailsPatchDTO.getYearBuilt());
        assertEquals(propertyDetails.getConditionStatus(),
                propertyDetailsPatchDTO.getConditionStatus());
    }

    @Test
    void patchPropertyDetails_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyDetailsService.patchPropertyDetails(id,
                        PropertyDetailsPatchDTO.builder().build()));
    }

    @Test
    void deletePropertyDetails() {
        UUID id = UUID.randomUUID();

        when(propertyDetailsRepository.findById(id))
                .thenReturn(Optional.of(PropertyDetails.builder().build()));

        propertyDetailsService.deletePropertyDetails(id);

        verify(propertyDetailsRepository).deleteById(id);
    }

    @Test
    void deletePropertyDetails_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyDetailsService.deletePropertyDetails(id));
    }
}