package cloud.uwu.realestatebackend.mappers.property;

import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyDetailsMapper {
    PropertyDetailsDTO propertyDetailsToPropertyDetailsDTO(PropertyDetails propertyDetails);

    PropertyDetailsResponseDTO propertyDetailsToPropertyDetailsResponseDTO(PropertyDetails propertyDetails);
}
