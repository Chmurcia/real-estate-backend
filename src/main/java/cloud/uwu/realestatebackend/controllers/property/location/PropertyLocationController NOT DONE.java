package cloud.uwu.realestatebackend.controllers.property.location;

import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationResponseDTO;
import cloud.uwu.realestatebackend.services.property.location.PropertyLocationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.PropertyController.PropertyURL;

@RestController
@RequestMapping(PropertyLocationController.URL)
@RequiredArgsConstructor
public class PropertyLocationController {
    public static final String URL = PropertyURL + "/location";

    private final PropertyLocationService propertyLocationService;

    @GetMapping("/{propertyLocationId}")
    public ResponseEntity<PropertyLocationResponseDTO> getPropertyLocationById(
            @PathVariable("propertyLocationId") @NotNull UUID id) {
        PropertyLocationResponseDTO foundPropertyLocation = propertyLocationService
                .getPropertyLocationById(id);

        return ResponseEntity.ok(foundPropertyLocation);
    }

}
