package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.property.propertyReview.PropertyReviewDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyReview.PropertyReviewPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyReview.PropertyReviewResponseDTO;
import cloud.uwu.realestatebackend.entities.property.Property;
import cloud.uwu.realestatebackend.entities.property.PropertyReview;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.property.PropertyReviewMapper;
import cloud.uwu.realestatebackend.repositories.property.PropertyRepository;
import cloud.uwu.realestatebackend.repositories.property.PropertyReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyReviewService {
    private final PropertyReviewRepository propertyReviewRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyReviewMapper propertyReviewMapper;

    public List<PropertyReviewResponseDTO> getPropertyReviewsByPropertyId(UUID id) {
        getProperty(id);

        return propertyReviewRepository.getPropertyReviewsByPropertyId(id)
                .stream().map(propertyReviewMapper::propertyReviewToPropertyReviewResponseDTO)
                .toList();
    }

    public PropertyReviewResponseDTO getPropertyReviewById(UUID id) {
        return propertyReviewMapper
                .propertyReviewToPropertyReviewResponseDTO(getPropertyReview(id));
    }

    public PropertyReviewResponseDTO createPropertyReview(PropertyReviewDTO propertyReviewDTO) {
        if (propertyReviewDTO.getPropertyId() == null) {
            throw new NullException("property_id is null");
        }

        Property property = getProperty(propertyReviewDTO.getPropertyId());

        PropertyReview propertyReview = PropertyReview.builder()
                .property(property)
                .evaluatorId(propertyReviewDTO.getEvaluatorId())
                .rate(propertyReviewDTO.getRate())
                .description(propertyReviewDTO.getDescription())
                .build();

        PropertyReview savedPropertyReview = propertyReviewRepository
                .save(propertyReview);

        return propertyReviewMapper
                .propertyReviewToPropertyReviewResponseDTO(savedPropertyReview);
    }

    public void updatePropertyReview(
            UUID id, PropertyReviewDTO propertyReviewDTO
    ) {
        PropertyReview propertyReview = getPropertyReview(id);

        if (propertyReviewDTO.getRate() != null) {
            propertyReview.setRate(propertyReviewDTO.getRate());
        } else {
            throw new NullException("rate is null");
        }

        if (propertyReviewDTO.getDescription() != null) {
            propertyReview.setDescription(propertyReviewDTO.getDescription());
        } else {
            throw new NullException("description is null");
        }

        propertyReviewRepository.save(propertyReview);
    }

    public void patchPropertyReview(
            UUID id, PropertyReviewPatchDTO propertyReviewPatchDTO
    ) {
        PropertyReview propertyReview = getPropertyReview(id);

        if (propertyReviewPatchDTO.getRate() != null) {
            propertyReview.setRate(propertyReviewPatchDTO.getRate());
        }

        if (propertyReviewPatchDTO.getDescription() != null) {
            propertyReview.setDescription(propertyReviewPatchDTO.getDescription());
        }
        propertyReviewRepository.save(propertyReview);
    }

    public void deletePropertyReview(UUID id) {
        getPropertyReview(id);

        propertyReviewRepository.deleteById(id);
    }

    //

    private PropertyReview getPropertyReview(UUID id) {
        return propertyReviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyReview not found"));
    }

    private Property getProperty(UUID id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Property not found"));
    }
}
