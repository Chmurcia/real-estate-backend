package cloud.uwu.realestatebackend.mappers.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCountsHistory.PropertyCountsHistoryResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyCountsHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropertyCountsHistoryMapper {
    PropertyCountsHistoryResponseDTO propertyCountsHistoryToPropertyCountsHistoryResponseDTO(PropertyCountsHistory propertyCountsHistory);
}
