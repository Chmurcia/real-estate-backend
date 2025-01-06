package cloud.uwu.realestatebackend.services.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyCounts;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyCountsMapper;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyCountsRepository;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyCountsService {
    private final PropertyCountsRepository propertyCountsRepository;
    private final PropertyStatisticsRepository propertyStatisticsRepository;
    private final PropertyCountsMapper propertyCountsMapper;

    public PropertyCountsResponseDTO getPropertyCountsByPropertyStatisticsId(UUID id) {
        getPropertyStatistics(id);

        PropertyCounts propertyCounts = propertyCountsRepository.getPropertyCountsByPropertyStatisticsId(id);

        return propertyCountsMapper
                .propertyCountsToPropertyCountsResponseDTO(propertyCounts);
    }

    public PropertyCountsResponseDTO createPropertyCounts(UUID propertyStatisticsID) {
        PropertyStatistics propertyStatistics = getPropertyStatistics(propertyStatisticsID);

        PropertyCounts propertyCounts = PropertyCounts.builder()
                .propertyStatistics(propertyStatistics)
                .visits(0)
                .likes(0)
                .dislikes(0)
                .build();

        PropertyCounts savedPropertyCounts = propertyCountsRepository
                .save(propertyCounts);

        return propertyCountsMapper
                .propertyCountsToPropertyCountsResponseDTO(savedPropertyCounts);
    }

    public void updatePropertyCounts(UUID id, PropertyCountsDTO propertyCountsDTO) {
        PropertyCounts propertyCounts = getPropertyCounts(id);

        if (propertyCountsDTO.getVisits() != null) {
            propertyCounts.setVisits(propertyCountsDTO.getVisits());
        } else {
            throw new NullException("visits are null");
        }

        if (propertyCountsDTO.getLikes() != null) {
            propertyCounts.setLikes(propertyCountsDTO.getLikes());
        } else {
            throw new NullException("likes are null");
        }

        if (propertyCountsDTO.getDislikes() != null) {
            propertyCounts.setDislikes(propertyCountsDTO.getDislikes());
        } else {
            throw new NullException("dislikes are null");
        }

        propertyCountsRepository.save(propertyCounts);
    }

    public void patchPropertyCounts(UUID id, PropertyCountsPatchDTO propertyCountsPatchDTO) {
        PropertyCounts propertyCounts = getPropertyCounts(id);

        if (propertyCountsPatchDTO.getVisits() != null) {
            propertyCounts.setVisits(propertyCountsPatchDTO.getVisits());
        }

        if (propertyCountsPatchDTO.getLikes() != null) {
            propertyCounts.setLikes(propertyCountsPatchDTO.getLikes());
        }

        if (propertyCountsPatchDTO.getDislikes() != null) {
            propertyCounts.setDislikes(propertyCountsPatchDTO.getDislikes());
        }

        propertyCountsRepository.save(propertyCounts);
    }

    public void deletePropertyCount(UUID id) {
        getPropertyCounts(id);

        propertyCountsRepository.deleteById(id);
    }

    //

    private PropertyCounts getPropertyCounts(UUID id) {
        return propertyCountsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyCounts not found"));
    }

    private PropertyStatistics getPropertyStatistics(UUID id) {
        return propertyStatisticsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyStatistics not found"));
    }
}
