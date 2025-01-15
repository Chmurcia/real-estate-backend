package cloud.uwu.realestatebackend.controllers.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyTrustLevel.PropertyTrustLevelDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyTrustLevel.PropertyTrustLevelResponseDTO;
import cloud.uwu.realestatebackend.services.property.statistics.PropertyTrustLevelService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.statistics.PropertyStatisticsController.StatisticsURL;

@RestController
@RequestMapping(PropertyTrustLevelController.URL)
@RequiredArgsConstructor
public class PropertyTrustLevelController {
    public static final String URL = StatisticsURL + "/trust";

    private final PropertyTrustLevelService propertyTrustLevelService;

    @GetMapping("/property/{statisticsId}")
    public ResponseEntity<Page<PropertyTrustLevelResponseDTO>> getPropertyTrustLevelsByPropertyStatisticsId(
            @PathVariable("statisticsId") UUID id,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<PropertyTrustLevelResponseDTO> foundTrustLevels = propertyTrustLevelService
                .getPropertyTrustLevelsByPropertyStatisticsId(id, page, size);

        return ResponseEntity.ok(foundTrustLevels);
    }

    @PostMapping
    public ResponseEntity<PropertyTrustLevelResponseDTO> createPropertyTrustLevel(
            @RequestParam UUID statisticsId,
            @RequestBody @Validated PropertyTrustLevelDTO propertyTrustLevelDTO) {
        PropertyTrustLevelResponseDTO createdTrustLevel = propertyTrustLevelService
                .createPropertyTrustLevel(statisticsId, propertyTrustLevelDTO);

        URI url = URI.create(URL + "/" + createdTrustLevel.getId());
        return ResponseEntity.created(url).body(createdTrustLevel);
    }

    @DeleteMapping("/{trustLevelId}")
    public ResponseEntity<Object> deleteTrustLevel(
            @PathVariable("trustLevelId") @NotNull UUID id)
    {
        propertyTrustLevelService.deletePropertyTrustLevel(id);

        return ResponseEntity.noContent().build();
    }
}
