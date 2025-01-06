package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyRooms;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.factories.PropertyRoomsFactory;
import cloud.uwu.realestatebackend.mappers.property.PropertyRoomsMapper;
import cloud.uwu.realestatebackend.repositories.property.PropertyRoomsRepository;
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
class PropertyRoomsServiceUnitTests {
    @Mock
    private PropertyRoomsRepository propertyRoomsRepository;

    @Mock
    private PropertyRoomsMapper propertyRoomsMapper;

    @InjectMocks
    private PropertyRoomsService propertyRoomsService;

    private final PropertyRooms propertyRooms =
            PropertyRoomsFactory.createPropertyRooms();

    private final PropertyRoomsResponseDTO propertyRoomsResponseDTO =
            PropertyRoomsFactory.createPropertyRoomsResponseDTO();

    private final PropertyRoomsDTO propertyRoomsDTOCreate =
            PropertyRoomsFactory.createPropertyRoomsDTOToCreate();

    private final PropertyRoomsDTO propertyRoomsDTOUpdate =
            PropertyRoomsFactory.createPropertyRoomsDTOToUpdate();

    private final PropertyRoomsPatchDTO propertyRoomsPatchDTO =
            PropertyRoomsFactory.createPropertyRoomsPatchDTO();

    @Test
    void getPropertyRoomsById() {
        UUID id = UUID.randomUUID();

        when(propertyRoomsRepository.findById(id))
                .thenReturn(Optional.of(propertyRooms));

        when(propertyRoomsMapper
                .propertyRoomsToPropertyRoomsResponseDTO(propertyRooms))
                .thenReturn(propertyRoomsResponseDTO);

        PropertyRoomsResponseDTO foundPropertyRooms = propertyRoomsService
                .getPropertyRoomsById(id);

        verify(propertyRoomsRepository).findById(id);

        assertNotNull(foundPropertyRooms);
    }

    @Test
    void getPropertyRoomsById_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyRoomsService.getPropertyRoomsById(id));
    }

    @Test
    void createPropertyRooms() {
        when(propertyRoomsRepository.save(any(PropertyRooms.class)))
                .thenReturn(propertyRooms);

        PropertyRooms createdPropertyRooms = propertyRoomsService
                .createPropertyRooms(propertyRoomsDTOCreate);

        verify(propertyRoomsRepository).save(any(PropertyRooms.class));

        comparePropertyRoomsDTOs(createdPropertyRooms, propertyRoomsDTOCreate);
    }

    @Test
    void updatePropertyRooms() {
        UUID id = UUID.randomUUID();

        when(propertyRoomsRepository.findById(id))
                .thenReturn(Optional.of(propertyRooms));

        propertyRoomsService.updatePropertyRooms(id,
                propertyRoomsDTOUpdate);

        verify(propertyRoomsRepository).save(propertyRooms);

        comparePropertyRoomsDTOs(propertyRooms, propertyRoomsDTOUpdate);
    }

    @Test
    void updatePropertyRooms_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyRoomsService.updatePropertyRooms(id,
                        propertyRoomsDTOUpdate));
    }

    @Test
    void patchPropertyRooms() {
        UUID id = UUID.randomUUID();

        when(propertyRoomsRepository.findById(id))
                .thenReturn(Optional.of(propertyRooms));

        propertyRoomsService.patchPropertyRooms(id, propertyRoomsPatchDTO);

        verify(propertyRoomsRepository).save(propertyRooms);

        comparePatchedPropertyRoomsDTO(propertyRooms);
    }

    @Test
    void patchPropertyRooms_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyRoomsService.patchPropertyRooms(id, propertyRoomsPatchDTO));
    }

    @Test
    void deletePropertyRooms() {
        UUID id = UUID.randomUUID();

        when(propertyRoomsRepository.findById(id))
                .thenReturn(Optional.of(propertyRooms));

        propertyRoomsService.deletePropertyRooms(id);

        verify(propertyRoomsRepository).deleteById(id);
    }

    @Test
    void deletePropertyRooms_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyRoomsService.deletePropertyRooms(id));
    }

    private void comparePropertyRoomsDTOs(PropertyRooms createdPropertyRooms,
                                          PropertyRoomsDTO propertyRoomsDTO) {
        assertEquals(createdPropertyRooms.getTotalRooms(),
                propertyRoomsDTO.getTotalRooms());
        assertEquals(createdPropertyRooms.getTotalBedrooms(),
                propertyRoomsDTO.getTotalBedrooms());
        assertEquals(createdPropertyRooms.getTotalKitchens(),
                propertyRoomsDTO.getTotalKitchens());
        assertEquals(createdPropertyRooms.getTotalBathrooms(),
                propertyRoomsDTO.getTotalBathrooms());
        assertEquals(createdPropertyRooms.getTotalLivingRooms(),
                propertyRoomsDTO.getTotalLivingRooms());
        assertEquals(createdPropertyRooms.getTotalDiningRooms(),
                propertyRoomsDTO.getTotalDiningRooms());
        assertEquals(createdPropertyRooms.getTotalBalconies(),
                propertyRoomsDTO.getTotalBalconies());
        assertEquals(createdPropertyRooms.getTotalGarages(),
                propertyRoomsDTO.getTotalGarages());
        assertEquals(createdPropertyRooms.getTotalBasements(),
                propertyRoomsDTO.getTotalBasements());
        assertEquals(createdPropertyRooms.getTotalAttics(),
                propertyRoomsDTO.getTotalAttics());
        assertEquals(createdPropertyRooms.getTotalStorageRooms(),
                propertyRoomsDTO.getTotalStorageRooms());
        assertEquals(createdPropertyRooms.getTotalClosets(),
                propertyRoomsDTO.getTotalClosets());
        assertEquals(createdPropertyRooms.getTotalTheaterRooms(),
                propertyRoomsDTO.getTotalTheaterRooms());
        assertEquals(createdPropertyRooms.getTotalSaunas(),
                propertyRoomsDTO.getTotalSaunas());
        assertEquals(createdPropertyRooms.getTotalHomeOffices(),
                propertyRoomsDTO.getTotalHomeOffices());
        assertEquals(createdPropertyRooms.getTotalGyms(),
                propertyRoomsDTO.getTotalGyms());
        assertEquals(createdPropertyRooms.getTotalLibraries(),
                propertyRoomsDTO.getTotalLibraries());
    }

    private void comparePatchedPropertyRoomsDTO(PropertyRooms propertyRooms) {
        assertEquals(propertyRooms.getTotalRooms(),
                propertyRoomsPatchDTO.getTotalRooms());
        assertEquals(propertyRooms.getTotalBedrooms(),
                propertyRoomsPatchDTO.getTotalBedrooms());
        assertEquals(propertyRooms.getTotalKitchens(),
                propertyRoomsPatchDTO.getTotalKitchens());
        assertEquals(propertyRooms.getTotalBathrooms(),
                propertyRoomsPatchDTO.getTotalBathrooms());
        assertEquals(propertyRooms.getTotalLivingRooms(),
                propertyRoomsPatchDTO.getTotalLivingRooms());
        assertEquals(propertyRooms.getTotalDiningRooms(),
                propertyRoomsPatchDTO.getTotalDiningRooms());
        assertEquals(propertyRooms.getTotalBalconies(),
                propertyRoomsPatchDTO.getTotalBalconies());
        assertEquals(propertyRooms.getTotalGarages(),
                propertyRoomsPatchDTO.getTotalGarages());
        assertEquals(propertyRooms.getTotalBasements(),
                propertyRoomsPatchDTO.getTotalBasements());
        assertEquals(propertyRooms.getTotalAttics(),
                propertyRoomsPatchDTO.getTotalAttics());
        assertEquals(propertyRooms.getTotalStorageRooms(),
                propertyRoomsPatchDTO.getTotalStorageRooms());
        assertEquals(propertyRooms.getTotalClosets(),
                propertyRoomsPatchDTO.getTotalClosets());
        assertEquals(propertyRooms.getTotalTheaterRooms(),
                propertyRoomsPatchDTO.getTotalTheaterRooms());
        assertEquals(propertyRooms.getTotalSaunas(),
                propertyRoomsPatchDTO.getTotalSaunas());
        assertEquals(propertyRooms.getTotalHomeOffices(),
                propertyRoomsPatchDTO.getTotalHomeOffices());
        assertEquals(propertyRooms.getTotalGyms(),
                propertyRoomsPatchDTO.getTotalGyms());
        assertEquals(propertyRooms.getTotalLibraries(),
                propertyRoomsPatchDTO.getTotalLibraries());
    }
}