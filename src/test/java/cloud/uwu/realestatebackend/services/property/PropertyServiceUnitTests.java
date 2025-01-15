package cloud.uwu.realestatebackend.services.property;

import cloud.uwu.realestatebackend.dtos.other.filters.PropertyFilterDTO;
import cloud.uwu.realestatebackend.dtos.other.post.DetailsDTO;
import cloud.uwu.realestatebackend.dtos.other.post.FullPropertyDTO;
import cloud.uwu.realestatebackend.dtos.other.post.LocationDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertyDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertyPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertyResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertySmallResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.property.Property;
import cloud.uwu.realestatebackend.entities.property.PropertyDetails;
import cloud.uwu.realestatebackend.entities.property.location.PropertyLocation;
import cloud.uwu.realestatebackend.entities.property.neighbourhood.PropertyNeighbourhood;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.OfferStatus;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.PropertyType;
import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.property.PropertyMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRepository;
import cloud.uwu.realestatebackend.repositories.property.PropertyRepository;
import cloud.uwu.realestatebackend.services.property.location.PropertyLocationService;
import cloud.uwu.realestatebackend.services.property.neighbourhood.PropertyNeighbourhoodService;
import cloud.uwu.realestatebackend.services.property.statistics.PropertyStatisticsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyServiceUnitTests {
    @Mock
    private PropertyRepository propertyRepository;
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private PropertyMapper propertyMapper;
    @Mock
    private PropertyNeighbourhoodService propertyNeighbourhoodService;
    @Mock
    private PropertyDetailsService propertyDetailsService;
    @Mock
    private PropertyLocationService propertyLocationService;
    @Mock
    private PropertyStatisticsService propertyStatisticsService;

    @InjectMocks
    private PropertyService propertyService;

    @Test
    void getAllProperties() {
        int page = 0;
        int size = 100;

        Sort sort = Sort.by(Sort.Order.desc("price"));

        PageRequest pageable = PageRequest.of(page, size, sort);

        List<Property> properties = Arrays.asList(
                Property.builder().build(),
                Property.builder().build(),
                Property.builder().build()
        );

        Page<Property> propertyPage = new PageImpl<>(
                properties, pageable, properties.size()
        );

        PropertyFilterDTO filters = PropertyFilterDTO.builder().build();

        when(propertyRepository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(propertyPage);

        when(propertyMapper
                .propertyToPropertyResponseDTO(any(Property.class)))
                .thenReturn(PropertyResponseDTO.builder().build());

        Page<PropertySmallResponseDTO> foundProperties = propertyService
                .getAllProperties(filters, page, size, "price", "desc");

        assertNotNull(foundProperties);
        assertEquals(properties.size(), foundProperties.getTotalElements());
    }

    @Test
    void getPropertiesByProfileId() {
        UUID id = UUID.randomUUID();

        int page = 0;
        int size = 10;

        PageRequest pageable = PageRequest.of(page, size);

        List<Property> properties = Arrays.asList(
                Property.builder().build(),
                Property.builder().build(),
                Property.builder().build()
        );

        Page<Property> propertyPage = new PageImpl<>(
                properties, pageable, properties.size()
        );

        when(profileRepository.findById(id))
                .thenReturn(Optional.of(Profile.builder().build()));

        when(propertyRepository
                .getPropertiesByProfileId(id, pageable))
                .thenReturn(propertyPage);

        when(propertyMapper
                .propertyToPropertyResponseDTO(any(Property.class)))
                .thenReturn(PropertyResponseDTO.builder().build());

        Page<PropertyResponseDTO> foundProperties = propertyService
                .getPropertiesByProfileId(id, page, size);

        verify(profileRepository).findById(id);

        assertNotNull(foundProperties);
        assertEquals(foundProperties.getTotalElements(),
                propertyPage.getTotalElements());
    }

    @Test
    void getPropertiesByProfileId_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyService.getPropertiesByProfileId(id, 0, 50));
    }

    @Test
    void getPropertyById() {
        UUID id = UUID.randomUUID();

        Property property = Property.builder()
                .title("Title")
                .description("Description")
                .price(72000.00)
                .propertyType(PropertyType.RETAIL)
                .offerStatus(OfferStatus.AVAILABLE)
                .build();

        PropertyResponseDTO propertyResponseDTO = PropertyResponseDTO
                .builder()
                .title("Title")
                .description("Description")
                .price(72000.00)
                .propertyType(PropertyType.RETAIL)
                .offerStatus(OfferStatus.AVAILABLE)
                .build();

        when(propertyRepository.findById(id))
                .thenReturn(Optional.of(property));

        when(propertyMapper
                .propertyToPropertyResponseDTO(property))
                .thenReturn(propertyResponseDTO);

        PropertyResponseDTO foundProperty = propertyService
                .getPropertyById(id);

        assertNotNull(foundProperty);
    }

    @Test
    void getPropertyById_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyService.getPropertyById(id));
    }

    @Test
    void createProperty() {
        UUID id = UUID.randomUUID();

        PropertyDTO propertyDTO = PropertyDTO.builder()
                .propertyType(PropertyType.CABIN)
                .offerStatus(OfferStatus.UNDER_OFFER)
                .title("Title")
                .description("Description")
                .price(170000.00)
                .build();

        FullPropertyDTO fullPropertyDTO = FullPropertyDTO.builder()
                .propertyDTO(propertyDTO)
                .propertyNeighbourhoodDTO(PropertyNeighbourhoodDTO.builder().build())
                .detailsDTO(DetailsDTO.builder().build())
                .locationDTO(LocationDTO.builder().build())
                .propertyStatisticsDTO(PropertyStatisticsDTO.builder().build())
                .build();

        Property property = Property.builder()
                .propertyType(propertyDTO.getPropertyType())
                .offerStatus(propertyDTO.getOfferStatus())
                .title(propertyDTO.getTitle())
                .description(propertyDTO.getDescription())
                .price(propertyDTO.getPrice())
                .build();

        PropertyResponseDTO propertyResponseDTO = PropertyResponseDTO
                .builder()
                .propertyType(propertyDTO.getPropertyType())
                .offerStatus(propertyDTO.getOfferStatus())
                .title(propertyDTO.getTitle())
                .description(propertyDTO.getDescription())
                .price(propertyDTO.getPrice())
                .build();

        when(profileRepository.findById(id))
                .thenReturn(Optional.of(Profile.builder().build()));

        when(propertyNeighbourhoodService
                .createPropertyNeighbourhood(any(PropertyNeighbourhoodDTO
                        .class)))
                .thenReturn(PropertyNeighbourhood.builder().build());

        when(propertyDetailsService
                .createPropertyDetails(any(DetailsDTO.class)))
                .thenReturn(PropertyDetails.builder().build());

        when(propertyLocationService
                .createPropertyLocation(any(LocationDTO.class)))
                .thenReturn(PropertyLocation.builder().build());

        when(propertyStatisticsService
                .createPropertyStatistics(any(PropertyStatisticsDTO.class)))
                .thenReturn(PropertyStatistics.builder().build());

        when(propertyRepository.save(any(Property.class)))
                .thenReturn(property);

        when(propertyMapper
                .propertyToPropertyResponseDTO(property))
                .thenReturn(propertyResponseDTO);

        PropertyResponseDTO createdProperty = propertyService.
                createProperty(id, fullPropertyDTO);

        assertNotNull(createdProperty);
        assertEquals(createdProperty.getPropertyType(),
                propertyDTO.getPropertyType());
        assertEquals(createdProperty.getOfferStatus(),
                propertyDTO.getOfferStatus());
        assertEquals(createdProperty.getTitle(),
                propertyDTO.getTitle());
        assertEquals(createdProperty.getDescription(),
                propertyDTO.getDescription());
        assertEquals(createdProperty.getPrice(),
                propertyDTO.getPrice());
    }

    @Test
    void createProperty_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        FullPropertyDTO fullPropertyDTO = FullPropertyDTO
                .builder()
                .propertyDTO(PropertyDTO.builder()
                        .build())
                .build();

        assertThrows(NotFoundException.class, () ->
                propertyService.createProperty(id, fullPropertyDTO));
    }

    @Test
    void updateProperty() {
        UUID id = UUID.randomUUID();

        PropertyDTO propertyDTO = PropertyDTO.builder()
                .propertyType(PropertyType.BUNGALOW)
                .offerStatus(OfferStatus.AVAILABLE)
                .title("Title 2")
                .description("Description 2")
                .price(140000.00)
                .build();

        Property property = Property.builder()
                .propertyType(PropertyType.BUNGALOW)
                .offerStatus(OfferStatus.EXPIRED)
                .title("Title")
                .description("Description")
                .price(90000.00)
                .build();

        when(propertyRepository.findById(id))
                .thenReturn(Optional.of(property));

        propertyService.updateProperty(id, propertyDTO);

        assertEquals(property.getPropertyType(),
                propertyDTO.getPropertyType());
        assertEquals(property.getOfferStatus(),
                propertyDTO.getOfferStatus());
        assertEquals(property.getTitle(),
                propertyDTO.getTitle());
        assertEquals(property.getDescription(),
                propertyDTO.getDescription());
        assertEquals(property.getPrice(),
                propertyDTO.getPrice());
    }

    @Test
    void updateProperty_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyService.updateProperty(id,
                        PropertyDTO.builder().build()));
    }

    @Test
    void patchProperty() {
        UUID id = UUID.randomUUID();

        PropertyPatchDTO propertyPatchDTO = PropertyPatchDTO.builder()
                .propertyType(PropertyType.BUNGALOW)
                .offerStatus(OfferStatus.AVAILABLE)
                .title("Title 2")
                .build();

        Property property = Property.builder()
                .propertyType(PropertyType.BUNGALOW)
                .offerStatus(OfferStatus.EXPIRED)
                .title("Title")
                .description("Description")
                .price(90000.00)
                .build();

        when(propertyRepository.findById(id))
                .thenReturn(Optional.of(property));

        propertyService.patchProperty(id, propertyPatchDTO);

        assertEquals(property.getPropertyType(),
                propertyPatchDTO.getPropertyType());
        assertEquals(property.getOfferStatus(),
                propertyPatchDTO.getOfferStatus());
        assertEquals(property.getTitle(),
                propertyPatchDTO.getTitle());
        assertEquals(property.getDescription(),
                "Description");
        assertEquals(property.getPrice(),
                90000.00);
    }

    @Test
    void patchProperty_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyService.patchProperty(id,
                        PropertyPatchDTO.builder().build()));
    }

    @Test
    void deleteProperty() {
        UUID id = UUID.randomUUID();

        when(propertyRepository.findById(id))
                .thenReturn(Optional.of(Property.builder().build()));

        propertyService.deleteProperty(id);

        verify(propertyRepository).deleteById(id);
    }

    @Test
    void deleteProperty_ShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () ->
                propertyService.deleteProperty(id));
    }
}