package cloud.uwu.realestatebackend.mappers.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAmenity.PropertyAmenityDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAmenity.PropertyAmenityResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyAmenity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropertyAmenityMapper {
    @Mapping(source = "propertyStatistics.id", target = "propertyStatisticsId")
    PropertyAmenityDTO propertyAmenityToPropertyAmenityDTO(PropertyAmenity propertyAmenity);

    PropertyAmenityResponseDTO propertyAmenityToPropertyAmenityResponseDTO(PropertyAmenity propertyAmenity);
}
