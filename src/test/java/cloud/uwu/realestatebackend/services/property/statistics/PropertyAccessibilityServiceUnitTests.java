package cloud.uwu.realestatebackend.services.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAccessibility.PropertyAccessibilityDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAccessibility.PropertyAccessibilityResponseDTO;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.AccessibilityType;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyAccessibility;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyAccessibilityMapper;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyAccessibilityRepository;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyStatisticsRepository;
import org.apache.catalina.LifecycleState;
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
class PropertyAccessibilityServiceUnitTests {

    @Mock
    private PropertyAccessibilityRepository propertyAccessibilityRepository;

    @Mock
    private PropertyStatisticsRepository propertyStatisticsRepository;

    @Mock
    private PropertyAccessibilityMapper propertyAccessibilityMapper;

    @InjectMocks
    private PropertyAccessibilityService propertyAccessibilityService;

    @Test
    void getPropertyAccessibilitiesByPropertyStatisticsId() {
        UUID id = UUID.randomUUID();

        List<PropertyAccessibility> accessibilities = Arrays.asList(
                PropertyAccessibility.builder().build(),
                PropertyAccessibility.builder().build(),
                PropertyAccessibility.builder().build(),
                PropertyAccessibility.builder().build()
        );

        when(propertyStatisticsRepository.findById(id))
                .thenReturn(Optional.of(PropertyStatistics.builder().build()));

        when(propertyAccessibilityRepository
                .getPropertyAccessibilitiesByPropertyStatisticsId(id))
                .thenReturn(accessibilities);

        when(propertyAccessibilityMapper
                .propertyAccessibilityToPropertyAccessibilityResponseDTO(
                        any(PropertyAccessibility.class))
        ).thenReturn(PropertyAccessibilityResponseDTO.builder().build());

        List<PropertyAccessibilityResponseDTO> foundAccessibilities =
                propertyAccessibilityService
                        .getPropertyAccessibilitiesByPropertyStatisticsId(id);

        verify(propertyStatisticsRepository).findById(id);

        assertNotNull(foundAccessibilities);
        assertEquals(foundAccessibilities.size(), accessibilities.size());
    }

    @Test
    void getPropertyAccessibilitiesByPropertyStatisticsId_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyAccessibilityService
                        .getPropertyAccessibilitiesByPropertyStatisticsId(id));
    }

    @Test
    void createPropertyAccessibility() {
        PropertyAccessibilityDTO propertyAccessibilityDTO = PropertyAccessibilityDTO
                .builder()
                .propertyStatisticsId(UUID.randomUUID())
                .accessibilityType(AccessibilityType.SMART_HOME)
                .build();

        PropertyStatistics propertyStatistics = PropertyStatistics.builder()
                .id(propertyAccessibilityDTO.getPropertyStatisticsId())
                .build();

        PropertyAccessibility propertyAccessibility = PropertyAccessibility
                .builder()
                .accessibilityType(AccessibilityType.SMART_HOME)
                .propertyStatistics(propertyStatistics)
                .build();

        PropertyAccessibilityResponseDTO propertyAccessibilityResponseDTO =
                PropertyAccessibilityResponseDTO.builder()
                        .accessibilityType(AccessibilityType.SMART_HOME)
                        .build();

        when(propertyStatisticsRepository.findById(propertyAccessibilityDTO
                .getPropertyStatisticsId()))
                .thenReturn(Optional.of(propertyStatistics));

        when(propertyAccessibilityRepository.save(any(PropertyAccessibility.class)))
                .thenReturn(propertyAccessibility);

        when(propertyAccessibilityMapper
                .propertyAccessibilityToPropertyAccessibilityResponseDTO(propertyAccessibility))
                .thenReturn(propertyAccessibilityResponseDTO);

        PropertyAccessibilityResponseDTO createdPropertyAccessibility =
                propertyAccessibilityService
                        .createPropertyAccessibility(propertyAccessibilityDTO);

        verify(propertyStatisticsRepository)
                .findById(propertyAccessibilityDTO.getPropertyStatisticsId());

        assertEquals(createdPropertyAccessibility.getAccessibilityType(),
                propertyAccessibilityDTO.getAccessibilityType());
    }

    @Test
    void createPropertyAccessibility_ShouldThrowNotFoundException() {
        PropertyAccessibilityDTO propertyAccessibilityDTO = PropertyAccessibilityDTO
                .builder()
                .propertyStatisticsId(UUID.randomUUID())
                .build();

        assertThrows(NotFoundException.class, () ->
                propertyAccessibilityService
                        .createPropertyAccessibility(propertyAccessibilityDTO));
    }

    @Test
    void deletePropertyAccessibility() {
        UUID id = UUID.randomUUID();

        when(propertyAccessibilityRepository.findById(id))
                .thenReturn(Optional.of(PropertyAccessibility.builder().build()));

        propertyAccessibilityService.deletePropertyAccessibility(id);

        verify(propertyAccessibilityRepository).deleteById(id);
    }

    @Test
    void deletePropertyAccessibility_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyAccessibilityService.deletePropertyAccessibility(id));
    }
}