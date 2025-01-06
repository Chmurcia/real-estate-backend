package cloud.uwu.realestatebackend.services.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAccessibility.PropertyAccessibilityDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAccessibility.PropertyAccessibilityResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyAccessibility;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyAccessibilityMapper;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyAccessibilityRepository;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyAccessibilityService {
    private final PropertyAccessibilityRepository propertyAccessibilityRepository;
    private final PropertyStatisticsRepository propertyStatisticsRepository;
    private final PropertyAccessibilityMapper propertyAccessibilityMapper;

    public List<PropertyAccessibilityResponseDTO> getPropertyAccessibilitiesByPropertyStatisticsId(UUID id) {
        getPropertyStatistics(id);

        return propertyAccessibilityRepository.getPropertyAccessibilitiesByPropertyStatisticsId(id).stream()
                .map(propertyAccessibilityMapper::propertyAccessibilityToPropertyAccessibilityResponseDTO)
                .toList();
    }

    public PropertyAccessibilityResponseDTO createPropertyAccessibility(PropertyAccessibilityDTO propertyAccessibilityDTO) {
        PropertyStatistics propertyStatistics = getPropertyStatistics(propertyAccessibilityDTO
                .getPropertyStatisticsId());

        PropertyAccessibility propertyAccessibility = PropertyAccessibility.builder()
                .propertyStatistics(propertyStatistics)
                .accessibilityType(propertyAccessibilityDTO.getAccessibilityType())
                .build();

        PropertyAccessibility savedPropertyAccessibility = propertyAccessibilityRepository
                .save(propertyAccessibility);

        return propertyAccessibilityMapper
                .propertyAccessibilityToPropertyAccessibilityResponseDTO(savedPropertyAccessibility);
    }

    public void deletePropertyAccessibility(UUID id) {
        checkPropertyAccessibility(id);

        propertyAccessibilityRepository.deleteById(id);
    }

    //

    private void checkPropertyAccessibility(UUID id) {
         propertyAccessibilityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyAccessibility not found"));
    }

    private PropertyStatistics getPropertyStatistics(UUID id) {
        return propertyStatisticsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyStatistics not found"));
    }
}
