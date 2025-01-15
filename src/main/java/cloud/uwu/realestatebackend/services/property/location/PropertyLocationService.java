package cloud.uwu.realestatebackend.services.property.location;

import cloud.uwu.realestatebackend.dtos.other.post.LocationDTO;
import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationDTO;
import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationResponseDTO;
import cloud.uwu.realestatebackend.entities.property.location.PropertyGeolocation;
import cloud.uwu.realestatebackend.entities.property.location.PropertyLocation;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.location.PropertyLocationMapper;
import cloud.uwu.realestatebackend.repositories.property.location.PropertyLocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyLocationService {
    private final PropertyLocationRepository propertyLocationRepository;
    private final PropertyLocationMapper propertyLocationMapper;
    private final PropertyGeolocationService propertyGeolocationService;

    public PropertyLocationResponseDTO getPropertyLocationById(UUID id) {
        return propertyLocationMapper
                .propertyLocationToPropertyLocationResponseDTO(getPropertyLocation(id));
    }

    public PropertyLocation createPropertyLocation(
            LocationDTO locationDTO
    ) {
         PropertyLocation propertyLocation = propertyLocationMapper
                .propertyLocationDTOToPropertyLocation(locationDTO.getPropertyLocationDTO());

        PropertyGeolocation propertyGeolocation = propertyGeolocationService
                .createPropertyGeolocation(locationDTO.getPropertyGeolocationDTO());

        propertyLocation.setGeolocation(propertyGeolocation);

        return propertyLocationRepository.saveAndFlush(propertyLocation);
    }

    public void updatePropertyLocation(UUID id, PropertyLocationDTO propertyLocationDTO) {
        PropertyLocation propertyLocation = getPropertyLocation(id);

        if (StringUtils.hasText(propertyLocationDTO.getCountry())) {
            propertyLocation.setCountry(propertyLocationDTO.getCountry());
        } else {
            throw new NotFoundException("country is null");
        }

        if (StringUtils.hasText(propertyLocationDTO.getState())) {
            propertyLocation.setState(propertyLocationDTO.getState());
        } else {
            throw new NotFoundException("state is null");
        }

        if (StringUtils.hasText(propertyLocationDTO.getCity())) {
            propertyLocation.setCity(propertyLocationDTO.getCity());
        } else {
            throw new NotFoundException("city is null");
        }

        if (StringUtils.hasText(propertyLocationDTO.getZipCode())) {
            propertyLocation.setZipCode(propertyLocationDTO.getZipCode());
        } else {
            throw new NotFoundException("zip_code is null");
        }

        if (StringUtils.hasText(propertyLocationDTO.getAddress())) {
            propertyLocation.setAddress(propertyLocationDTO.getAddress());
        } else {
            throw new NotFoundException("address is null");
        }

        propertyLocationRepository.save(propertyLocation);
    }

    public void patchPropertyLocation(UUID id, PropertyLocationPatchDTO propertyLocationPatchDTO) {
        PropertyLocation propertyLocation = getPropertyLocation(id);

        if (StringUtils.hasText(propertyLocationPatchDTO.getCountry())) {
            propertyLocation.setCountry(propertyLocationPatchDTO.getCountry());
        }

        if (StringUtils.hasText(propertyLocationPatchDTO.getState())) {
            propertyLocation.setState(propertyLocationPatchDTO.getState());
        }

        if (StringUtils.hasText(propertyLocationPatchDTO.getCity())) {
            propertyLocation.setCity(propertyLocationPatchDTO.getCity());
        }

        if (StringUtils.hasText(propertyLocationPatchDTO.getZipCode())) {
            propertyLocation.setZipCode(propertyLocationPatchDTO.getZipCode());
        }

        if (StringUtils.hasText(propertyLocationPatchDTO.getAddress())) {
            propertyLocation.setAddress(propertyLocationPatchDTO.getAddress());
        }

        propertyLocationRepository.save(propertyLocation);
    }

    public void deletePropertyLocation(UUID id) {
        PropertyLocation propertyLocation = getPropertyLocation(id);

        propertyLocationRepository.deleteById(id);
    }

    //

    private PropertyLocation getPropertyLocation(UUID id) {
        return propertyLocationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyLocation not found"));
    }
}
