package cloud.uwu.realestatebackend.mappers.property.location;

import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationDTO;
import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationResponseDTO;
import cloud.uwu.realestatebackend.entities.property.location.PropertyGeolocation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyGeolocationMapper {
    PropertyGeolocationDTO propertyGeolocationToPropertyGeolocationDTO(PropertyGeolocation propertyGeolocation);

    PropertyGeolocationResponseDTO propertyGeolocationToPropertyGeolocationResponseDTO(PropertyGeolocation propertyGeolocation);

}

