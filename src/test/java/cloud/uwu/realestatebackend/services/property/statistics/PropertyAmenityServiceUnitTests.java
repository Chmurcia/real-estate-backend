package cloud.uwu.realestatebackend.services.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAmenity.PropertyAmenityDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAmenity.PropertyAmenityResponseDTO;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.AmenityType;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyAmenity;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyAmenityMapper;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyAmenityRepository;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyStatisticsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyAmenityServiceUnitTests {

    @Mock
    private PropertyAmenityRepository propertyAmenityRepository;

    @Mock
    private PropertyStatisticsRepository propertyStatisticsRepository;

    @Mock
    private PropertyAmenityMapper propertyAmenityMapper;

    @InjectMocks
    private PropertyAmenityService propertyAmenityService;

    @Test
    void getPropertyAmenitiesByPropertyStatisticsId() {
        UUID id = UUID.randomUUID();

        List<PropertyAmenity> amenities = Arrays.asList(
                PropertyAmenity.builder().build(),
                PropertyAmenity.builder().build(),
                PropertyAmenity.builder().build()
        );

        when(propertyStatisticsRepository.findById(id))
                .thenReturn(Optional.of(PropertyStatistics.builder().build()));

        when(propertyAmenityRepository
                .getPropertyAmenitiesByPropertyStatisticsId(id))
                .thenReturn(amenities);

        when(propertyAmenityMapper
                .propertyAmenityToPropertyAmenityResponseDTO(
                        any(PropertyAmenity.class)
                )).thenReturn(PropertyAmenityResponseDTO.builder().build());

        List<PropertyAmenityResponseDTO> foundAmenities = propertyAmenityService
                .getPropertyAmenitiesByPropertyStatisticsId(id);

        verify(propertyStatisticsRepository).findById(id);

        assertNotNull(foundAmenities);
        assertEquals(foundAmenities.size(), amenities.size());
    }

    @Test
    void getPropertyAmenitiesByPropertyStatisticsId_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyAmenityService
                        .getPropertyAmenitiesByPropertyStatisticsId(id));
    }

    @Test
    void createPropertyAmenity() {
        UUID id = UUID.randomUUID();

        PropertyAmenityDTO propertyAmenityDTO = PropertyAmenityDTO.builder()
                .amenityType(AmenityType.ELEVATOR)
                .build();

        PropertyStatistics propertyStatistics = PropertyStatistics.builder()
                .id(id)
                .build();

        PropertyAmenity propertyAmenity = PropertyAmenity.builder()
                .amenityType(AmenityType.ELEVATOR)
                .propertyStatistics(propertyStatistics)
                .build();

        PropertyAmenityResponseDTO propertyAmenityResponseDTO =
                PropertyAmenityResponseDTO.builder()
                        .amenityType(AmenityType.ELEVATOR)
                        .build();

        when(propertyStatisticsRepository
                .findById(id))
                .thenReturn(Optional.of(propertyStatistics));

        when(propertyAmenityRepository.save(any(PropertyAmenity.class)))
                .thenReturn(propertyAmenity);

        when(propertyAmenityMapper
                .propertyAmenityToPropertyAmenityResponseDTO(propertyAmenity))
                .thenReturn(propertyAmenityResponseDTO);

        PropertyAmenityResponseDTO createdPropertyAmenity = propertyAmenityService
                .createPropertyAmenity(id, propertyAmenityDTO);

        verify(propertyStatisticsRepository)
                .findById(id);

        assertNotNull(createdPropertyAmenity);
        assertEquals(createdPropertyAmenity.getAmenityType(),
                propertyAmenityDTO.getAmenityType());
    }

    @Test
    void createPropertyAmenity_ShouldThrowNotFoundException() {
        PropertyAmenityDTO propertyAmenityDTO = PropertyAmenityDTO.builder()
                .build();

        assertThrows(NotFoundException.class, () ->
                propertyAmenityService
                        .createPropertyAmenity(UUID.randomUUID(), propertyAmenityDTO));
    }

    @Test
    void deletePropertyAmenity() {
        UUID id = UUID.randomUUID();

        when(propertyAmenityRepository.findById(id))
                .thenReturn(Optional.of(PropertyAmenity.builder().build()));

        propertyAmenityService.deletePropertyAmenity(id);

        verify(propertyAmenityRepository).deleteById(id);
    }

    @Test
    void deletePropertyAmenity_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyAmenityService
                        .deletePropertyAmenity(id));
    }
}