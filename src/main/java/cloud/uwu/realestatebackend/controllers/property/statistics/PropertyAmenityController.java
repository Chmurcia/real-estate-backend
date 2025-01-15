package cloud.uwu.realestatebackend.controllers.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAmenity.PropertyAmenityDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAmenity.PropertyAmenityResponseDTO;
import cloud.uwu.realestatebackend.services.property.statistics.PropertyAmenityService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.statistics.PropertyStatisticsController.StatisticsURL;

@RestController
@RequestMapping(PropertyAmenityController.URL)
@RequiredArgsConstructor
public class PropertyAmenityController {
    public static final String URL = StatisticsURL + "/amenity";

    private final PropertyAmenityService propertyAmenityService;

    @GetMapping("/property/{statisticsId}")
    public ResponseEntity<List<PropertyAmenityResponseDTO>> getPropertyAmenitiesByPropertyStatisticsId(
            @PathVariable("statisticsId") @NotNull UUID id)
    {
        List<PropertyAmenityResponseDTO> foundAmenities = propertyAmenityService
                .getPropertyAmenitiesByPropertyStatisticsId(id);

        return ResponseEntity.ok(foundAmenities);
    }

    @PostMapping
    public ResponseEntity<PropertyAmenityResponseDTO> createPropertyAmenity(
            @RequestParam UUID statisticsId,
            @RequestBody @Validated PropertyAmenityDTO PropertyAmenityDTO)
    {
        PropertyAmenityResponseDTO createdAmenity = propertyAmenityService
                .createPropertyAmenity(statisticsId, PropertyAmenityDTO);

        URI url = URI.create(URL + "/" + createdAmenity.getId());
        return ResponseEntity.created(url).body(createdAmenity);
    }

    @DeleteMapping("/{amenityId}")
    public ResponseEntity<Object> deletePropertyAmenity(
            @PathVariable("amenityId") @NotNull UUID id)
    {
        propertyAmenityService.deletePropertyAmenity(id);

        return ResponseEntity.noContent().build();
    }

}
