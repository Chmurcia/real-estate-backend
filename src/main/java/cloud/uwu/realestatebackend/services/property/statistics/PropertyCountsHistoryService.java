package cloud.uwu.realestatebackend.services.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCountsHistory.PropertyCountsHistoryDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCountsHistory.PropertyCountsHistoryResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyCountsHistory;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyCountsHistoryMapper;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyCountsHistoryRepository;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyCountsHistoryService {
    private final PropertyCountsHistoryRepository propertyCountsHistoryRepository;
    private final PropertyStatisticsRepository propertyStatisticsRepository;
    private final PropertyCountsHistoryMapper propertyCountsHistoryMapper;

    public Page<PropertyCountsHistoryResponseDTO> getCountsHistoryByPropertyStatisticsId(
            UUID id, Integer page, Integer size) {

        getPropertyStatistics(id);

        page = (page != null) ? Math.max(page, 0) : 0;
        size = (size != null && size > 0) ? size : 50;

        Sort sort = Sort.by(Sort.Direction.ASC, "createdAt");

        PageRequest pageable = PageRequest.of(page, size, sort);

        return propertyCountsHistoryRepository.
                getPropertyCountsHistoriesByPropertyStatisticsIdOrderByCreatedAtAsc(id, pageable)
                .map(propertyCountsHistoryMapper::propertyCountsHistoryToPropertyCountsHistoryResponseDTO);
    }

    public PropertyCountsHistoryResponseDTO createCountsHistory(UUID id,
            PropertyCountsHistoryDTO propertyCountsHistoryDTO) {
        PropertyStatistics propertyStatistics = getPropertyStatistics(id);

        PropertyCountsHistory propertyCountsHistory =
                PropertyCountsHistory.builder()
                        .visits(propertyCountsHistoryDTO.getVisits())
                        .likes(propertyCountsHistoryDTO.getLikes())
                        .dislikes(propertyCountsHistoryDTO.getDislikes())
                        .propertyStatistics(propertyStatistics)
                .build();

        return propertyCountsHistoryMapper
                .propertyCountsHistoryToPropertyCountsHistoryResponseDTO(propertyCountsHistoryRepository
                        .saveAndFlush(propertyCountsHistory));

    }

    public void deleteCountsHistory(UUID id) {
        getCountsHistory(id);

        propertyCountsHistoryRepository.deleteById(id);
    }

    //

    private void getCountsHistory(UUID id) {
        propertyCountsHistoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyCountsHistory not found"));
    }

    private PropertyStatistics getPropertyStatistics(UUID id) {
        return propertyStatisticsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyStatistics not found"));
    }
}
