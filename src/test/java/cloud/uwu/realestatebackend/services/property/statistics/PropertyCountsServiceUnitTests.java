package cloud.uwu.realestatebackend.services.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyCounts;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyCountsMapper;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyCountsRepository;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyStatisticsRepository;
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
class PropertyCountsServiceUnitTests {
    @Mock
    private PropertyCountsRepository propertyCountsRepository;

    @Mock
    private PropertyStatisticsRepository propertyStatisticsRepository;

    @Mock
    private PropertyCountsMapper propertyCountsMapper;

    @InjectMocks
    private PropertyCountsService propertyCountsService;

    @Test
    void getPropertyCountsByPropertyStatisticsId() {
        UUID id = UUID.randomUUID();

        PropertyCounts propertyCounts = PropertyCounts.builder()
                .visits(918274)
                .likes(6152)
                .dislikes(91)
                .build();

        PropertyCountsResponseDTO propertyCountsResponseDTO = PropertyCountsResponseDTO
                .builder()
                .visits(918274)
                .likes(6152)
                .dislikes(91)
                .build();

        when(propertyStatisticsRepository.findById(id))
                .thenReturn(Optional.of(PropertyStatistics.builder().build()));

        when(propertyCountsRepository
                .getPropertyCountsByPropertyStatisticsId(id))
                .thenReturn(propertyCounts);

        when(propertyCountsMapper
                .propertyCountsToPropertyCountsResponseDTO(propertyCounts))
                .thenReturn(propertyCountsResponseDTO);

        PropertyCountsResponseDTO foundPropertyCounts = propertyCountsService
                .getPropertyCountsByPropertyStatisticsId(id);

        verify(propertyStatisticsRepository).findById(id);

        assertNotNull(foundPropertyCounts);
        assertEquals(foundPropertyCounts.getVisits(),
                propertyCountsResponseDTO.getVisits());
        assertEquals(foundPropertyCounts.getLikes(),
                propertyCountsResponseDTO.getLikes());
        assertEquals(foundPropertyCounts.getDislikes(),
                propertyCountsResponseDTO.getDislikes());
    }

    @Test
    void getPropertyCountsByPropertyStatisticsId_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyCountsService.getPropertyCountsByPropertyStatisticsId(id));
    }

    @Test
    void createPropertyCounts() {
        UUID id = UUID.randomUUID();

        PropertyCounts propertyCounts = PropertyCounts.builder()
                .visits(918274)
                .likes(6152)
                .dislikes(91)
                .build();

        PropertyCountsResponseDTO propertyCountsResponseDTO = PropertyCountsResponseDTO
                .builder()
                .visits(918274)
                .likes(6152)
                .dislikes(91)
                .build();

        when(propertyStatisticsRepository.findById(id))
                .thenReturn(Optional.of(PropertyStatistics.builder().build()));

        when(propertyCountsRepository.save(any(PropertyCounts.class)))
                .thenReturn(propertyCounts);

        when(propertyCountsMapper
                .propertyCountsToPropertyCountsResponseDTO(propertyCounts))
                .thenReturn(propertyCountsResponseDTO);

        PropertyCountsResponseDTO createPropertyCounts = propertyCountsService
                .createPropertyCounts(id);

        verify(propertyStatisticsRepository).findById(id);

        assertNotNull(createPropertyCounts);
        assertEquals(createPropertyCounts.getVisits(),
                propertyCountsResponseDTO.getVisits());
        assertEquals(createPropertyCounts.getLikes(),
                propertyCountsResponseDTO.getLikes());
        assertEquals(createPropertyCounts.getDislikes(),
                propertyCountsResponseDTO.getDislikes());
    }

    @Test
    void createPropertyCounts_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyCountsService.createPropertyCounts(id));
    }

    @Test
    void updatePropertyCounts() {
        UUID id = UUID.randomUUID();

        PropertyCountsDTO propertyCountsDTO = PropertyCountsDTO.builder()
                .visits(91823)
                .likes(20133)
                .dislikes(8185)
                .build();

        PropertyCounts propertyCounts = PropertyCounts.builder()
                .visits(97812)
                .likes(23198)
                .dislikes(8189)
                .build();

        when(propertyCountsRepository.findById(id))
                .thenReturn(Optional.of(propertyCounts));

        propertyCountsService.updatePropertyCounts(id, propertyCountsDTO);

        assertEquals(propertyCounts.getVisits(),
                propertyCountsDTO.getVisits());
        assertEquals(propertyCounts.getLikes(),
                propertyCountsDTO.getLikes());
        assertEquals(propertyCounts.getDislikes(),
                propertyCountsDTO.getDislikes());
    }

    @Test
    void updatePropertyCounts_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyCountsService.updatePropertyCounts(id,
                        PropertyCountsDTO.builder().build()));
    }

    @Test
    void patchPropertyCounts() {
        UUID id = UUID.randomUUID();

        PropertyCountsPatchDTO propertyCountsPatchDTO = PropertyCountsPatchDTO.builder()
                .visits(91823)
                .build();

        PropertyCounts propertyCounts = PropertyCounts.builder()
                .visits(97812)
                .likes(23198)
                .dislikes(8189)
                .build();

        when(propertyCountsRepository.findById(id))
                .thenReturn(Optional.of(propertyCounts));

        propertyCountsService.patchPropertyCounts(id, propertyCountsPatchDTO);

        assertEquals(propertyCounts.getVisits(),
                propertyCountsPatchDTO.getVisits());
        assertEquals(propertyCounts.getLikes(),
                23198);
        assertEquals(propertyCounts.getDislikes(),
                8189);
    }

    @Test
    void patchPropertyCounts_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyCountsService.patchPropertyCounts(id,
                        PropertyCountsPatchDTO.builder().build()));
    }

    @Test
    void deletePropertyCount() {
        UUID id = UUID.randomUUID();

        when(propertyCountsRepository.findById(id))
                .thenReturn(Optional.of(PropertyCounts.builder().build()));

        propertyCountsService.deletePropertyCount(id);

        verify(propertyCountsRepository).deleteById(id);
    }

    @Test
    void deletePropertyCount_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyCountsService.deletePropertyCount(id));
    }
}