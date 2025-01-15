package cloud.uwu.realestatebackend.services.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyCounts;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyStatisticsMapper;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyStatisticsService {
    private final PropertyStatisticsRepository propertyStatisticsRepository;
    private final PropertyStatisticsMapper propertyStatisticsMapper;
    private final PropertyCountsService propertyCountsService;

    public PropertyStatisticsResponseDTO getPropertyStatisticsById(UUID id) {
        PropertyStatistics propertyStatistics = getPropertyStatistics(id);

        return propertyStatisticsMapper
                .propertyStatisticsToPropertyStatisticsResponseDTO(propertyStatistics);
    }

    public PropertyStatistics createPropertyStatistics(PropertyStatisticsDTO propertyStatisticsDTO) {

        PropertyCounts propertyCounts = propertyCountsService.createPropertyCounts();

        PropertyStatistics propertyStatistics = PropertyStatistics.builder()
                .rating(propertyStatisticsDTO.getRating())
                .lastVisitedAt(propertyStatisticsDTO.getLastVisitedAt())
                .counts(propertyCounts)
                .build();

        return propertyStatisticsRepository.saveAndFlush(propertyStatistics);
    }

    public void updatePropertyStatistics(UUID id, PropertyStatisticsDTO propertyStatisticsDTO) {
        PropertyStatistics propertyStatistics = getPropertyStatistics(id);

        if (propertyStatisticsDTO.getRating() != null) {
            propertyStatistics.setRating(propertyStatisticsDTO.getRating());
        } else {
            throw new NullException("last_visited_at is null");
        }

        if (propertyStatisticsDTO.getLastVisitedAt() != null) {
            propertyStatistics.setLastVisitedAt(propertyStatisticsDTO.getLastVisitedAt());
        } else {
            throw new NullException("last_visited_at is null");
        }

        propertyStatisticsRepository.save(propertyStatistics);
    }

    public void patchPropertyStatistics(UUID id, PropertyStatisticsPatchDTO propertyStatisticsPatchDTO) {
        PropertyStatistics propertyStatistics = getPropertyStatistics(id);

        if (propertyStatisticsPatchDTO.getRating() != null) {
            propertyStatistics.setRating(propertyStatisticsPatchDTO.getRating());
        }

        if (propertyStatisticsPatchDTO.getLastVisitedAt() != null) {
            propertyStatistics.setLastVisitedAt(propertyStatisticsPatchDTO.getLastVisitedAt());
        }

        propertyStatisticsRepository.save(propertyStatistics);
    }

    public void deletePropertyStatistics(UUID id) {
        getPropertyStatistics(id);

        propertyStatisticsRepository.deleteById(id);
    }

    //

    private PropertyStatistics getPropertyStatistics(UUID id) {
        return propertyStatisticsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyStatistics not found"));
    }
}
