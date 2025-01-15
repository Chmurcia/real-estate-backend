package cloud.uwu.realestatebackend.controllers.property.statistics;


import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAccessibility.PropertyAccessibilityDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAccessibility.PropertyAccessibilityResponseDTO;
import cloud.uwu.realestatebackend.services.property.statistics.PropertyAccessibilityService;
import cloud.uwu.realestatebackend.services.property.statistics.PropertyCountsService;
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
@RequestMapping(PropertyAccessibilityController.URL)
@RequiredArgsConstructor
public class PropertyAccessibilityController {

    public static final String URL = StatisticsURL + "/accessibility";

    private final PropertyAccessibilityService propertyAccessibilityService;

    @GetMapping("/property/{statisticsId}")
    public ResponseEntity<List<PropertyAccessibilityResponseDTO>> getPropertyAccessibilitiesByPropertyStatisticsId(
            @PathVariable("statisticsId") @NotNull UUID id)
    {
        List<PropertyAccessibilityResponseDTO> foundAccessibilities = propertyAccessibilityService
                .getPropertyAccessibilitiesByPropertyStatisticsId(id);

        return ResponseEntity.ok(foundAccessibilities);
    }

    @PostMapping
    public ResponseEntity<PropertyAccessibilityResponseDTO> createPropertyAccessibility(
            @RequestParam UUID statisticsId,
            @RequestBody @Validated PropertyAccessibilityDTO propertyAccessibilityDTO)
    {
        PropertyAccessibilityResponseDTO createdAccessibilities = propertyAccessibilityService
                .createPropertyAccessibility(statisticsId, propertyAccessibilityDTO);

        URI url = URI.create(URL + "/" + createdAccessibilities.getId());
        return ResponseEntity.created(url).body(createdAccessibilities);
    }

    @DeleteMapping("/{accessibilityId}")
    public ResponseEntity<Object> deletePropertyAccessibility(
            @PathVariable("accessibilityId") @NotNull UUID id)
    {
        propertyAccessibilityService.deletePropertyAccessibility(id);

        return ResponseEntity.noContent().build();
    }
}
