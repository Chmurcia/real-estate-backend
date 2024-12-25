package cloud.uwu.realestatebackend.mappers.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PropertyAccessibilityMapper.class, PropertyAmenityMapper.class, PropertyTrustLevelMapper.class, PropertyCountsMapper.class})
public interface PropertyStatisticsMapper {
    PropertyStatisticsDTO propertyStatisticsToPropertyStatisticsDTO(PropertyStatistics propertyStatistics);

    PropertyStatisticsResponseDTO propertyStatisticsToPropertyStatisticsResponseDTO(PropertyStatistics propertyStatistics);
}
