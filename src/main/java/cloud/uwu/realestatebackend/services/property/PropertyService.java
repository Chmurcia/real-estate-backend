package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.other.filters.PropertyFilterDTO;
import cloud.uwu.realestatebackend.dtos.other.post.DetailsDTO;
import cloud.uwu.realestatebackend.dtos.other.post.FullPropertyDTO;
import cloud.uwu.realestatebackend.dtos.other.post.LocationDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertyDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertyPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertyResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.property.Property;
import cloud.uwu.realestatebackend.entities.property.PropertyDetails;
import cloud.uwu.realestatebackend.entities.property.location.PropertyLocation;
import cloud.uwu.realestatebackend.entities.property.multimedia.PropertyMultimedia;
import cloud.uwu.realestatebackend.entities.property.neighbourhood.PropertyNeighbourhood;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.property.PropertyMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRepository;
import cloud.uwu.realestatebackend.repositories.property.PropertyRepository;
import cloud.uwu.realestatebackend.services.property.location.PropertyLocationService;
import cloud.uwu.realestatebackend.services.property.neighbourhood.PropertyNeighbourhoodService;
import cloud.uwu.realestatebackend.services.property.statistics.PropertyStatisticsService;
import cloud.uwu.realestatebackend.sorts.PropertySort;
import cloud.uwu.realestatebackend.specifications.PropertySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final ProfileRepository profileRepository;
    private final PropertyMapper propertyMapper;

    private final PropertyNeighbourhoodService propertyNeighbourhoodService;
    private final PropertyDetailsService propertyDetailsService;
    private final PropertyLocationService propertyLocationService;
    private final PropertyStatisticsService propertyStatisticsService;

    public Page<PropertyResponseDTO> getAllProperties(
            PropertyFilterDTO filters, int page, int size,
            String sortBy, String sortDirection) {
        page = Math.max(page, 0);
        size = size > 0 ? size : 50;

        PropertySort sortField = (sortBy != null && PropertySort.isValid(sortBy))
                ? PropertySort.valueOf(sortBy.toUpperCase())
                : PropertySort.PRICE;

        String sortOrder = ("desc".equalsIgnoreCase(sortDirection) ? "desc" : "asc");

        Sort sort = ("desc".equalsIgnoreCase(sortOrder))
                ? Sort.by(Sort.Order.desc(sortField.getField()))
                : Sort.by(Sort.Order.asc(sortField.getField()));

        PageRequest pageable = PageRequest.of(page, size, sort);

        var specification = PropertySpecification.createSpecification(filters);

        return propertyRepository.findAll(specification, pageable)
                .map(propertyMapper::propertyToPropertyResponseDTO);
    }

    public Page<PropertyResponseDTO> getPropertiesByProfileId(UUID id, int page, int size) {
        getProfile(id);

        page = Math.max(page, 0);
        size = size > 0 ? size : 50;

        PageRequest pageable = PageRequest.of(page, size);


        return propertyRepository.getPropertiesByProfileId(id, pageable)
                .map(propertyMapper::propertyToPropertyResponseDTO);
    }

    public PropertyResponseDTO getPropertyById(UUID id) {
        return propertyMapper
                .propertyToPropertyResponseDTO(getProperty(id));
    }

    public PropertyResponseDTO createProperty(FullPropertyDTO fullPropertyDTO) {

        Profile profile = fetchProfile(fullPropertyDTO);

        PropertyNeighbourhood propertyNeighbourhood = createPropertyNeighbourhood(fullPropertyDTO
                .getPropertyNeighbourhoodDTO());

        PropertyDetails propertyDetails = createPropertyDetails(fullPropertyDTO.getDetailsDTO());

        PropertyLocation propertyLocation = createPropertyLocation(fullPropertyDTO.getLocationDTO());

        PropertyStatistics propertyStatistics = createPropertyStatistics(fullPropertyDTO
                .getPropertyStatisticsDTO());

        PropertyMultimedia propertyMultimedia = PropertyMultimedia.builder().build(); // Spring should take care of creating PropertyMultimedia because of CascadeType.ALL

        Property property = buildProperty(profile, fullPropertyDTO,
                propertyNeighbourhood, propertyDetails, propertyLocation,
                propertyMultimedia, propertyStatistics);

        Property savedProperty = propertyRepository.save(property);

        return propertyMapper.propertyToPropertyResponseDTO(savedProperty);
    }

    public void updateProperty(UUID id, PropertyDTO propertyDTO) {
        Property property = getProperty(id);

        if (propertyDTO.getPropertyType() != null) {
            property.setPropertyType(propertyDTO.getPropertyType());
        } else {
            throw new NullException("property_type is null");
        }

        if (propertyDTO.getOfferStatus() != null) {
            property.setOfferStatus(propertyDTO.getOfferStatus());
        } else {
            throw new NullException("offer_status is null");
        }

        if (StringUtils.hasText(propertyDTO.getTitle())) {
            property.setTitle(propertyDTO.getTitle());
        } else {
            throw new NullException("title is null");
        }

        if (StringUtils.hasText(propertyDTO.getDescription())) {
            property.setDescription(propertyDTO.getDescription());
        } else {
            throw new NullException("description is null");
        }

        if (propertyDTO.getPrice() != null) {
            property.setPrice(propertyDTO.getPrice());
        } else {
            throw new NullException("price is null");
        }

        propertyRepository.save(property);
    }

    public void patchProperty(UUID id, PropertyPatchDTO propertyPatchDTO) {
        Property property = getProperty(id);

        if (propertyPatchDTO.getPropertyType() != null) {
            property.setPropertyType(propertyPatchDTO.getPropertyType());
        }

        if (propertyPatchDTO.getOfferStatus() != null) {
            property.setOfferStatus(propertyPatchDTO.getOfferStatus());
        }

        if (StringUtils.hasText(propertyPatchDTO.getTitle())) {
            property.setTitle(propertyPatchDTO.getTitle());
        }

        if (StringUtils.hasText(propertyPatchDTO.getDescription())) {
            property.setDescription(propertyPatchDTO.getDescription());
        }

        if (propertyPatchDTO.getPrice() != null) {
            property.setPrice(propertyPatchDTO.getPrice());
        }

        propertyRepository.save(property);
    }

    public void deleteProperty(UUID id) {
        getProperty(id);

        propertyRepository.deleteById(id);
    }

    // HELPERS

    private Property getProperty(UUID id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Property not found"));
    }

    private Profile getProfile(UUID id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profile not found"));
    }

    // CREATORS

    private PropertyNeighbourhood createPropertyNeighbourhood(
            PropertyNeighbourhoodDTO propertyNeighbourhoodDTO
    ) {
        return propertyNeighbourhoodService.createPropertyNeighbourhood(propertyNeighbourhoodDTO);
    }

    private PropertyDetails createPropertyDetails(
            DetailsDTO detailsDTO
    ) {
        return propertyDetailsService.createPropertyDetails(detailsDTO);
    }

    private PropertyLocation createPropertyLocation(
            LocationDTO locationDTO
    ) {
        return propertyLocationService.createPropertyLocation(locationDTO);
    }

    private PropertyStatistics createPropertyStatistics(
            PropertyStatisticsDTO propertyStatisticsDTO
    ) {
        return propertyStatisticsService.createPropertyStatistics(propertyStatisticsDTO);
    }
    // SUB-FUNCTIONS

    private Property buildProperty(
            Profile profile,
            FullPropertyDTO fullPropertyDTO,
            PropertyNeighbourhood propertyNeighbourhood,
            PropertyDetails propertyDetails,
            PropertyLocation propertyLocation,
            PropertyMultimedia propertyMultimedia,
            PropertyStatistics propertyStatistics
    ) {
        return Property.builder()
                .profile(profile)
                .propertyType(fullPropertyDTO.getPropertyDTO().getPropertyType())
                .offerStatus(fullPropertyDTO.getPropertyDTO().getOfferStatus())
                .title(fullPropertyDTO.getPropertyDTO().getTitle())
                .description(fullPropertyDTO.getPropertyDTO().getDescription())
                .price(fullPropertyDTO.getPropertyDTO().getPrice())
                .neighbourhood(propertyNeighbourhood)
                .details(propertyDetails)
                .propertyLocation(propertyLocation)
                .propertyMultimedia(propertyMultimedia)
                .statistics(propertyStatistics)
                .build();
    }

    private Profile fetchProfile(FullPropertyDTO fullPropertyDTO) {
        if (fullPropertyDTO.getPropertyDTO().getProfileId() == null) {
            throw new NullException("profile_id is null");
        }

        return getProfile(fullPropertyDTO
                .getPropertyDTO().getProfileId());
    }
}
