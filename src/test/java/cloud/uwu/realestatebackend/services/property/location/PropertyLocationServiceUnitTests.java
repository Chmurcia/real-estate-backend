package cloud.uwu.realestatebackend.services.property.location;

import cloud.uwu.realestatebackend.dtos.other.post.LocationDTO;
import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationDTO;
import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationDTO;
import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationResponseDTO;
import cloud.uwu.realestatebackend.entities.property.location.PropertyGeolocation;
import cloud.uwu.realestatebackend.entities.property.location.PropertyLocation;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.location.PropertyLocationMapper;
import cloud.uwu.realestatebackend.repositories.property.location.PropertyLocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyLocationServiceUnitTests {
    @Mock
    private PropertyLocationRepository propertyLocationRepository;

    @Mock
    private PropertyLocationMapper propertyLocationMapper;

    @Mock
    private PropertyGeolocationService propertyGeolocationService;

    @InjectMocks
    private PropertyLocationService propertyLocationService;

    @Test
    void getPropertyLocationById() {
        UUID id = UUID.randomUUID();
        PropertyLocation propertyLocation = PropertyLocation.builder()
                .country("Estados Unidos")
                .state("Texas")
                .city("Sprunki Town")
                .zipCode("18-192")
                .address("Address 12/34")
                .geolocation(PropertyGeolocation.builder().build())
                .build();

        PropertyLocationResponseDTO propertyLocationResponseDTO =
                PropertyLocationResponseDTO.builder()
                        .country(propertyLocation.getCountry())
                        .state(propertyLocation.getState())
                        .city(propertyLocation.getCity())
                        .zipCode(propertyLocation.getZipCode())
                        .address(propertyLocation.getAddress())
                        .geolocationResponseDTO(PropertyGeolocationResponseDTO
                                .builder().build())
                        .build();

        when(propertyLocationRepository.findById(id))
                .thenReturn(Optional.of(propertyLocation));

        when(propertyLocationMapper
                .propertyLocationToPropertyLocationResponseDTO(propertyLocation))
                .thenReturn(propertyLocationResponseDTO);

        PropertyLocationResponseDTO foundPropertyLocation = propertyLocationService
                .getPropertyLocationById(id);

        assertEquals(foundPropertyLocation.getCountry(), propertyLocation.getCountry());
        assertEquals(foundPropertyLocation.getState(), propertyLocation.getState());
        assertEquals(foundPropertyLocation.getCity(), propertyLocation.getCity());
        assertEquals(foundPropertyLocation.getZipCode(), propertyLocation.getZipCode());
        assertEquals(foundPropertyLocation.getAddress(), propertyLocation.getAddress());
    }

    @Test
    void getPropertyLocationById_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyLocationService.getPropertyLocationById(id));
    }

    @Test
    void createPropertyLocation() {
        PropertyLocationDTO propertyLocationDTO = PropertyLocationDTO.builder()
                .country("Estados Unidos")
                .state("Texas")
                .city("Sprunki Town")
                .zipCode("18-192")
                .address("Address 12/34")
                .build();

        PropertyGeolocationDTO propertyGeolocationDTO = PropertyGeolocationDTO.builder()
                .latitude(81.9)
                .longitude(32.6)
                .build();

        PropertyLocation propertyLocation = PropertyLocation.builder()
                .country("Estados Unidos")
                .state("Texas")
                .city("Sprunki Town")
                .zipCode("18-192")
                .address("Address 12/34")
                .build();

        PropertyGeolocation propertyGeolocation = PropertyGeolocation.builder()
                .latitude(81.9)
                .longitude(32.6)
                .build();

        LocationDTO locationDTO = LocationDTO.builder()
                .propertyLocationDTO(propertyLocationDTO)
                .propertyGeolocationDTO(propertyGeolocationDTO)
                .build();

        when(propertyLocationMapper
                .propertyLocationDTOToPropertyLocation(propertyLocationDTO))
                .thenReturn(propertyLocation);

        when(propertyGeolocationService
                .createPropertyGeolocation(propertyGeolocationDTO))
                .thenReturn(propertyGeolocation);

        when(propertyLocationRepository.save(any(PropertyLocation.class)))
                .thenReturn(propertyLocation);

        PropertyLocation createdPropertyLocation = propertyLocationService
                .createPropertyLocation(locationDTO);

        verify(propertyLocationRepository).save(any(PropertyLocation.class));

        assertEquals(createdPropertyLocation.getCountry(), propertyLocation.getCountry());
        assertEquals(createdPropertyLocation.getAddress(), propertyLocation.getAddress());
        assertEquals(createdPropertyLocation.getGeolocation().getLongitude(),
                propertyGeolocationDTO.getLongitude());
        assertEquals(createdPropertyLocation.getGeolocation().getLatitude(),
                propertyGeolocationDTO.getLatitude());
    }

    @Test
    void updatePropertyLocation() {
        UUID id = UUID.randomUUID();

        PropertyLocationDTO propertyLocationDTO = PropertyLocationDTO.builder()
                .country("United States of America")
                .state("Arizona")
                .city("Sprunki Town")
                .zipCode("18-192")
                .address("Address 12/34")
                .build();

        PropertyLocation propertyLocation = PropertyLocation.builder()
                .country("Estados Unidos")
                .state("Texas")
                .city("Drunki Town")
                .zipCode("18-192")
                .address("Address 12/34")
                .build();

        when(propertyLocationRepository.findById(id))
                .thenReturn(Optional.of(propertyLocation));

        propertyLocationService.updatePropertyLocation(id, propertyLocationDTO);

        verify(propertyLocationRepository).save(propertyLocation);

        assertEquals(propertyLocation.getCountry(), propertyLocationDTO.getCountry());
        assertEquals(propertyLocation.getState(), propertyLocationDTO.getState());
        assertEquals(propertyLocation.getCity(), propertyLocationDTO.getCity());
        assertEquals(propertyLocation.getZipCode(), propertyLocationDTO.getZipCode());
        assertEquals(propertyLocation.getAddress(), propertyLocationDTO.getAddress());
    }

    @Test
    void updatePropertyLocation_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyLocationService.updatePropertyLocation(id,
                        PropertyLocationDTO.builder().build()));
    }

    @Test
    void patchPropertyLocation() {
        UUID id = UUID.randomUUID();

        PropertyLocationPatchDTO propertyLocationPatchDTO = PropertyLocationPatchDTO
                .builder()
                .country("United States of America")
                .state("Arizona")
                .city("Sprunki Town")
                .build();

        PropertyLocation propertyLocation = PropertyLocation.builder()
                .country("Estados Unidos")
                .state("Texas")
                .city("Drunki Town")
                .zipCode("18-192")
                .address("Address 12/34")
                .build();

        when(propertyLocationRepository.findById(id))
                .thenReturn(Optional.of(propertyLocation));

        propertyLocationService.patchPropertyLocation(id, propertyLocationPatchDTO);

        verify(propertyLocationRepository).save(propertyLocation);

        assertEquals(propertyLocation.getCountry(), propertyLocationPatchDTO.getCountry());
        assertEquals(propertyLocation.getState(), propertyLocationPatchDTO.getState());
        assertEquals(propertyLocation.getCity(), propertyLocationPatchDTO.getCity());
        assertEquals(propertyLocation.getZipCode(), "18-192");
        assertEquals(propertyLocation.getAddress(), "Address 12/34");
    }

    @Test
    void patchPropertyLocation_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyLocationService.patchPropertyLocation(id,
                        PropertyLocationPatchDTO.builder().build()));
    }

//    @Test
//    void deletePropertyLocation() {
//        UUID id = UUID.randomUUID();
//
//        PropertyLocation propertyLocation = PropertyLocation.builder()
//                .country("Estados Unidos")
//                .state("Texas")
//                .city("Drunki Town")
//                .zipCode("18-192")
//                .address("Address 12/34")
//                .geolocation(PropertyGeolocation.builder()
//                        .id(UUID.randomUUID())
//                        .build())
//                .build();
//
//        when(propertyLocationRepository.findById(id))
//                .thenReturn(Optional.of(propertyLocation));
//
//        propertyLocationService.deletePropertyLocation(id);
//
//        verify(propertyGeolocationService).deletePropertyGeolocation(propertyLocation
//                .getGeolocation().getId());
//        verify(propertyLocationRepository).deleteById(id);
//    }

    @Test
    void deletePropertyLocation_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyLocationService.deletePropertyLocation(id));
    }
}