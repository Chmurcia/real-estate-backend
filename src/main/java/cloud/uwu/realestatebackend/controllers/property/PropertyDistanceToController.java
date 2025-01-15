package cloud.uwu.realestatebackend.controllers.property;

import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToResponseDTO;
import cloud.uwu.realestatebackend.services.property.PropertyDistanceToService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.PropertyController.PropertyURL;

@RestController
@RequestMapping(PropertyDistanceToController.URL)
@RequiredArgsConstructor
public class PropertyDistanceToController {
    public static final String URL = PropertyURL + "/distance";

    private final PropertyDistanceToService propertyDistanceToService;

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<PropertyDistanceToResponseDTO>> getPropertyDistancesToByPropertyId(
            @PathVariable("propertyId") @NotNull UUID id) {
        List<PropertyDistanceToResponseDTO> foundDistances = propertyDistanceToService
                .getPropertyDistancesToByPropertyId(id);

        return ResponseEntity.ok(foundDistances);
    }

    @GetMapping("/{distanceId}")
    public ResponseEntity<PropertyDistanceToResponseDTO> getPropertyDistanceToById(
            @PathVariable("distanceId") @NotNull UUID id) {
        PropertyDistanceToResponseDTO foundDistance = propertyDistanceToService
                .getPropertyDistanceToById(id);

        return ResponseEntity.ok(foundDistance);
    }

    @PostMapping
    public ResponseEntity<PropertyDistanceToResponseDTO> createPropertyDistances(
            @RequestParam UUID propertyId,
            @RequestBody @Validated PropertyDistanceToDTO propertyDistanceToDTO) {
        PropertyDistanceToResponseDTO createdDistance = propertyDistanceToService
                .createPropertyDistances(propertyId, propertyDistanceToDTO);

        URI url = URI.create(URL + "/" + createdDistance.getId());
        return ResponseEntity.created(url).body(createdDistance);
    }

    @PutMapping("/{distanceId}")
    public ResponseEntity<Object> updatePropertyDistance(
            @PathVariable("distanceId") @NotNull UUID id,
            @RequestBody @Validated PropertyDistanceToDTO propertyDistanceToDTO) {
        propertyDistanceToService.updatePropertyDistances(id, propertyDistanceToDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{distanceId}")
    public ResponseEntity<Object> patchPropertyDistance(
            @PathVariable("distanceId") @NotNull UUID id,
            @RequestBody @Validated PropertyDistanceToPatchDTO propertyDistanceToPatchDTO) {
        propertyDistanceToService.patchPropertyDistances(id, propertyDistanceToPatchDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{distanceId}")
    public ResponseEntity<Object> deletePropertyDistance(
            @PathVariable("distanceId") @NotNull UUID id) {
        propertyDistanceToService.deletePropertyDistanceTo(id);

        return ResponseEntity.noContent().build();
    }
}
