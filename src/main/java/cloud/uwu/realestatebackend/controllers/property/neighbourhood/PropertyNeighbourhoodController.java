package cloud.uwu.realestatebackend.controllers.property.neighbourhood;

import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodResponseDTO;
import cloud.uwu.realestatebackend.services.property.neighbourhood.PropertyNeighbourhoodService;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.PropertyController.PropertyURL;

@RestController
@RequestMapping(PropertyNeighbourhoodController.NeighbourhoodURL)
@RequiredArgsConstructor
public class PropertyNeighbourhoodController {
    public static final String NeighbourhoodURL = PropertyURL + "/neighbourhood";

    private final PropertyNeighbourhoodService propertyNeighbourhoodService;

    @GetMapping("/{neighbourhoodId}")
    public ResponseEntity<PropertyNeighbourhoodResponseDTO> getPropertyNeighbourhoodById(
            @PathVariable("neighbourhoodId") @NotNull UUID id) {
        PropertyNeighbourhoodResponseDTO foundNeighbourhood = propertyNeighbourhoodService
                .getPropertyNeighbourhoodById(id);

        return ResponseEntity.ok(foundNeighbourhood);
    }

    @PutMapping("/{neighbourhoodId}")
    public ResponseEntity<PropertyNeighbourhoodResponseDTO> updatePropertyNeighbourhood(
            @PathVariable("neighbourhoodId") @NotNull UUID id,
            @RequestBody @Validated PropertyNeighbourhoodDTO propertyNeighbourhoodDTO) {
        propertyNeighbourhoodService
                .updatePropertyNeighbourhood(id, propertyNeighbourhoodDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{neighbourhoodId}")
    public ResponseEntity<PropertyNeighbourhoodResponseDTO> patchPropertyNeighbourhood(
            @PathVariable("neighbourhoodId") @NotNull UUID id,
            @RequestBody @Validated PropertyNeighbourhoodPatchDTO propertyNeighbourhoodPatchDTO) {
        propertyNeighbourhoodService
                .patchPropertyNeighbourhood(id, propertyNeighbourhoodPatchDTO);

        return ResponseEntity.noContent().build();
    }
}
