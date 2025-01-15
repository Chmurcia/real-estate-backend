package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToResponseDTO;
import cloud.uwu.realestatebackend.entities.property.Property;
import cloud.uwu.realestatebackend.entities.property.PropertyDistanceTo;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.PropertyDistanceToMapper;
import cloud.uwu.realestatebackend.repositories.property.PropertyDistanceToRepository;
import cloud.uwu.realestatebackend.repositories.property.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyDistanceToServiceUnitTests {

    @Mock
    private PropertyDistanceToRepository propertyDistanceToRepository;

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private PropertyDistanceToMapper propertyDistanceToMapper;

    @InjectMocks
    private PropertyDistanceToService propertyDistanceToService;

    @Test
    void getPropertyDistancesToByPropertyId() {
        UUID id = UUID.randomUUID();

        List<PropertyDistanceTo> propertyDistancesTo = Arrays.asList(
                PropertyDistanceTo.builder().build(),
                PropertyDistanceTo.builder().build(),
                PropertyDistanceTo.builder().build(),
                PropertyDistanceTo.builder().build()

        );

        when(propertyRepository.findById(id))
                .thenReturn(Optional.of(Property.builder().build()));

        when(propertyDistanceToRepository
                .getPropertyDistancesToByPropertyId(id))
                .thenReturn(propertyDistancesTo);

        when(propertyDistanceToMapper
                .propertyDistanceToToPropertyDistanceToResponseDTO(any(PropertyDistanceTo.class)))
                .thenReturn(PropertyDistanceToResponseDTO.builder().build());

        List<PropertyDistanceToResponseDTO> foundPropertyDistancesTo = propertyDistanceToService
                .getPropertyDistancesToByPropertyId(id);

        verify(propertyRepository).findById(id);

        assertNotNull(foundPropertyDistancesTo);
        assertEquals(foundPropertyDistancesTo.size(),
                propertyDistancesTo.size());
    }

    @Test
    void getPropertyDistancesToByPropertyId_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyDistanceToService
                        .getPropertyDistancesToByPropertyId(id));
    }

    @Test
    void getPropertyDistanceToById() {
        UUID id = UUID.randomUUID();

        PropertyDistanceTo propertyDistanceTo = PropertyDistanceTo.builder()
                .distance(500.0)
                .destination("Gym")
                .build();

        PropertyDistanceToResponseDTO propertyDistanceToResponseDTO = PropertyDistanceToResponseDTO
                .builder()
                .distance(500.0)
                .destination("Gym")
                .build();

        when(propertyDistanceToRepository.findById(id))
                .thenReturn(Optional.of(propertyDistanceTo));

        when(propertyDistanceToMapper
                .propertyDistanceToToPropertyDistanceToResponseDTO(propertyDistanceTo))
                .thenReturn(propertyDistanceToResponseDTO);

        PropertyDistanceToResponseDTO foundPropertyDistanceTo = propertyDistanceToService
                .getPropertyDistanceToById(id);

        verify(propertyDistanceToRepository).findById(id);

        assertNotNull(foundPropertyDistanceTo);
    }

    @Test
    void getPropertyDistanceToById_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyDistanceToService
                        .getPropertyDistanceToById(id));
    }

    @Test
    void createPropertyDistances() {
        UUID id = UUID.randomUUID();

        PropertyDistanceToDTO propertyDistanceToDTO = PropertyDistanceToDTO
                .builder()
                .distance(55.0)
                .destination("Supermarket")
                .build();

        PropertyDistanceTo propertyDistanceTo = PropertyDistanceTo
                .builder()
                .distance(55.0)
                .destination("Supermarket")
                .build();

        PropertyDistanceToResponseDTO propertyDistanceToResponseDTO = PropertyDistanceToResponseDTO
                .builder()
                .distance(55.0)
                .destination("Supermarket")
                .build();

        when(propertyRepository.findById(id))
                .thenReturn(Optional.of(Property.builder().build()));

        when(propertyDistanceToRepository.saveAndFlush(any(PropertyDistanceTo.class)))
                .thenReturn(propertyDistanceTo);

        when(propertyDistanceToMapper
                .propertyDistanceToToPropertyDistanceToResponseDTO(propertyDistanceTo))
                .thenReturn(propertyDistanceToResponseDTO);

        PropertyDistanceToResponseDTO createdPropertyDistanceTo = propertyDistanceToService
                .createPropertyDistances(id, propertyDistanceToDTO);

        verify(propertyRepository)
                .findById(id);
        assertEquals(createdPropertyDistanceTo.getDistance(),
                propertyDistanceToResponseDTO.getDistance());
        assertEquals(createdPropertyDistanceTo.getDestination(),
                propertyDistanceToResponseDTO.getDestination());
    }

    @Test
    void createPropertyDistances_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                propertyDistanceToService
                        .createPropertyDistances(UUID.randomUUID(),
                                PropertyDistanceToDTO.builder().build()));
    }

    @Test
    void updatePropertyDistances() {
        UUID id = UUID.randomUUID();

        PropertyDistanceToDTO propertyDistanceToDTO = PropertyDistanceToDTO
                .builder()
                .distance(78.5)
                .destination("Market")
                .build();

        PropertyDistanceTo propertyDistanceTo = PropertyDistanceTo
                .builder()
                .distance(55.0)
                .destination("Supermarket")
                .build();

        when(propertyDistanceToRepository.findById(id))
                .thenReturn(Optional.of(propertyDistanceTo));

        propertyDistanceToService.updatePropertyDistances(id, propertyDistanceToDTO);

        verify(propertyDistanceToRepository).findById(id);

        assertEquals(propertyDistanceTo.getDistance(),
                propertyDistanceToDTO.getDistance());
        assertEquals(propertyDistanceTo.getDestination(),
                propertyDistanceToDTO.getDestination());
    }

    @Test
    void updatePropertyDistances_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyDistanceToService
                        .updatePropertyDistances(id,
                                PropertyDistanceToDTO.builder().build()));
    }

    @Test
    void patchPropertyDistances() {
        UUID id = UUID.randomUUID();

        PropertyDistanceToPatchDTO propertyDistanceToPatchDTO = PropertyDistanceToPatchDTO
                .builder()
                .distance(78.5)
                .build();

        PropertyDistanceTo propertyDistanceTo = PropertyDistanceTo
                .builder()
                .distance(55.0)
                .destination("Supermarket")
                .build();

        when(propertyDistanceToRepository.findById(id))
                .thenReturn(Optional.of(propertyDistanceTo));

        propertyDistanceToService.patchPropertyDistances(id, propertyDistanceToPatchDTO);

        verify(propertyDistanceToRepository).findById(id);

        assertEquals(propertyDistanceTo.getDistance(),
                propertyDistanceToPatchDTO.getDistance());
        assertEquals(propertyDistanceTo.getDestination(),
                "Supermarket");
    }

    @Test
    void patchPropertyDistances_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyDistanceToService
                        .patchPropertyDistances(id,
                                PropertyDistanceToPatchDTO.builder().build()));
    }

    @Test
    void deletePropertyDistanceTo() {
        UUID id = UUID.randomUUID();

        when(propertyDistanceToRepository.findById(id))
                .thenReturn(Optional.of(PropertyDistanceTo.builder().build()));

        propertyDistanceToService.deletePropertyDistanceTo(id);

        verify(propertyDistanceToRepository).deleteById(id);
    }

    @Test
    void deletePropertyDistances_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyDistanceToService
                        .deletePropertyDistanceTo(id));
    }
}