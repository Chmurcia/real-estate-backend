package cloud.uwu.realestatebackend.mappers.property.location;

import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationDTO;
import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationResponseDTO;
import cloud.uwu.realestatebackend.entities.property.location.PropertyLocation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PropertyGeolocationMapper.class})
public interface PropertyLocationMapper {
    PropertyLocationDTO propertyLocationToPropertyLocationDTO(PropertyLocation propertyLocation);

    PropertyLocationResponseDTO propertyLocationToPropertyLocationResponseDTO(PropertyLocation propertyLocation);
}
