package cloud.uwu.realestatebackend.controllers.property.multimedia;

import cloud.uwu.realestatebackend.dtos.property.multimedia.PropertyMultimediaResponseDTO;
import cloud.uwu.realestatebackend.services.property.multimedia.PropertyMultimediaService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.PropertyController.PropertyURL;

@RestController
@RequestMapping(PropertyMultimediaController.MultimediaURL)
@RequiredArgsConstructor
public class PropertyMultimediaController {
    public static final String MultimediaURL = PropertyURL + "/multimedia";

    private final PropertyMultimediaService propertyMultimediaService;

    @GetMapping("/{propertyMultimediaId}")
    public ResponseEntity<PropertyMultimediaResponseDTO> getPropertyMultimediaById(
            @PathVariable("propertyMultimediaId") @NotNull UUID id) {
        PropertyMultimediaResponseDTO foundPropertyMultimedia = propertyMultimediaService
                .getPropertyMultimediaById(id);

        return ResponseEntity.ok(foundPropertyMultimedia);
    }
}
