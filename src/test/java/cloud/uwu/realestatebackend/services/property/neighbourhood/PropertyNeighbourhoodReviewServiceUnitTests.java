package cloud.uwu.realestatebackend.services.property.neighbourhood;

import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview.PropertyNeighbourhoodReviewDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview.PropertyNeighbourhoodReviewPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview.PropertyNeighbourhoodReviewResponseDTO;
import cloud.uwu.realestatebackend.entities.property.neighbourhood.PropertyNeighbourhood;
import cloud.uwu.realestatebackend.entities.property.neighbourhood.PropertyNeighbourhoodReview;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.neighbourhood.PropertyNeighbourhoodReviewMapper;
import cloud.uwu.realestatebackend.repositories.property.neighbourhood.PropertyNeighbourhoodRepository;
import cloud.uwu.realestatebackend.repositories.property.neighbourhood.PropertyNeighbourhoodReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyNeighbourhoodReviewServiceUnitTests {
    @Mock
    private PropertyNeighbourhoodReviewRepository propertyNeighbourhoodReviewRepository;

    @Mock
    private PropertyNeighbourhoodRepository propertyNeighbourhoodRepository;

    @Mock
    private PropertyNeighbourhoodReviewMapper propertyNeighbourhoodReviewMapper;

    @InjectMocks
    private PropertyNeighbourhoodReviewService propertyNeighbourhoodReviewService;

    @Test
    void getPropertyNeighbourhoodReviewById() {
        UUID id = UUID.randomUUID();
        PropertyNeighbourhoodReview propertyNeighbourhoodReview =
                PropertyNeighbourhoodReview.builder().build();

        when(propertyNeighbourhoodReviewRepository.findById(id))
                .thenReturn(Optional.of(propertyNeighbourhoodReview));

        when(propertyNeighbourhoodReviewMapper
                .propertyNeighbourhoodReviewToPropertyNeighbourhoodReviewResponseDTO(
                        propertyNeighbourhoodReview
                )).thenReturn(PropertyNeighbourhoodReviewResponseDTO.builder().build());

        PropertyNeighbourhoodReviewResponseDTO foundPropertyNeighbourhoodReview =
                propertyNeighbourhoodReviewService.getPropertyNeighbourhoodReviewById(id);

        verify(propertyNeighbourhoodReviewRepository).findById(id);

        assertNotNull(foundPropertyNeighbourhoodReview);
    }

    @Test
    void getPropertyNeighbourhoodReviewById_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyNeighbourhoodReviewService
                        .getPropertyNeighbourhoodReviewById(id)
        );
    }

    @Test
    void getPropertyNeighbourhoodReviewsByNeighbourhoodId() {
        UUID id = UUID.randomUUID();
        int page = 0;
        int size = 13;
        PageRequest pageable = PageRequest.of(page, size);

        List<PropertyNeighbourhoodReview> reviews = Arrays.asList(
                PropertyNeighbourhoodReview.builder().build(),
                PropertyNeighbourhoodReview.builder().build(),
                PropertyNeighbourhoodReview.builder().build()
        );

        Page<PropertyNeighbourhoodReview> pageReviews =
                new PageImpl<>(reviews, pageable, reviews.size());

        when(propertyNeighbourhoodRepository.findById(id))
                .thenReturn(Optional.of(PropertyNeighbourhood.builder().build()));

        when(propertyNeighbourhoodReviewMapper
                .propertyNeighbourhoodReviewToPropertyNeighbourhoodReviewResponseDTO(any(
                        PropertyNeighbourhoodReview.class
                ))).thenReturn(PropertyNeighbourhoodReviewResponseDTO.builder().build());

        when(propertyNeighbourhoodReviewRepository
                .getPropertyNeighbourhoodReviewsByNeighbourhoodId(id, pageable))
                .thenReturn(pageReviews);

        Page<PropertyNeighbourhoodReviewResponseDTO> foundPage =
                propertyNeighbourhoodReviewService
                        .getPropertyNeighbourhoodReviewsByNeighbourhoodId(id, page, size);

        verify(propertyNeighbourhoodRepository).findById(id);

        assertNotNull(foundPage);
        assertEquals(foundPage.getTotalElements(), 3);
    }

    @Test
    void getPropertyNeighbourhoodReviewsByNeighbourhoodId_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyNeighbourhoodReviewService
                        .getPropertyNeighbourhoodReviewsByNeighbourhoodId(id, 0, 50));
    }

    @Test
    void createPropertyNeighbourhoodReview() {
        UUID id = UUID.randomUUID();

        PropertyNeighbourhoodReviewDTO propertyNeighbourhoodReviewDTO =
                PropertyNeighbourhoodReviewDTO.builder()
                        .reviewerId(UUID.randomUUID())
                        .rate(7)
                        .description("Description")
                        .noiseLevelRate(8)
                        .qualityLevelRate(9)
                        .safetyLevelRate(10)
                        .build();

        PropertyNeighbourhoodReview propertyNeighbourhoodReview =
                PropertyNeighbourhoodReview.builder()
                        .reviewerId(propertyNeighbourhoodReviewDTO
                                .getReviewerId())
                        .rate(propertyNeighbourhoodReviewDTO
                                .getRate())
                        .description(propertyNeighbourhoodReviewDTO
                                .getDescription())
                        .noiseLevelRate(propertyNeighbourhoodReviewDTO
                                .getNoiseLevelRate())
                        .qualityLevelRate(propertyNeighbourhoodReviewDTO
                                .getQualityLevelRate())
                        .safetyLevelRate(propertyNeighbourhoodReviewDTO
                                .getSafetyLevelRate())
                        .build();

        PropertyNeighbourhoodReviewResponseDTO propertyNeighbourhoodReviewResponseDTO =
                PropertyNeighbourhoodReviewResponseDTO.builder()
                        .reviewerId(propertyNeighbourhoodReview
                                .getReviewerId())
                        .rate(propertyNeighbourhoodReview
                                .getRate())
                        .description(propertyNeighbourhoodReview
                                .getDescription())
                        .noiseLevelRate(propertyNeighbourhoodReview
                                .getNoiseLevelRate())
                        .qualityLevelRate(propertyNeighbourhoodReview
                                .getQualityLevelRate())
                        .safetyLevelRate(propertyNeighbourhoodReview
                                .getSafetyLevelRate())
                        .build();

        when(propertyNeighbourhoodRepository
                .findById(id))
                .thenReturn(Optional.of(PropertyNeighbourhood.builder().build()));

        when(propertyNeighbourhoodReviewRepository.save(any(PropertyNeighbourhoodReview
                .class))).thenReturn(propertyNeighbourhoodReview);

        when(propertyNeighbourhoodReviewMapper
                .propertyNeighbourhoodReviewToPropertyNeighbourhoodReviewResponseDTO(
                        propertyNeighbourhoodReview
                )).thenReturn(propertyNeighbourhoodReviewResponseDTO);

        PropertyNeighbourhoodReviewResponseDTO createdPropertyNeighbourhoodReview =
                propertyNeighbourhoodReviewService
                        .createPropertyNeighbourhoodReview(id, propertyNeighbourhoodReviewDTO);

        verify(propertyNeighbourhoodRepository)
                .findById(id);

        assertNotNull(createdPropertyNeighbourhoodReview);

        assertEquals(createdPropertyNeighbourhoodReview.getReviewerId(),
                propertyNeighbourhoodReviewDTO.getReviewerId());
        assertEquals(createdPropertyNeighbourhoodReview.getRate(),
                propertyNeighbourhoodReviewDTO.getRate());
        assertEquals(createdPropertyNeighbourhoodReview.getDescription(),
                propertyNeighbourhoodReviewDTO.getDescription());
        assertEquals(createdPropertyNeighbourhoodReview.getNoiseLevelRate(),
                propertyNeighbourhoodReviewDTO.getNoiseLevelRate());
        assertEquals(createdPropertyNeighbourhoodReview.getQualityLevelRate(),
                propertyNeighbourhoodReviewDTO.getQualityLevelRate());
        assertEquals(createdPropertyNeighbourhoodReview.getSafetyLevelRate(),
                propertyNeighbourhoodReviewDTO.getSafetyLevelRate());
    }

    @Test
    void createPropertyNeighbourhoodReview_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                propertyNeighbourhoodReviewService.createPropertyNeighbourhoodReview(
                        UUID.randomUUID(),
                        PropertyNeighbourhoodReviewDTO.builder()
                                .build())
        );
    }

    @Test
    void updatePropertyNeighbourhoodReview() {
        UUID id = UUID.randomUUID();

        PropertyNeighbourhoodReviewDTO propertyNeighbourhoodReviewDTO =
                PropertyNeighbourhoodReviewDTO.builder()
                        .rate(7)
                        .description("Description")
                        .noiseLevelRate(8)
                        .qualityLevelRate(9)
                        .safetyLevelRate(10)
                        .build();

        PropertyNeighbourhoodReview propertyNeighbourhoodReview =
                PropertyNeighbourhoodReview.builder()
                        .rate(9)
                        .description("Description")
                        .noiseLevelRate(8)
                        .qualityLevelRate(10)
                        .safetyLevelRate(10)
                        .build();

        when(propertyNeighbourhoodReviewRepository.findById(id))
                .thenReturn(Optional.of(propertyNeighbourhoodReview));

        propertyNeighbourhoodReviewService.updatePropertyNeighbourhoodReview(id,
                propertyNeighbourhoodReviewDTO);

        verify(propertyNeighbourhoodReviewRepository).findById(id);

        assertEquals(propertyNeighbourhoodReview.getRate(),
                propertyNeighbourhoodReviewDTO.getRate());
        assertEquals(propertyNeighbourhoodReview.getDescription(),
                "Description");
        assertEquals(propertyNeighbourhoodReview.getNoiseLevelRate(),
                8);
        assertEquals(propertyNeighbourhoodReview.getQualityLevelRate(),
                propertyNeighbourhoodReviewDTO.getQualityLevelRate());
        assertEquals(propertyNeighbourhoodReview.getSafetyLevelRate(),
                10);
    }

    @Test
    void updatePropertyNeighbourhoodReview_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyNeighbourhoodReviewService.updatePropertyNeighbourhoodReview(id,
                        PropertyNeighbourhoodReviewDTO.builder().build()));
    }

    @Test
    void patchPropertyNeighbourhoodReview() {
        UUID id = UUID.randomUUID();

        PropertyNeighbourhoodReviewPatchDTO propertyNeighbourhoodReviewPatchDTO =
                PropertyNeighbourhoodReviewPatchDTO.builder()
                        .rate(7)
                        .qualityLevelRate(9)
                        .build();

        PropertyNeighbourhoodReview propertyNeighbourhoodReview =
                PropertyNeighbourhoodReview.builder()
                        .rate(9)
                        .description("Description")
                        .noiseLevelRate(8)
                        .qualityLevelRate(10)
                        .safetyLevelRate(10)
                        .build();

        when(propertyNeighbourhoodReviewRepository.findById(id))
                .thenReturn(Optional.of(propertyNeighbourhoodReview));

        propertyNeighbourhoodReviewService.patchPropertyNeighbourhoodReview(id,
                propertyNeighbourhoodReviewPatchDTO);

        verify(propertyNeighbourhoodReviewRepository).findById(id);

        assertEquals(propertyNeighbourhoodReview.getRate(),
                propertyNeighbourhoodReviewPatchDTO.getRate());
        assertEquals(propertyNeighbourhoodReview.getDescription(),
                "Description");
        assertEquals(propertyNeighbourhoodReview.getNoiseLevelRate(),
                8);
        assertEquals(propertyNeighbourhoodReview.getQualityLevelRate(),
                propertyNeighbourhoodReviewPatchDTO.getQualityLevelRate());
        assertEquals(propertyNeighbourhoodReview.getSafetyLevelRate(),
                10);
    }

    @Test
    void patchPropertyNeighbourhoodReview_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyNeighbourhoodReviewService.patchPropertyNeighbourhoodReview(id,
                        PropertyNeighbourhoodReviewPatchDTO.builder().build()));
    }

    @Test
    void deletePropertyNeighbourhoodReview() {
        UUID id = UUID.randomUUID();

        when(propertyNeighbourhoodReviewRepository.findById(id))
                .thenReturn(Optional.of(PropertyNeighbourhoodReview.builder().build()));

        propertyNeighbourhoodReviewService.deletePropertyNeighbourhoodReview(id);

        verify(propertyNeighbourhoodReviewRepository).findById(id);
    }

    @Test
    void deletePropertyNeighbourhoodReview_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyNeighbourhoodReviewService.deletePropertyNeighbourhoodReview(id));
    }
}