package cloud.uwu.realestatebackend.mappers.property;

import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyDistanceTo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropertyDistanceToMapper {
    @Mapping(source = "property.id", target = "propertyId")
    PropertyDistanceToDTO propertyDistanceToToPropertyDistanceToDTO(PropertyDistanceTo propertyDistanceTo);

    PropertyDistanceToResponseDTO propertyDistanceToToPropertyDistanceToResponseDTO(PropertyDistanceTo propertyDistanceTo);
}
