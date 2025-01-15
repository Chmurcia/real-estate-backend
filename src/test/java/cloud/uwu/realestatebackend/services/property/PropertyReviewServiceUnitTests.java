package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.property.propertyReview.PropertyReviewDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyReview.PropertyReviewPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyReview.PropertyReviewResponseDTO;
import cloud.uwu.realestatebackend.entities.property.Property;
import cloud.uwu.realestatebackend.entities.property.PropertyReview;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.PropertyReviewMapper;
import cloud.uwu.realestatebackend.repositories.property.PropertyRepository;
import cloud.uwu.realestatebackend.repositories.property.PropertyReviewRepository;
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
class PropertyReviewServiceUnitTests {

    @Mock
    private PropertyReviewRepository propertyReviewRepository;

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private PropertyReviewMapper propertyReviewMapper;

    @InjectMocks
    private PropertyReviewService propertyReviewService;

    @Test
    void getPropertyReviewsByPropertyId() {
        UUID id = UUID.randomUUID();

        List<PropertyReview> propertyReviews = Arrays.asList(
                PropertyReview.builder().build(),
                PropertyReview.builder().build()
        );

        when(propertyRepository.findById(id))
                .thenReturn(Optional.of(Property.builder().build()));

        when(propertyReviewRepository
                .getPropertyReviewsByPropertyId(id))
                .thenReturn(propertyReviews);

        when(propertyReviewMapper
                .propertyReviewToPropertyReviewResponseDTO(any(PropertyReview.class)))
                .thenReturn(PropertyReviewResponseDTO.builder().build());

        List<PropertyReviewResponseDTO> foundPropertyReview = propertyReviewService
                .getPropertyReviewsByPropertyId(id);

        verify(propertyRepository).findById(id);

        assertNotNull(foundPropertyReview);
        assertEquals(foundPropertyReview.size(),
                propertyReviews.size());
    }

    @Test
    void getPropertyReviewByPropertyId_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyReviewService.getPropertyReviewsByPropertyId(id));
    }

    @Test
    void getPropertyReviewById() {
        UUID id = UUID.randomUUID();

        PropertyReview propertyReview = PropertyReview.builder()
                .evaluatorId(UUID.randomUUID())
                .rate(9.4)
                .description("Description")
                .build();

        PropertyReviewResponseDTO propertyReviewResponseDTO =
                PropertyReviewResponseDTO.builder()
                .evaluatorId(UUID.randomUUID())
                .rate(9.4)
                .description("Description")
                .build();

        when(propertyReviewRepository.findById(id))
                .thenReturn(Optional.of(propertyReview));

        when(propertyReviewMapper
                .propertyReviewToPropertyReviewResponseDTO(propertyReview))
                .thenReturn(propertyReviewResponseDTO);

        PropertyReviewResponseDTO foundPropertyReview =
                propertyReviewService.getPropertyReviewById(id);

        verify(propertyReviewRepository).findById(id);

        assertNotNull(foundPropertyReview);
    }

    @Test
    void getPropertyReviewById_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyReviewService.getPropertyReviewById(id));
    }

    @Test
    void createPropertyReview() {
        UUID id = UUID.randomUUID();

        PropertyReviewDTO propertyReviewDTO = PropertyReviewDTO.builder()
                .evaluatorId(UUID.randomUUID())
                .rate(9.4)
                .description("Description")
                .build();

        PropertyReview propertyReview = PropertyReview.builder()
                .evaluatorId(propertyReviewDTO.getEvaluatorId())
                .rate(9.4)
                .description("Description")
                .build();

        PropertyReviewResponseDTO propertyReviewResponseDTO = PropertyReviewResponseDTO
                .builder()
                .evaluatorId(propertyReview.getEvaluatorId())
                .rate(9.4)
                .description("Description")
                .build();

        when(propertyRepository.findById(id))
                .thenReturn(Optional.of(Property.builder().build()));

        when(propertyReviewRepository.saveAndFlush(any(PropertyReview.class)))
                .thenReturn(propertyReview);

        when(propertyReviewMapper
                .propertyReviewToPropertyReviewResponseDTO(propertyReview))
                .thenReturn(propertyReviewResponseDTO);

        PropertyReviewResponseDTO createdPropertyReview = propertyReviewService
                .createPropertyReview(id, propertyReviewDTO);

        verify(propertyRepository).findById(id);

        assertNotNull(createdPropertyReview);
        assertEquals(createdPropertyReview.getEvaluatorId(),
                propertyReviewDTO.getEvaluatorId());
        assertEquals(createdPropertyReview.getRate(),
                propertyReviewDTO.getRate());
        assertEquals(createdPropertyReview.getDescription(),
                propertyReviewDTO.getDescription());

    }

    @Test
    void createPropertyReview_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                propertyReviewService
                        .createPropertyReview(UUID.randomUUID(),
                                PropertyReviewDTO.builder().build()));
    }

    @Test
    void updatePropertyReview() {
        UUID id = UUID.randomUUID();

        PropertyReviewDTO propertyReviewDTO = PropertyReviewDTO.builder()
                .rate(9.9)
                .description("Description 2")
                .build();

        PropertyReview propertyReview = PropertyReview.builder()
                .rate(9.4)
                .description("Description")
                .build();

        when(propertyReviewRepository.findById(id))
                .thenReturn(Optional.of(propertyReview));

        propertyReviewService.updatePropertyReview(id, propertyReviewDTO);

        verify(propertyReviewRepository).findById(id);

        assertEquals(propertyReview.getRate(),
                propertyReviewDTO.getRate());
        assertEquals(propertyReview.getDescription(),
                propertyReviewDTO.getDescription());
    }

    @Test
    void updatePropertyReview_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyReviewService
                        .updatePropertyReview(id,
                                PropertyReviewDTO.builder()
                                        .build()));
    }

    @Test
    void patchPropertyReview() {
        UUID id = UUID.randomUUID();

        PropertyReviewPatchDTO propertyReviewPatchDTO = PropertyReviewPatchDTO.builder()
                .rate(9.9)
                .description("Description 2")
                .build();

        PropertyReview propertyReview = PropertyReview.builder()
                .rate(9.4)
                .description("Description")
                .build();

        when(propertyReviewRepository.findById(id))
                .thenReturn(Optional.of(propertyReview));

        propertyReviewService.patchPropertyReview(id, propertyReviewPatchDTO);

        verify(propertyReviewRepository).findById(id);

        assertEquals(propertyReview.getRate(),
                propertyReviewPatchDTO.getRate());
        assertEquals(propertyReview.getDescription(),
                propertyReviewPatchDTO.getDescription());
    }

    @Test
    void patchPropertyReview_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyReviewService
                        .patchPropertyReview(id,
                                PropertyReviewPatchDTO.builder()
                                        .build()));
    }

    @Test
    void deletePropertyReview() {
        UUID id = UUID.randomUUID();

        when(propertyReviewRepository.findById(id))
                .thenReturn(Optional.of(PropertyReview.builder().build()));

        propertyReviewService.deletePropertyReview(id);

        verify(propertyReviewRepository).deleteById(id);
    }

    @Test
    void deletePropertyReview_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyReviewService.deletePropertyReview(id));
    }
}