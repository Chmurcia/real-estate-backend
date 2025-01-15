package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.other.post.DetailsDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.PropertyAreas;
import cloud.uwu.realestatebackend.entities.property.PropertyDetails;
import cloud.uwu.realestatebackend.entities.property.PropertyRooms;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.property.PropertyDetailsMapper;
import cloud.uwu.realestatebackend.repositories.property.PropertyDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyDetailsService {
    private final PropertyDetailsRepository propertyDetailsRepository;
    private final PropertyDetailsMapper propertyDetailsMapper;
    private final PropertyAreasService propertyAreasService;
    private final PropertyRoomsService propertyRoomsService;

    public PropertyDetailsResponseDTO getPropertyDetailsById(UUID id) {
        return propertyDetailsMapper
                .propertyDetailsToPropertyDetailsResponseDTO(getPropertyDetails(id));
    }

    public PropertyDetails createPropertyDetails(
            DetailsDTO detailsDTO
    ) {
        PropertyAreas propertyAreas = propertyAreasService.createPropertyAreas(detailsDTO.getPropertyAreasDTO());
        PropertyRooms propertyRooms = propertyRoomsService.createPropertyRooms(detailsDTO.getPropertyRoomsDTO());

        PropertyDetails propertyDetails = PropertyDetails.builder()
                .totalFloors(detailsDTO.getPropertyDetailsDTO().getTotalFloors())
                .yearBuilt(detailsDTO.getPropertyDetailsDTO().getYearBuilt())
                .conditionStatus(detailsDTO.getPropertyDetailsDTO().getConditionStatus())
                .areas(propertyAreas)
                .rooms(propertyRooms)
                .build();

        return propertyDetailsRepository.saveAndFlush(propertyDetails);

    }

    public void updatePropertyDetails(
            UUID id, PropertyDetailsDTO propertyDetailsDTO
    ) {
        PropertyDetails propertyDetails = getPropertyDetails(id);

        if (propertyDetailsDTO.getTotalFloors() != null) {
            propertyDetails.setTotalFloors(propertyDetailsDTO.getTotalFloors());
        } else {
            throw new NullException("total_floors is null");
        }

        if (propertyDetailsDTO.getYearBuilt() != null) {
            propertyDetails.setYearBuilt(propertyDetailsDTO.getYearBuilt());
        } else {
            throw new NullException("year_built is null");
        }

        if (propertyDetailsDTO.getConditionStatus() != null) {
            propertyDetails.setConditionStatus(propertyDetailsDTO.getConditionStatus());
        } else {
            throw new NullException("condition_status is null");
        }

        propertyDetailsRepository.save(propertyDetails);
    }

    public void patchPropertyDetails(
            UUID id, PropertyDetailsPatchDTO propertyDetailsPatchDTO
    ) {
        PropertyDetails propertyDetails = getPropertyDetails(id);

        if (propertyDetailsPatchDTO.getTotalFloors() != null) {
            propertyDetails.setTotalFloors(propertyDetailsPatchDTO.getTotalFloors());
        }

        if (propertyDetailsPatchDTO.getYearBuilt() != null) {
            propertyDetails.setYearBuilt(propertyDetailsPatchDTO.getYearBuilt());
        }

        if (propertyDetailsPatchDTO.getConditionStatus() != null) {
            propertyDetails.setConditionStatus(propertyDetailsPatchDTO.getConditionStatus());
        }

        propertyDetailsRepository.save(propertyDetails);
    }

    public void deletePropertyDetails(UUID id) {
        getPropertyDetails(id);

        propertyDetailsRepository.deleteById(id);
    }

    //

    private PropertyDetails getPropertyDetails(UUID id) {
        return propertyDetailsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyDetails not found"));
    }
}
