package cloud.uwu.realestatebackend.controllers.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsResponseDTO;
import cloud.uwu.realestatebackend.services.property.statistics.PropertyCountsService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.statistics.PropertyStatisticsController.StatisticsURL;

@RestController
@RequestMapping(PropertyCountsController.URL)
@RequiredArgsConstructor
public class PropertyCountsController {
    public static final String URL = StatisticsURL + "/counts";

    private final PropertyCountsService propertyCountsService;

    @GetMapping("/property/{statisticsId}")
    public ResponseEntity<PropertyCountsResponseDTO> getPropertyCountsByPropertyStatisticsId(
            @PathVariable("statisticsId") @NotNull UUID id) {
        PropertyCountsResponseDTO foundCounts = propertyCountsService
                .getPropertyCountsByPropertyStatisticsId(id);

        return ResponseEntity.ok(foundCounts);
    }

    @PutMapping("/{countsId}")
    public ResponseEntity<Object> updatePropertyCounts(
            @PathVariable("countsId") @NotNull UUID id,
            @RequestBody @Validated PropertyCountsDTO propertyCountsDTO) {
        propertyCountsService.updatePropertyCounts(id, propertyCountsDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{countsId}")
    public ResponseEntity<Object> patchPropertyCounts(
            @PathVariable("countsId") @NotNull UUID id,
            @RequestBody @Validated PropertyCountsPatchDTO propertyCountsPatchDTO) {
        propertyCountsService.patchPropertyCounts(id, propertyCountsPatchDTO);

        return ResponseEntity.noContent().build();
    }
}
