package cloud.uwu.realestatebackend.services.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAmenity.PropertyAmenityDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAmenity.PropertyAmenityResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyAmenity;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyAmenityMapper;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyAmenityRepository;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyAmenityService {
    private final PropertyAmenityRepository propertyAmenityRepository;
    private final PropertyStatisticsRepository propertyStatisticsRepository;
    private final PropertyAmenityMapper propertyAmenityMapper;

    public List<PropertyAmenityResponseDTO> getPropertyAmenitiesByPropertyStatisticsId(UUID id) {
        getPropertyStatistics(id);

        return propertyAmenityRepository.getPropertyAmenitiesByPropertyStatisticsId(id).stream()
                .map(propertyAmenityMapper::propertyAmenityToPropertyAmenityResponseDTO)
                .toList();
    }

    public PropertyAmenityResponseDTO createPropertyAmenity(UUID statisticsId, PropertyAmenityDTO propertyAmenityDTO) {
        PropertyStatistics propertyStatistics = getPropertyStatistics(statisticsId);

        PropertyAmenity propertyAmenity = PropertyAmenity.builder()
                .propertyStatistics(propertyStatistics)
                .amenityType(propertyAmenityDTO.getAmenityType())
                .build();

        PropertyAmenity savedPropertyAmenity = propertyAmenityRepository.saveAndFlush(propertyAmenity);

        return propertyAmenityMapper.propertyAmenityToPropertyAmenityResponseDTO(savedPropertyAmenity);
    }

    public void deletePropertyAmenity(UUID id) {
        checkPropertyAmenity(id);

        propertyAmenityRepository.deleteById(id);
    }
    //

    private void checkPropertyAmenity(UUID id) {
        propertyAmenityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyAmenity not found"));
    }

    private PropertyStatistics getPropertyStatistics(UUID id) {
        return propertyStatisticsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyStatistics not found"));
    }

}

