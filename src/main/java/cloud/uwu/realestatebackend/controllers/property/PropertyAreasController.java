package cloud.uwu.realestatebackend.controllers.property;

import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsResponseDTO;
import cloud.uwu.realestatebackend.services.property.PropertyAreasService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.PropertyController.PropertyURL;

@RestController
@RequestMapping(PropertyAreasController.URL)
@RequiredArgsConstructor
public class PropertyAreasController {
    public static final String URL = PropertyURL + "/areas";

    private final PropertyAreasService propertyAreasService;

    @GetMapping("/{areasId}")
    public ResponseEntity<PropertyAreasResponseDTO> getPropertyAreasById(
            @PathVariable("areasId") @NotNull UUID id) {
        PropertyAreasResponseDTO foundAreas = propertyAreasService
                .getPropertyAreasById(id);

        return ResponseEntity.ok(foundAreas);
    }

    @PutMapping("/{areasId}")
    public ResponseEntity<Object> updatePropertyAreas(
            @PathVariable("areasId") @NotNull UUID id,
            @RequestBody @Validated PropertyAreasDTO propertyAreasDTO) {
        propertyAreasService.updatePropertyAreas(id, propertyAreasDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{areasId}")
    public ResponseEntity<Object> patchPropertyAreas(
            @PathVariable("areasId") @NotNull UUID id,
            @RequestBody @Validated PropertyAreasPatchDTO propertyAreasPatchDTO) {
        propertyAreasService.patchPropertyAreas(id, propertyAreasPatchDTO);

        return ResponseEntity.noContent().build();
    }
}
