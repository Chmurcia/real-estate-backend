package cloud.uwu.realestatebackend.mappers.property;

import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PropertyAreasMapper.class, PropertyRoomsMapper.class})
public interface PropertyDetailsMapper {
    PropertyDetailsDTO propertyDetailsToPropertyDetailsDTO(PropertyDetails propertyDetails);

    @Mapping(source = "areas", target = "propertyAreasResponseDTO")
    @Mapping(source = "rooms", target = "propertyRoomsResponseDTO")
    PropertyDetailsResponseDTO propertyDetailsToPropertyDetailsResponseDTO(PropertyDetails propertyDetails);
}
