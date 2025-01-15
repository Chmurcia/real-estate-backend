package cloud.uwu.realestatebackend.mappers.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PropertyAccessibilityMapper.class, PropertyAmenityMapper.class, PropertyTrustLevelMapper.class, PropertyCountsMapper.class})
public interface PropertyStatisticsMapper {
    PropertyStatisticsDTO propertyStatisticsToPropertyStatisticsDTO(PropertyStatistics propertyStatistics);

    @Mapping(source = "propertyAccessibilities", target = "propertyAccessibilityResponseDTOS")
    @Mapping(source = "propertyAmenities", target = "propertyAmenityResponseDTOS")
    @Mapping(source = "propertyTrustLevels", target = "propertyTrustLevelResponseDTOS")
    @Mapping(source = "counts", target = "propertyCountsResponseDTO")
    PropertyStatisticsResponseDTO propertyStatisticsToPropertyStatisticsResponseDTO(PropertyStatistics propertyStatistics);
}
