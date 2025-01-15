package cloud.uwu.realestatebackend.controllers.property;

import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsResponseDTO;
import cloud.uwu.realestatebackend.services.property.PropertyDetailsService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.PropertyController.PropertyURL;

@RestController
@RequestMapping(PropertyDetailsController.URL)
@RequiredArgsConstructor
public class PropertyDetailsController {
    public static final String URL = PropertyURL + "/details";

    private final PropertyDetailsService propertyDetailsService;

    @GetMapping("/{detailsId}")
    public ResponseEntity<PropertyDetailsResponseDTO> getPropertyDetailsById(
            @PathVariable("detailsId") @NotNull UUID id) {
        PropertyDetailsResponseDTO foundDetails = propertyDetailsService
                .getPropertyDetailsById(id);

        return ResponseEntity.ok(foundDetails);
    }

    @PutMapping("/{detailsId}")
    public ResponseEntity<Object> updatePropertyDetails(
            @PathVariable("detailsId") @NotNull UUID id,
            @RequestBody @Validated PropertyDetailsDTO propertyDetailsDTO) {
        propertyDetailsService.updatePropertyDetails(id, propertyDetailsDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{detailsId}")
    public ResponseEntity<Object> patchPropertyDetails(
            @PathVariable("detailsId") @NotNull UUID id,
            @RequestBody @Validated PropertyDetailsPatchDTO propertyDetailsPatchDTO) {
        propertyDetailsService.patchPropertyDetails(id, propertyDetailsPatchDTO);

        return ResponseEntity.noContent().build();
    }
}
