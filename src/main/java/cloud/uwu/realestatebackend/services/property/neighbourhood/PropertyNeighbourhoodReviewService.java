package cloud.uwu.realestatebackend.services.property.neighbourhood;

import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview.PropertyNeighbourhoodReviewDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview.PropertyNeighbourhoodReviewPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview.PropertyNeighbourhoodReviewResponseDTO;
import cloud.uwu.realestatebackend.entities.property.neighbourhood.PropertyNeighbourhood;
import cloud.uwu.realestatebackend.entities.property.neighbourhood.PropertyNeighbourhoodReview;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.property.neighbourhood.PropertyNeighbourhoodReviewMapper;
import cloud.uwu.realestatebackend.repositories.property.neighbourhood.PropertyNeighbourhoodRepository;
import cloud.uwu.realestatebackend.repositories.property.neighbourhood.PropertyNeighbourhoodReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyNeighbourhoodReviewService {
    private final PropertyNeighbourhoodReviewRepository propertyNeighbourhoodReviewRepository;
    private final PropertyNeighbourhoodRepository propertyNeighbourhoodRepository;
    private final PropertyNeighbourhoodReviewMapper propertyNeighbourhoodReviewMapper;

    public PropertyNeighbourhoodReviewResponseDTO getPropertyNeighbourhoodReviewById(UUID id) {
        return propertyNeighbourhoodReviewMapper
                .propertyNeighbourhoodReviewToPropertyNeighbourhoodReviewResponseDTO(getPropertyNeighbourhoodReview(id));
    }

    public Page<PropertyNeighbourhoodReviewResponseDTO> getPropertyNeighbourhoodReviewsByNeighbourhoodId(
            UUID id, Integer page, Integer size
    ) {
        getPropertyNeighbourhood(id);

        page = (page != null) ? Math.max(page, 0) : 0;
        size = (size != null && size > 0) ? size : 50;

        PageRequest pageable = PageRequest.of(page, size);

        return propertyNeighbourhoodReviewRepository
                .getPropertyNeighbourhoodReviewsByNeighbourhoodId(id, pageable)
                .map(propertyNeighbourhoodReviewMapper::
                        propertyNeighbourhoodReviewToPropertyNeighbourhoodReviewResponseDTO);
    }

    public PropertyNeighbourhoodReviewResponseDTO createPropertyNeighbourhoodReview(
            UUID neighbourhoodId,
            PropertyNeighbourhoodReviewDTO propertyNeighbourhoodReviewDTO
    ) {
        PropertyNeighbourhood propertyNeighbourhood =
                getPropertyNeighbourhood(neighbourhoodId);

        PropertyNeighbourhoodReview propertyNeighbourhoodReview = PropertyNeighbourhoodReview
                .builder()
                .neighbourhood(propertyNeighbourhood)
                .reviewerId(propertyNeighbourhoodReviewDTO.getReviewerId())
                .rate(propertyNeighbourhoodReviewDTO.getRate())
                .description(propertyNeighbourhoodReviewDTO.getDescription())
                .noiseLevelRate(propertyNeighbourhoodReviewDTO.getNoiseLevelRate())
                .qualityLevelRate(propertyNeighbourhoodReviewDTO.getQualityLevelRate())
                .safetyLevelRate(propertyNeighbourhoodReviewDTO.getSafetyLevelRate())
                .build();

        PropertyNeighbourhoodReview savedPropertyNeighbourhoodReview =
                propertyNeighbourhoodReviewRepository.save(propertyNeighbourhoodReview);

        return propertyNeighbourhoodReviewMapper
                .propertyNeighbourhoodReviewToPropertyNeighbourhoodReviewResponseDTO(savedPropertyNeighbourhoodReview);
    }

    public void updatePropertyNeighbourhoodReview(
            UUID id, PropertyNeighbourhoodReviewDTO propertyNeighbourhoodReviewDTO
    ) {
        PropertyNeighbourhoodReview propertyNeighbourhoodReview = getPropertyNeighbourhoodReview(id);

        if (propertyNeighbourhoodReviewDTO.getRate() != null) {
            propertyNeighbourhoodReview.setRate(propertyNeighbourhoodReviewDTO.getRate());
        } else {
            throw new NullException("rate is null");
        }

        if (propertyNeighbourhoodReviewDTO.getDescription() != null) {
            propertyNeighbourhoodReview.setDescription(propertyNeighbourhoodReviewDTO
                    .getDescription());
        } else {
            throw new NullException("description is null");
        }

        if (propertyNeighbourhoodReviewDTO.getNoiseLevelRate() != null) {
            propertyNeighbourhoodReview.setNoiseLevelRate(propertyNeighbourhoodReviewDTO
                    .getNoiseLevelRate());
        } else {
            throw new NullException("noise_level_rate is null");
        }

        if (propertyNeighbourhoodReviewDTO.getQualityLevelRate() != null) {
            propertyNeighbourhoodReview.setQualityLevelRate(propertyNeighbourhoodReviewDTO
                    .getQualityLevelRate());
        } else {
            throw new NullException("quality_level_rate is null");
        }

        if (propertyNeighbourhoodReviewDTO.getSafetyLevelRate() != null) {
            propertyNeighbourhoodReview.setSafetyLevelRate(propertyNeighbourhoodReviewDTO
                    .getSafetyLevelRate());
        } else {
            throw new NullException("safety_level_rate is null");
        }

        propertyNeighbourhoodReviewRepository.save(propertyNeighbourhoodReview);
    }

    public void patchPropertyNeighbourhoodReview(
            UUID id, PropertyNeighbourhoodReviewPatchDTO propertyNeighbourhoodReviewPatchDTO
    ) {
        PropertyNeighbourhoodReview propertyNeighbourhoodReview = getPropertyNeighbourhoodReview(id);

        if (propertyNeighbourhoodReviewPatchDTO.getRate() != null) {
            propertyNeighbourhoodReview.setRate(propertyNeighbourhoodReviewPatchDTO.getRate());
        }

        if (propertyNeighbourhoodReviewPatchDTO.getDescription() != null) {
            propertyNeighbourhoodReview.setDescription(propertyNeighbourhoodReviewPatchDTO
                    .getDescription());
        }

        if (propertyNeighbourhoodReviewPatchDTO.getNoiseLevelRate() != null) {
            propertyNeighbourhoodReview.setNoiseLevelRate(propertyNeighbourhoodReviewPatchDTO
                    .getNoiseLevelRate());
        }

        if (propertyNeighbourhoodReviewPatchDTO.getQualityLevelRate() != null) {
            propertyNeighbourhoodReview.setQualityLevelRate(propertyNeighbourhoodReviewPatchDTO
                    .getQualityLevelRate());
        }

        if (propertyNeighbourhoodReviewPatchDTO.getSafetyLevelRate() != null) {
            propertyNeighbourhoodReview.setSafetyLevelRate(propertyNeighbourhoodReviewPatchDTO
                    .getSafetyLevelRate());
        }

        propertyNeighbourhoodReviewRepository.save(propertyNeighbourhoodReview);
    }

    public void deletePropertyNeighbourhoodReview(UUID id) {
        getPropertyNeighbourhoodReview(id);

        propertyNeighbourhoodReviewRepository.deleteById(id);
    }
    //

    private PropertyNeighbourhoodReview getPropertyNeighbourhoodReview(UUID id) {
        return propertyNeighbourhoodReviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyNeighbourhoodReview not found"));
    }

    private PropertyNeighbourhood getPropertyNeighbourhood(UUID id) {
        return propertyNeighbourhoodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyNeighbourhood not found"));
    }
}
