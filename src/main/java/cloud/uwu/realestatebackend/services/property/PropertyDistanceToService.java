package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToResponseDTO;
import cloud.uwu.realestatebackend.entities.property.Property;
import cloud.uwu.realestatebackend.entities.property.PropertyDistanceTo;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.property.PropertyDistanceToMapper;
import cloud.uwu.realestatebackend.repositories.property.PropertyDistanceToRepository;
import cloud.uwu.realestatebackend.repositories.property.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyDistanceToService {
    private final PropertyDistanceToRepository propertyDistanceToRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyDistanceToMapper propertyDistanceToMapper;

    public List<PropertyDistanceToResponseDTO> getPropertyDistancesToByPropertyId(UUID id) {
        getProperty(id);

        return propertyDistanceToRepository
                .getPropertyDistancesToByPropertyId(id)
                .stream()
                .map(propertyDistanceToMapper::propertyDistanceToToPropertyDistanceToResponseDTO)
                .toList();
    }

    public PropertyDistanceToResponseDTO getPropertyDistanceToById(UUID id) {
        return propertyDistanceToMapper
                .propertyDistanceToToPropertyDistanceToResponseDTO(getPropertyDistanceTo(id));
    }

    public PropertyDistanceToResponseDTO createPropertyDistances(
            PropertyDistanceToDTO propertyDistanceToDTO
    ) {
        if (propertyDistanceToDTO.getPropertyId() == null) {
            throw new NullException("property_id is null");
        }

        Property property = getProperty(propertyDistanceToDTO
                .getPropertyId());

        PropertyDistanceTo propertyDistanceTo = PropertyDistanceTo
                .builder()
                .property(property)
                .distance(propertyDistanceToDTO.getDistance())
                .destination(propertyDistanceToDTO.getDestination())
                .build();

        PropertyDistanceTo savedPropertyDistanceTo = propertyDistanceToRepository
                .save(propertyDistanceTo);

        return propertyDistanceToMapper
                .propertyDistanceToToPropertyDistanceToResponseDTO(savedPropertyDistanceTo);
    }

    public void updatePropertyDistances(
            UUID id, PropertyDistanceToDTO propertyDistanceToDTO
    ) {
        PropertyDistanceTo propertyDistanceTo = getPropertyDistanceTo(id);

        if (propertyDistanceToDTO.getDistance() != null) {
            propertyDistanceTo.setDistance(propertyDistanceToDTO.getDistance());
        } else {
            throw new NullException("distance is null");
        }

        if (propertyDistanceToDTO.getDestination() != null) {
            propertyDistanceTo.setDestination(propertyDistanceToDTO.getDestination());
        } else {
            throw new NullException("destination is null");
        }

        propertyDistanceToRepository.save(propertyDistanceTo);
    }

    public void patchPropertyDistances(
            UUID id, PropertyDistanceToPatchDTO propertyDistanceToPatchDTO
    ) {
        PropertyDistanceTo propertyDistanceTo = getPropertyDistanceTo(id);

        if (propertyDistanceToPatchDTO.getDistance() != null) {
            propertyDistanceTo.setDistance(propertyDistanceToPatchDTO.getDistance());
        }

        if (propertyDistanceToPatchDTO.getDestination() != null) {
            propertyDistanceTo.setDestination(propertyDistanceToPatchDTO.getDestination());
        }

        propertyDistanceToRepository.save(propertyDistanceTo);
    }

    public void deletePropertyDistanceTo(UUID id) {
        getPropertyDistanceTo(id);

        propertyDistanceToRepository.deleteById(id);
    }

    //

    private PropertyDistanceTo getPropertyDistanceTo(UUID id) {
        return propertyDistanceToRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PropertyDistanceTo not found"));
    }

    private Property getProperty(UUID id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Property not found"));
    }
}
