package cloud.uwu.realestatebackend.mappers.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyTrustLevel.PropertyTrustLevelDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyTrustLevel.PropertyTrustLevelResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyTrustLevel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyTrustLevelMapper {
    PropertyTrustLevelDTO propertyTrustLevelToPropertyTrustLevelDTO(PropertyTrustLevel propertyTrustLevel);

    PropertyTrustLevelResponseDTO propertyTrustLevelToPropertyTrustLevelResponseDTO(PropertyTrustLevel propertyTrustLevel);
}
