package cloud.uwu.realestatebackend.services.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCountsHistory.PropertyCountsHistoryDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCountsHistory.PropertyCountsHistoryResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyCountsHistory;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyCountsHistoryMapper;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyStatisticsMapper;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyCountsHistoryRepository;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyStatisticsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyCountsHistoryServiceUnitTests {

    @Mock
    private PropertyCountsHistoryRepository propertyCountsHistoryRepository;

    @Mock
    private PropertyStatisticsRepository propertyStatisticsRepository;

    @Mock
    private PropertyCountsHistoryMapper propertyCountsHistoryMapper;

    @InjectMocks
    private PropertyCountsHistoryService propertyCountsHistoryService;

    @Test
    void getCountsHistoryByPropertyStatisticsId() {
        UUID id = UUID.randomUUID();

        int page = 0;
        int size = 50;

        Sort sort = Sort.by(Sort.Direction.ASC, "createdAt");

        PageRequest pageable = PageRequest.of(page, size, sort);

        List <PropertyCountsHistory> propertyCountsHistory = List.of(
                PropertyCountsHistory.builder().build(),
                PropertyCountsHistory.builder().build(),
                PropertyCountsHistory.builder().build(),
                PropertyCountsHistory.builder().build()
        );

        Page<PropertyCountsHistory> propertyCountsHistoryPage = new PageImpl<>(
                propertyCountsHistory, pageable,
                propertyCountsHistory.size()
        );

        when(propertyStatisticsRepository.findById(id))
                .thenReturn(Optional.of(PropertyStatistics.builder().build()));

        when(propertyCountsHistoryRepository
                .getPropertyCountsHistoriesByPropertyStatisticsIdOrderByCreatedAtAsc(id, pageable))
                .thenReturn(propertyCountsHistoryPage);

        when(propertyCountsHistoryMapper
                .propertyCountsHistoryToPropertyCountsHistoryResponseDTO(
                        any(PropertyCountsHistory.class)))
                .thenReturn(PropertyCountsHistoryResponseDTO.builder().build());

        Page<PropertyCountsHistoryResponseDTO> foundCountsHistory = propertyCountsHistoryService
                .getCountsHistoryByPropertyStatisticsId(id, 0, 50);

        assertEquals(foundCountsHistory.getContent().size(),
                foundCountsHistory.getContent().size());
    }

    @Test
    void getCountsHistoryByPropertyStatisticsId_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                propertyCountsHistoryService
                        .getCountsHistoryByPropertyStatisticsId(UUID.randomUUID(), 0 ,50));
    }

    @Test
    void createCountsHistory() {
        UUID id = UUID.randomUUID();

        PropertyCountsHistoryDTO propertyCountsHistoryDTO =
                PropertyCountsHistoryDTO.builder()
                        .visits(10290)
                        .likes(918)
                        .dislikes(23)
                        .build();

        PropertyCountsHistoryResponseDTO propertyCountsHistoryResponseDTO =
                PropertyCountsHistoryResponseDTO.builder()
                        .visits(10290)
                        .likes(918)
                        .dislikes(23)
                        .build();

        PropertyCountsHistory propertyCountsHistory =
                PropertyCountsHistory.builder()
                        .visits(10290)
                        .likes(918)
                        .dislikes(23)
                        .build();

        when(propertyStatisticsRepository.findById(id))
                .thenReturn(Optional.of(PropertyStatistics.builder().build()));

        when(propertyCountsHistoryRepository.saveAndFlush(any(PropertyCountsHistory.class)))
                .thenReturn(propertyCountsHistory);

        when(propertyCountsHistoryMapper
                .propertyCountsHistoryToPropertyCountsHistoryResponseDTO(propertyCountsHistory))
                .thenReturn(propertyCountsHistoryResponseDTO);

        PropertyCountsHistoryResponseDTO createdCountsHistory =
                propertyCountsHistoryService
                        .createCountsHistory(id, propertyCountsHistoryDTO);

        verify(propertyCountsHistoryRepository)
                .saveAndFlush(any(PropertyCountsHistory.class));

        assertEquals(createdCountsHistory.getVisits(),
                propertyCountsHistoryDTO.getVisits());
        assertEquals(createdCountsHistory.getLikes(),
                propertyCountsHistoryDTO.getLikes());
        assertEquals(createdCountsHistory.getDislikes(),
                propertyCountsHistoryDTO.getDislikes());
    }

    @Test
    void createCountsHistory_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                propertyCountsHistoryService
                        .createCountsHistory(UUID.randomUUID(),
                                PropertyCountsHistoryDTO.builder().build()));
    }


    @Test
    void deleteCountsHistory() {
        UUID id = UUID.randomUUID();

        when(propertyCountsHistoryRepository.findById(id))
                .thenReturn(Optional.of(PropertyCountsHistory.builder().build()));

        propertyCountsHistoryService.deleteCountsHistory(id);

        verify(propertyCountsHistoryRepository).deleteById(id);
    }

    @Test
    void deleteCountsHistory_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                propertyCountsHistoryService.deleteCountsHistory(UUID.randomUUID()));
    }
}