package cloud.uwu.realestatebackend.controllers.property.statistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCountsHistory.PropertyCountsHistoryDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCountsHistory.PropertyCountsHistoryResponseDTO;
import cloud.uwu.realestatebackend.services.property.statistics.PropertyCountsHistoryService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.PropertyCountsController.CountsURL;

@RestController
@RequestMapping(PropertyCountsHistoryController.URL)
@RequiredArgsConstructor
public class PropertyCountsHistoryController {
    public static final String URL = CountsURL + "/history";

    private final PropertyCountsHistoryService propertyCountsHistoryService;

    @GetMapping("/statistics/{statisticsId}")
    public ResponseEntity<Page<PropertyCountsHistoryResponseDTO>> getCountsHistory(
            @PathVariable("statisticsId") @NotNull UUID id,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<PropertyCountsHistoryResponseDTO> foundCountsHistory =
                propertyCountsHistoryService
                        .getCountsHistoryByPropertyStatisticsId(id, page, size);

        return ResponseEntity.ok(foundCountsHistory);
    }

    @PostMapping
    public ResponseEntity<PropertyCountsHistoryResponseDTO> createCountsHistory(
            @RequestParam UUID propertyStatisticsId,
            @RequestBody @Validated PropertyCountsHistoryDTO propertyCountsHistoryDTO) {
        PropertyCountsHistoryResponseDTO createdCountsHistory =
                propertyCountsHistoryService
                        .createCountsHistory(propertyStatisticsId, propertyCountsHistoryDTO);

        URI url = URI.create(URL + "/" + createdCountsHistory.getId());
        return ResponseEntity.created(url).body(createdCountsHistory);
    }

    @DeleteMapping("/{countsHistoryId}")
    public ResponseEntity<Object> deleteCountsHistory(
            @PathVariable("countsHistoryId") @NotNull UUID id) {
        propertyCountsHistoryService.deleteCountsHistory(id);

        return ResponseEntity.noContent().build();
    }
}
