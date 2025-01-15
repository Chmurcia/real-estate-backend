package cloud.uwu.realestatebackend.controllers.property;

import cloud.uwu.realestatebackend.dtos.other.filters.PropertyFilterDTO;
import cloud.uwu.realestatebackend.dtos.other.post.FullPropertyDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertyDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertyPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertyResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertySmallResponseDTO;
import cloud.uwu.realestatebackend.services.property.PropertyService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(PropertyController.PropertyURL)
@RequiredArgsConstructor
public class PropertyController {
    public static final String PropertyURL = "/api/v0/property";

    private final PropertyService propertyService;

    @GetMapping
    public ResponseEntity<Page<PropertySmallResponseDTO>> getAllProperties(
            @RequestBody(required = false) @Validated PropertyFilterDTO filters,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDirection) {
        Page<PropertySmallResponseDTO> foundProperties = propertyService
                .getAllProperties(filters, page, size, sortBy, sortDirection);

        return ResponseEntity.ok(foundProperties);
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<Page<PropertyResponseDTO>> getPropertiesByProfileId(
            @PathVariable("profileId") @NotNull UUID id,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<PropertyResponseDTO> foundProperties = propertyService
                .getPropertiesByProfileId(id, page, size);

        return ResponseEntity.ok(foundProperties);
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyResponseDTO> getPropertyById(
            @PathVariable("propertyId") @NotNull UUID id) {
        PropertyResponseDTO foundProperty = propertyService.getPropertyById(id);

        return ResponseEntity.ok(foundProperty);
    }

    @PostMapping
    public ResponseEntity<PropertyResponseDTO> createProperty(
            @RequestParam UUID profileId,
            @RequestBody @Validated FullPropertyDTO fullPropertyDTO) {
                PropertyResponseDTO createdProperty = propertyService
                    .createProperty(profileId, fullPropertyDTO);

                URI url = URI.create(PropertyURL + "/" + createdProperty.getId());
                return ResponseEntity.created(url).body(createdProperty);
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<Object> updateProperty(
            @PathVariable("propertyId") @NotNull UUID id,
            @RequestBody @Validated PropertyDTO propertyDTO) {
        propertyService.updateProperty(id, propertyDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{propertyId}")
    public ResponseEntity<Object> patchProperty(
            @PathVariable("propertyId") @NotNull UUID id,
            @RequestBody @Validated PropertyPatchDTO propertyPatchDTO) {
        propertyService.patchProperty(id, propertyPatchDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Object> deleteProperty(
            @PathVariable("propertyId") @NotNull UUID id) {
        propertyService.deleteProperty(id);

        return ResponseEntity.noContent().build();
    }
}
