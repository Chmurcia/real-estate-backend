package cloud.uwu.realestatebackend.mappers.property;

import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyDistanceTo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyDistanceToMapper {
    PropertyDistanceToDTO propertyDistanceToToPropertyDistanceToDTO(PropertyDistanceTo propertyDistanceTo);

    PropertyDistanceToResponseDTO propertyDistanceToToPropertyDistanceToResponseDTO(PropertyDistanceTo propertyDistanceTo);
}
