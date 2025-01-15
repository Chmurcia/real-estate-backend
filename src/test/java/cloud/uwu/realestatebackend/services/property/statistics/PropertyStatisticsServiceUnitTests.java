package cloud.uwu.realestatebackend.services.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyCounts;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyStatisticsMapper;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyCountsRepository;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyStatisticsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyStatisticsServiceUnitTests {

    @Mock
    private PropertyStatisticsRepository propertyStatisticsRepository;

    @Mock
    private PropertyStatisticsMapper propertyStatisticsMapper;

    @Mock
    private PropertyCountsRepository propertyCountsRepository;

    @Mock
    private PropertyCountsService propertyCountsService;

    @InjectMocks
    private PropertyStatisticsService propertyStatisticsService;

    private final ZonedDateTime zonedDateTime = ZonedDateTime.of(2024, 12,
            29, 10, 0, 0,
            0, ZoneId.of("Europe/Warsaw"));

    @Test
    void getPropertyStatisticsById() {
        UUID id = UUID.randomUUID();

        PropertyStatistics propertyStatistics = PropertyStatistics.builder()
                .rating(10.0)
                .lastVisitedAt(zonedDateTime)
                .build();

        PropertyStatisticsResponseDTO propertyStatisticsResponseDTO =
                PropertyStatisticsResponseDTO.builder()
                        .rating(propertyStatistics.getRating())
                        .lastVisitedAt(propertyStatistics.getLastVisitedAt())
                        .build();

        when(propertyStatisticsRepository.findById(id))
                .thenReturn(Optional.of(propertyStatistics));

        when(propertyStatisticsMapper
                .propertyStatisticsToPropertyStatisticsResponseDTO(propertyStatistics))
                .thenReturn(propertyStatisticsResponseDTO);

        PropertyStatisticsResponseDTO foundPropertyStatistics = propertyStatisticsService
                .getPropertyStatisticsById(id);

        verify(propertyStatisticsRepository).findById(id);

        assertNotNull(foundPropertyStatistics);
        assertEquals(foundPropertyStatistics.getRating(),
                propertyStatistics.getRating());
        assertEquals(foundPropertyStatistics.getLastVisitedAt(),
                propertyStatistics.getLastVisitedAt());
    }

    @Test
    void getPropertyStatisticsById_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyStatisticsService.getPropertyStatisticsById(id));
    }

    @Test
    void createPropertyStatistics() {
        PropertyStatisticsDTO propertyStatisticsDTO = PropertyStatisticsDTO
                .builder()
                .rating(9.73)
                .lastVisitedAt(zonedDateTime)
                .build();

        PropertyStatistics propertyStatistics = PropertyStatistics.builder()
                .id(UUID.randomUUID())
                .rating(propertyStatisticsDTO.getRating())
                .lastVisitedAt(propertyStatisticsDTO.getLastVisitedAt())
                .build();

        when(propertyStatisticsRepository.saveAndFlush(any(PropertyStatistics.class)))
                .thenReturn(propertyStatistics);

        PropertyStatistics createdPropertyStatistics = propertyStatisticsService
                .createPropertyStatistics(propertyStatisticsDTO);

        verify(propertyStatisticsRepository).saveAndFlush(any(PropertyStatistics.class));

        assertNotNull(createdPropertyStatistics);
        assertEquals(createdPropertyStatistics.getRating(),
                propertyStatisticsDTO.getRating());
        assertEquals(createdPropertyStatistics.getLastVisitedAt(),
                propertyStatisticsDTO.getLastVisitedAt());
    }

    @Test
    void updatePropertyStatistics() {
        UUID id = UUID.randomUUID();

        PropertyStatisticsDTO propertyStatisticsDTO = PropertyStatisticsDTO
                .builder()
                .rating(9.73)
                .lastVisitedAt(zonedDateTime)
                .build();

        PropertyStatistics propertyStatistics = PropertyStatistics.builder()
                .rating(9.81)
                .lastVisitedAt(zonedDateTime)
                .build();

        when(propertyStatisticsRepository.findById(id))
                .thenReturn(Optional.of(propertyStatistics));

        propertyStatisticsService.updatePropertyStatistics(id, propertyStatisticsDTO);

        assertEquals(propertyStatistics.getRating(),
                propertyStatisticsDTO.getRating());
        assertEquals(propertyStatistics.getLastVisitedAt(),
                propertyStatisticsDTO.getLastVisitedAt());
    }

    @Test
    void updatePropertyStatistics_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyStatisticsService.updatePropertyStatistics(id,
                        PropertyStatisticsDTO.builder().build()));
    }

    @Test
    void patchPropertyStatistics() {
        UUID id = UUID.randomUUID();

        PropertyStatisticsPatchDTO propertyStatisticsPatchDTO =
                PropertyStatisticsPatchDTO.builder()
                .rating(9.73)
                .build();

        PropertyStatistics propertyStatistics = PropertyStatistics.builder()
                .rating(9.81)
                .lastVisitedAt(zonedDateTime)
                .build();

        when(propertyStatisticsRepository.findById(id))
                .thenReturn(Optional.of(propertyStatistics));

        propertyStatisticsService.patchPropertyStatistics(id, propertyStatisticsPatchDTO);

        assertEquals(propertyStatistics.getRating(),
                propertyStatisticsPatchDTO.getRating());
        assertEquals(propertyStatistics.getLastVisitedAt(),
                zonedDateTime);
    }

    @Test
    void patchPropertyStatistics_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyStatisticsService.patchPropertyStatistics(id,
                        PropertyStatisticsPatchDTO.builder().build()));
    }

    @Test
    void deletePropertyStatistics() {
        UUID id = UUID.randomUUID();

        when(propertyStatisticsRepository.findById(id))
                .thenReturn(Optional.of(PropertyStatistics.builder().build()));

        propertyStatisticsService.deletePropertyStatistics(id);

        verify(propertyStatisticsRepository).deleteById(id);
    }

    @Test
    void deletePropertyStatistics_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyStatisticsService.deletePropertyStatistics(id));
    }
}