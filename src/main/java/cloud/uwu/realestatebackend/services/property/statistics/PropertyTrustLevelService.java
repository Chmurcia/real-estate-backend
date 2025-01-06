package cloud.uwu.realestatebackend.services.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyTrustLevel.PropertyTrustLevelDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyTrustLevel.PropertyTrustLevelResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyTrustLevel;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyTrustLevelMapper;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyStatisticsRepository;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyTrustLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyTrustLevelService {
    private final PropertyTrustLevelRepository propertyTrustLevelRepository;
    private final PropertyStatisticsRepository propertyStatisticsRepository;
    private final PropertyTrustLevelMapper propertyTrustLevelMapper;

    public Page<PropertyTrustLevelResponseDTO> getPropertyTrustLevelsByPropertyStatisticsId(UUID id, int page, int size) {
        getPropertyStatistics(id);

        page = Math.max(page, 0);
        size = size > 0 ? size : 50;

        PageRequest pageable = PageRequest.of(page, size);

        return propertyTrustLevelRepository.getPropertyTrustLevelsByPropertyStatisticsId(id, pageable)
                .map(propertyTrustLevelMapper::propertyTrustLevelToPropertyTrustLevelResponseDTO);
    }

    public PropertyTrustLevelResponseDTO createPropertyTrustLevel(PropertyTrustLevelDTO propertyTrustLevelDTO) {
        PropertyStatistics propertyStatistics = getPropertyStatistics(propertyTrustLevelDTO
                .getPropertyStatisticsId());

        PropertyTrustLevel propertyTrustLevel = PropertyTrustLevel.builder()
                .propertyStatistics(propertyStatistics)
                .evaluatorId(propertyTrustLevelDTO.getEvaluatorId())
                .trustLevel(propertyTrustLevelDTO.getTrustLevel())
                .build();

        PropertyTrustLevel savedPropertyTrustLevel = propertyTrustLevelRepository
                .save(propertyTrustLevel);

        return propertyTrustLevelMapper
                .propertyTrustLevelToPropertyTrustLevelResponseDTO(savedPropertyTrustLevel);
    }

    public void deletePropertyTrustLevel(UUID id) {
        getPropertyTrustLevel(id);

        propertyTrustLevelRepository.deleteById(id);
    }

    //

    private void getPropertyTrustLevel(UUID id) {
        propertyTrustLevelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyAmenity not found"));
    }

    private PropertyStatistics getPropertyStatistics(UUID id) {
        return propertyStatisticsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyStatistics not found"));
    }
}
