package cloud.uwu.realestatebackend.services.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyTrustLevel.PropertyTrustLevelDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyTrustLevel.PropertyTrustLevelResponseDTO;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyTrustLevel;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.statistics.PropertyTrustLevelMapper;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyStatisticsRepository;
import cloud.uwu.realestatebackend.repositories.property.statistics.PropertyTrustLevelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyTrustLevelServiceUnitTests {
    @Mock
    private PropertyTrustLevelRepository propertyTrustLevelRepository;

    @Mock
    private PropertyStatisticsRepository propertyStatisticsRepository;

    @Mock
    private PropertyTrustLevelMapper propertyTrustLevelMapper;

    @InjectMocks
    private PropertyTrustLevelService propertyTrustLevelService;

    @Test
    void getPropertyTrustLevelsByPropertyStatisticsId() {
        UUID id = UUID.randomUUID();
        int page = 0;
        int size = 50;

        PageRequest pageable = PageRequest.of(page, size);

        List<PropertyTrustLevel> trustLevels = Arrays.asList(
                PropertyTrustLevel.builder().build(),
                PropertyTrustLevel.builder().build(),
                PropertyTrustLevel.builder().build()
        );

        Page<PropertyTrustLevel> trustLevelsPage = new PageImpl<>(
                trustLevels,
                pageable,
                trustLevels.size()
        );

        when(propertyStatisticsRepository.findById(id))
                .thenReturn(Optional.of(PropertyStatistics.builder().build()));

        when(propertyTrustLevelRepository
                .getPropertyTrustLevelsByPropertyStatisticsId(id, pageable))
                .thenReturn(trustLevelsPage);

        when(propertyTrustLevelMapper
                .propertyTrustLevelToPropertyTrustLevelResponseDTO(
                        any(PropertyTrustLevel.class))
        ).thenReturn(PropertyTrustLevelResponseDTO.builder().build());

        Page<PropertyTrustLevelResponseDTO> foundTrustLevels = propertyTrustLevelService
                .getPropertyTrustLevelsByPropertyStatisticsId(id, page, size);

        verify(propertyStatisticsRepository).findById(id);

        assertNotNull(foundTrustLevels);
        assertEquals(foundTrustLevels.getContent().size(),
                trustLevelsPage.getContent().size());
    }

    @Test
    void getPropertyTrustLevelsByPropertyStatisticsId_ShouldReturn1() {
        UUID id = UUID.randomUUID();
        int page = 0;
        int size = 1;

        PageRequest pageable = PageRequest.of(page, size);

        List<PropertyTrustLevel> trustLevels = Collections.singletonList(
                PropertyTrustLevel.builder().build()
        );

        Page<PropertyTrustLevel> trustLevelsPage = new PageImpl<>(
                trustLevels,
                pageable,
                trustLevels.size()
        );

        when(propertyStatisticsRepository.findById(id))
                .thenReturn(Optional.of(PropertyStatistics.builder().build()));

        when(propertyTrustLevelRepository
                .getPropertyTrustLevelsByPropertyStatisticsId(id, pageable))
                .thenReturn(trustLevelsPage);

        when(propertyTrustLevelMapper
                .propertyTrustLevelToPropertyTrustLevelResponseDTO(
                        any(PropertyTrustLevel.class))
        ).thenReturn(PropertyTrustLevelResponseDTO.builder().build());

        Page<PropertyTrustLevelResponseDTO> foundTrustLevels = propertyTrustLevelService
                .getPropertyTrustLevelsByPropertyStatisticsId(id, page, size);

        verify(propertyStatisticsRepository).findById(id);

        assertNotNull(foundTrustLevels);
        assertEquals(foundTrustLevels.getTotalElements(), 1);
    }

    @Test
    void getPropertyTrustLevelsByPropertyStatisticsId_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyTrustLevelService
                        .getPropertyTrustLevelsByPropertyStatisticsId(id, 0, 50));
    }

    @Test
    void createPropertyTrustLevel() {
        UUID id = UUID.randomUUID();

        PropertyTrustLevelDTO propertyTrustLevelDTO = PropertyTrustLevelDTO
                .builder()
                .evaluatorId(UUID.randomUUID())
                .trustLevel(9)
                .build();

        PropertyTrustLevel propertyTrustLevel = PropertyTrustLevel.builder()
                .evaluatorId(propertyTrustLevelDTO.getEvaluatorId())
                .trustLevel(propertyTrustLevelDTO.getTrustLevel())
                .build();

        PropertyTrustLevelResponseDTO propertyTrustLevelResponseDTO =
                PropertyTrustLevelResponseDTO.builder()
                .evaluatorId(propertyTrustLevelDTO.getEvaluatorId())
                .trustLevel(propertyTrustLevelDTO.getTrustLevel())
                .build();

        when(propertyStatisticsRepository
                .findById(id))
                .thenReturn(Optional.of(PropertyStatistics.builder().build()));

        when(propertyTrustLevelRepository.save(any(PropertyTrustLevel.class)))
                .thenReturn(propertyTrustLevel);

        when(propertyTrustLevelMapper
                .propertyTrustLevelToPropertyTrustLevelResponseDTO(propertyTrustLevel))
                .thenReturn(propertyTrustLevelResponseDTO);

        PropertyTrustLevelResponseDTO createdPropertyTrustLevel =
                propertyTrustLevelService.createPropertyTrustLevel(id, propertyTrustLevelDTO);

        verify(propertyStatisticsRepository)
                .findById(id);

        assertNotNull(createdPropertyTrustLevel);
        assertEquals(propertyTrustLevelDTO.getEvaluatorId(),
                createdPropertyTrustLevel.getEvaluatorId());
        assertEquals(propertyTrustLevelDTO.getTrustLevel(),
                createdPropertyTrustLevel.getTrustLevel());
    }

    @Test
    void createPropertyTrustLevel_ShouldThrowNotFoundException() {
        PropertyTrustLevelDTO propertyTrustLevelDTO = PropertyTrustLevelDTO
                .builder()
                .build();

        assertThrows(NotFoundException.class, () ->
                propertyTrustLevelService
                        .createPropertyTrustLevel(UUID.randomUUID(),
                                propertyTrustLevelDTO));
    }

    @Test
    void deletePropertyTrustLevel() {
        UUID id = UUID.randomUUID();

        when(propertyTrustLevelRepository.findById(id))
                .thenReturn(Optional.of(PropertyTrustLevel.builder().build()));

        propertyTrustLevelService.deletePropertyTrustLevel(id);

        verify(propertyTrustLevelRepository).deleteById(id);
    }

    @Test
    void deletePropertyTrustLevel_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyTrustLevelService.deletePropertyTrustLevel(id));
    }
}