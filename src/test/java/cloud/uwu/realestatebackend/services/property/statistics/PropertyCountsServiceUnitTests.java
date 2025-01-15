package cloud.uwu.realestatebackend.services.property.statistics;

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

        when(propertyCountsRepository.saveAndFlush(any(PropertyCounts.class)))
                .thenReturn(propertyCounts);

        PropertyCounts createPropertyCounts = propertyCountsService
                .createPropertyCounts();

        assertNotNull(createPropertyCounts);
        assertEquals(createPropertyCounts.getVisits(),
                propertyCountsResponseDTO.getVisits());
        assertEquals(createPropertyCounts.getLikes(),
                propertyCountsResponseDTO.getLikes());
        assertEquals(createPropertyCounts.getDislikes(),
                propertyCountsResponseDTO.getDislikes());
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