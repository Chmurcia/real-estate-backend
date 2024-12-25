package cloud.uwu.realestatebackend.mappers.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyCounts;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyCountsMapper {
    PropertyCountsDTO propertyCountsToPropertyCountsDTO(PropertyCounts propertyCounts);

    PropertyCountsResponseDTO propertyCountsToPropertyCountsResponseDTO(PropertyCounts propertyCounts);
}
