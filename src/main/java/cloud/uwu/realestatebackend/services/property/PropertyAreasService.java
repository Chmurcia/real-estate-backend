package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyAreas;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.property.PropertyAreasMapper;
import cloud.uwu.realestatebackend.repositories.property.PropertyAreasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyAreasService {
    private final PropertyAreasRepository propertyAreasRepository;
    private final PropertyAreasMapper propertyAreasMapper;


    public PropertyAreasResponseDTO getPropertyAreasById(UUID id) {
        return propertyAreasMapper
                .propertyAreasToPropertyAreasResponseDTO(getPropertyAreas(id));
    }

    public PropertyAreas createPropertyAreas(PropertyAreasDTO propertyAreasDTO) {
        PropertyAreas propertyAreas = PropertyAreas.builder()
                .totalArea(propertyAreasDTO.getTotalArea())
                .buildingArea(propertyAreasDTO.getBuildingArea())
                .livingArea(propertyAreasDTO.getLivingArea())
                .gardenArea(propertyAreasDTO.getGardenArea())
                .garageArea(propertyAreasDTO.getGarageArea())
                .basementArea(propertyAreasDTO.getBasementArea())
                .atticArea(propertyAreasDTO.getAtticArea())
                .poolArea(propertyAreasDTO.getPoolArea())
                .build();

        return propertyAreasRepository.saveAndFlush(propertyAreas);
    }

    public void updatePropertyAreas(UUID id, PropertyAreasDTO propertyAreasDTO) {
        PropertyAreas propertyAreas = getPropertyAreas(id);

        if (propertyAreasDTO.getTotalArea() != null) {
            propertyAreas.setTotalArea(propertyAreasDTO.getTotalArea());
        } else {
            throw new NullException("total_area is null");
        }

        if (propertyAreasDTO.getBuildingArea() != null) {
            propertyAreas.setBuildingArea(propertyAreasDTO.getBuildingArea());
        } else {
            throw new NullException("building_area is null");
        }

        if (propertyAreasDTO.getLivingArea() != null) {
            propertyAreas.setLivingArea(propertyAreasDTO.getLivingArea());
        } else {
            throw new NullException("living_area is null");
        }

        if (propertyAreasDTO.getGardenArea() != null) {
            propertyAreas.setGardenArea(propertyAreasDTO.getGardenArea());
        } else {
            throw new NullException("garden_area is null");
        }

        if (propertyAreasDTO.getGarageArea() != null) {
            propertyAreas.setGarageArea(propertyAreasDTO.getGarageArea());
        } else {
            throw new NullException("garage_area is null");
        }

        if (propertyAreasDTO.getBasementArea() != null) {
            propertyAreas.setBasementArea(propertyAreasDTO.getBasementArea());
        } else {
            throw new NullException("basement_area is null");
        }

        if (propertyAreasDTO.getAtticArea() != null) {
            propertyAreas.setAtticArea(propertyAreasDTO.getAtticArea());
        } else {
            throw new NullException("attic_area is null");
        }

        if (propertyAreasDTO.getPoolArea() != null) {
            propertyAreas.setPoolArea(propertyAreasDTO.getPoolArea());
        } else {
            throw new NullException("pool_area is null");
        }

        propertyAreasRepository.save(propertyAreas);
    }

    public void patchPropertyAreas(
            UUID id, PropertyAreasPatchDTO propertyAreasPatchDTO
    ) {
        PropertyAreas propertyAreas = getPropertyAreas(id);

        if (propertyAreasPatchDTO.getTotalArea() != null) {
            propertyAreas.setTotalArea(propertyAreasPatchDTO.getTotalArea());
        }

        if (propertyAreasPatchDTO.getBuildingArea() != null) {
            propertyAreas.setBuildingArea(propertyAreasPatchDTO.getBuildingArea());
        }

        if (propertyAreasPatchDTO.getLivingArea() != null) {
            propertyAreas.setLivingArea(propertyAreasPatchDTO.getLivingArea());
        }

        if (propertyAreasPatchDTO.getGardenArea() != null) {
            propertyAreas.setGardenArea(propertyAreasPatchDTO.getGardenArea());
        }

        if (propertyAreasPatchDTO.getGarageArea() != null) {
            propertyAreas.setGarageArea(propertyAreasPatchDTO.getGarageArea());
        }

        if (propertyAreasPatchDTO.getBasementArea() != null) {
            propertyAreas.setBasementArea(propertyAreasPatchDTO.getBasementArea());
        }

        if (propertyAreasPatchDTO.getAtticArea() != null) {
            propertyAreas.setAtticArea(propertyAreasPatchDTO.getAtticArea());
        }

        if (propertyAreasPatchDTO.getPoolArea() != null) {
            propertyAreas.setPoolArea(propertyAreasPatchDTO.getPoolArea());
        }

        propertyAreasRepository.save(propertyAreas);
    }

    public void deletePropertyAreas(UUID id) {
        getPropertyAreas(id);

        propertyAreasRepository.deleteById(id);
    }

    //

    private PropertyAreas getPropertyAreas(UUID id) {
        return propertyAreasRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyAreas not found"));
    }
}
