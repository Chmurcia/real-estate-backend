package cloud.uwu.realestatebackend.controllers.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsResponseDTO;
import cloud.uwu.realestatebackend.services.property.statistics.PropertyStatisticsService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.PropertyController.PropertyURL;

@RestController
@RequestMapping(PropertyStatisticsController.StatisticsURL)
@RequiredArgsConstructor
public class PropertyStatisticsController {
    public static final String StatisticsURL = PropertyURL + "/statistics";

    private final PropertyStatisticsService propertyStatisticsService;

    @GetMapping("/{statisticsId}")
    public ResponseEntity<PropertyStatisticsResponseDTO> getPropertyStatisticsById(
            @PathVariable("statisticsId") @NotNull UUID id) {
        PropertyStatisticsResponseDTO foundStatistics = propertyStatisticsService
                .getPropertyStatisticsById(id);

        return ResponseEntity.ok(foundStatistics);
    }

    @PutMapping("/{statisticsId}")
    public ResponseEntity<Object> updatePropertyStatistics(
            @PathVariable("statisticsId") @NotNull UUID id,
            @RequestBody @Validated PropertyStatisticsDTO propertyStatisticsDTO) {
        propertyStatisticsService.updatePropertyStatistics(id, propertyStatisticsDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{statisticsId}")
    public ResponseEntity<Object> updatePropertyStatistics(
            @PathVariable("statisticsId") @NotNull UUID id,
            @RequestBody @Validated PropertyStatisticsPatchDTO propertyStatisticsPatchDTO) {
        propertyStatisticsService.patchPropertyStatistics(id, propertyStatisticsPatchDTO);

        return ResponseEntity.noContent().build();
    }
}
