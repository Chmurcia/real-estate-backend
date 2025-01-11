package cloud.uwu.realestatebackend.services.property.location;

import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationDTO;
import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationPatchDTO;
import cloud.uwu.realestatebackend.entities.property.location.PropertyGeolocation;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.property.location.PropertyGeolocationMapper;
import cloud.uwu.realestatebackend.repositories.property.location.PropertyGeolocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyGeolocationService {
    private final PropertyGeolocationRepository propertyGeolocationRepository;
    private final PropertyGeolocationMapper propertyGeolocationMapper;

    public PropertyGeolocation createPropertyGeolocation(
            PropertyGeolocationDTO propertyGeolocationDTO) {
        return propertyGeolocationRepository.saveAndFlush(propertyGeolocationMapper
                .propertyGeolocationDTOToPropertyGeolocation(propertyGeolocationDTO));
    }

    public void updatePropertyGeolocation(UUID id, PropertyGeolocationDTO propertyGeolocationDTO) {
        PropertyGeolocation propertyGeolocation = getPropertyGeolocation(id);

        if (propertyGeolocationDTO.getLatitude() != null) {
            propertyGeolocation.setLatitude(propertyGeolocationDTO.getLatitude());
        } else {
            throw new NullException("latitude is null");
        }

        if (propertyGeolocationDTO.getLongitude() != null) {
            propertyGeolocation.setLongitude(propertyGeolocationDTO.getLongitude());
        } else {
            throw new NullException("longitude is null");
        }

        propertyGeolocationRepository.save(propertyGeolocation);
    }

    public void patchPropertyGeolocation(UUID id, PropertyGeolocationPatchDTO propertyGeolocationPatchDTO) {
        PropertyGeolocation propertyGeolocation = getPropertyGeolocation(id);

        if (propertyGeolocationPatchDTO.getLatitude() != null) {
            propertyGeolocation.setLatitude(propertyGeolocationPatchDTO.getLatitude());
        }

        if (propertyGeolocationPatchDTO.getLongitude() != null) {
            propertyGeolocation.setLongitude(propertyGeolocationPatchDTO.getLongitude());
        }

        propertyGeolocationRepository.save(propertyGeolocation);
    }

    //

    private PropertyGeolocation getPropertyGeolocation(UUID id) {
        return propertyGeolocationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyGeolocation not found"));
    }
}
