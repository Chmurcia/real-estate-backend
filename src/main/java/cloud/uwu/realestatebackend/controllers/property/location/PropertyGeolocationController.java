package cloud.uwu.realestatebackend.controllers.property.location;

import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationDTO;
import cloud.uwu.realestatebackend.dtos.property.location.geolocation.PropertyGeolocationPatchDTO;
import cloud.uwu.realestatebackend.services.property.location.PropertyGeolocationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.PropertyController.PropertyURL;

@RestController
@RequestMapping(PropertyGeolocationController.URL)
@RequiredArgsConstructor
public class PropertyGeolocationController {
    public static final String URL = PropertyURL + "/geolocation";

    private final PropertyGeolocationService propertyGeolocationService;

    @PutMapping("/{propertyGeolocationId}")
    public ResponseEntity<Object> updatePropertyGeolocation(
            @PathVariable("propertyGeolocationId") @NotNull UUID id,
            @RequestBody @Validated PropertyGeolocationDTO propertyGeolocationDTO) {
        propertyGeolocationService.updatePropertyGeolocation(id, propertyGeolocationDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{propertyGeolocationId}")
    public ResponseEntity<Object> patchPropertyGeolocation(
            @PathVariable("propertyGeolocationId") @NotNull UUID id,
            @RequestBody @Validated PropertyGeolocationPatchDTO propertyGeolocationPatchDTO) {
        propertyGeolocationService.patchPropertyGeolocation(id, propertyGeolocationPatchDTO);

        return ResponseEntity.noContent().build();
    }
}
