package cloud.uwu.realestatebackend.mappers.property.location;

import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationDTO;
import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationResponseDTO;
import cloud.uwu.realestatebackend.entities.property.location.PropertyLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PropertyGeolocationMapper.class})
public interface PropertyLocationMapper {
    @Mapping(source = "property.id", target = "propertyId")
    PropertyLocationDTO propertyLocationToPropertyLocationDTO(PropertyLocation propertyLocation);

    PropertyLocation propertyLocationDTOToPropertyLocation(PropertyLocationDTO propertyLocationDTO);

    PropertyLocationResponseDTO propertyLocationToPropertyLocationResponseDTO(PropertyLocation propertyLocation);
}
