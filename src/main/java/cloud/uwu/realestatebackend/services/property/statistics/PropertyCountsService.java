package cloud.uwu.realestatebackend.services.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyCounts;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyCountsMapper;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyCountsRepository;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyStatisticsRepository;
import cloud.uwu.realestatebackend.services.other.PropertyCountsBufferService;
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
    private final PropertyCountsBufferService propertyCountsBufferService;

    public PropertyCountsResponseDTO getPropertyCountsByPropertyStatisticsId(UUID id) {
        getPropertyStatistics(id);

        PropertyCounts propertyCounts = propertyCountsRepository.getPropertyCountsByPropertyStatisticsId(id);

        return propertyCountsMapper
                .propertyCountsToPropertyCountsResponseDTO(propertyCounts);
    }

    public PropertyCounts createPropertyCounts() {
        PropertyCounts propertyCounts = PropertyCounts.builder()
                .visits(0)
                .likes(0)
                .dislikes(0)
                .build();

        return propertyCountsRepository.saveAndFlush(propertyCounts);

    }

    public void updatePropertyCounts(UUID id, PropertyCountsDTO propertyCountsDTO) {
        getPropertyCounts(id);

        if (propertyCountsDTO.getVisits() == null) {
            throw new NullException("visits are null");
        }

        if (propertyCountsDTO.getLikes() == null) {
            throw new NullException("likes are null");
        }

        if (propertyCountsDTO.getDislikes() == null) {
            throw new NullException("dislikes are null");
        }

        propertyCountsBufferService.updatePropertyCountsInMemory(
                id,
                propertyCountsDTO.getVisits(),
                propertyCountsDTO.getLikes(),
                propertyCountsDTO.getDislikes()
        );
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

        propertyCountsBufferService.updatePropertyCountsInMemory(
                id,
                propertyCounts.getVisits(),
                propertyCounts.getLikes(),
                propertyCounts.getDislikes()
        );
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

    private void getPropertyStatistics(UUID id) {
        propertyStatisticsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyStatistics not found"));
    }
}
