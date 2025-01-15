package cloud.uwu.realestatebackend.services.property.neighbourhood;

import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodResponseDTO;
import cloud.uwu.realestatebackend.entities.property.neighbourhood.PropertyNeighbourhood;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.property.neighbourhood.PropertyNeighbourhoodMapper;
import cloud.uwu.realestatebackend.repositories.property.neighbourhood.PropertyNeighbourhoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyNeighbourhoodService {
    private final PropertyNeighbourhoodRepository propertyNeighbourhoodRepository;
    private final PropertyNeighbourhoodMapper propertyNeighbourhoodMapper;

    public PropertyNeighbourhoodResponseDTO getPropertyNeighbourhoodById(UUID id) {
        return propertyNeighbourhoodMapper
                .propertyNeighbourhoodToPropertyNeighbourhoodResponseDTO(getPropertyNeighbourhood(id));
    }

    public PropertyNeighbourhood createPropertyNeighbourhood(PropertyNeighbourhoodDTO propertyNeighbourhoodDTO) {
        PropertyNeighbourhood propertyNeighbourhood = PropertyNeighbourhood.builder()
                .noiseLevel(propertyNeighbourhoodDTO.getNoiseLevel())
                .qualityLevel(propertyNeighbourhoodDTO.getQualityLevel())
                .safetyLevel(propertyNeighbourhoodDTO.getSafetyLevel())
                .build();

        return propertyNeighbourhoodRepository.save(propertyNeighbourhood);

    }

    public void updatePropertyNeighbourhood(
            UUID id,
            PropertyNeighbourhoodDTO propertyNeighbourhoodDTO
    ) {
        PropertyNeighbourhood propertyNeighbourhood = getPropertyNeighbourhood(id);

        if (propertyNeighbourhoodDTO.getNoiseLevel() != null) {
            propertyNeighbourhood.setNoiseLevel(propertyNeighbourhoodDTO.getNoiseLevel());
        } else {
            throw new NullException("noise_level is null");
        }

        if (propertyNeighbourhoodDTO.getQualityLevel() != null) {
            propertyNeighbourhood.setQualityLevel(propertyNeighbourhoodDTO.getQualityLevel());
        } else {
            throw new NullException("quality_level is null");
        }

        if (propertyNeighbourhoodDTO.getSafetyLevel() != null) {
            propertyNeighbourhood.setSafetyLevel(propertyNeighbourhoodDTO.getSafetyLevel());
        } else {
            throw new NullException("safety_level is null");
        }

        propertyNeighbourhoodRepository.save(propertyNeighbourhood);
    }

    public void patchPropertyNeighbourhood(
            UUID id,
            PropertyNeighbourhoodPatchDTO propertyNeighbourhoodPatchDTO
    ) {
        PropertyNeighbourhood propertyNeighbourhood = getPropertyNeighbourhood(id);

        if (propertyNeighbourhoodPatchDTO.getNoiseLevel() != null) {
            propertyNeighbourhood.setNoiseLevel(propertyNeighbourhoodPatchDTO.getNoiseLevel());
        }

        if (propertyNeighbourhoodPatchDTO.getQualityLevel() != null) {
            propertyNeighbourhood.setQualityLevel(propertyNeighbourhoodPatchDTO.getQualityLevel());
        }

        if (propertyNeighbourhoodPatchDTO.getSafetyLevel() != null) {
            propertyNeighbourhood.setSafetyLevel(propertyNeighbourhoodPatchDTO.getSafetyLevel());
        }

        propertyNeighbourhoodRepository.save(propertyNeighbourhood);
    }

    public void deletePropertyNeighbourhood(UUID id) {
        getPropertyNeighbourhood(id);

        propertyNeighbourhoodRepository.deleteById(id);
    }

    //

    private PropertyNeighbourhood getPropertyNeighbourhood(UUID id) {
        return propertyNeighbourhoodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyNeighbourhood not found"));
    }
}
