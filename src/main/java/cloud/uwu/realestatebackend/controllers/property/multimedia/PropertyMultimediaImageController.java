package cloud.uwu.realestatebackend.controllers.property.multimedia;

import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaImage.PropertyMultimediaImageDTO;
import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaImage.PropertyMultimediaImageResponseDTO;
import cloud.uwu.realestatebackend.services.property.multimedia.PropertyMultimediaImageService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.multimedia.PropertyMultimediaController.MultimediaURL;

@RestController
@RequestMapping(PropertyMultimediaImageController.URL)
@RequiredArgsConstructor
public class PropertyMultimediaImageController {
    public static final String URL = MultimediaURL + "/image";

    private final PropertyMultimediaImageService propertyMultimediaImageService;

    @PostMapping
    public ResponseEntity<PropertyMultimediaImageResponseDTO> createPropertyMultimediaImage(
            @RequestParam UUID propertyMultimediaId,
            @RequestBody @Validated PropertyMultimediaImageDTO propertyMultimediaImageDTO) {
        PropertyMultimediaImageResponseDTO createdImage = propertyMultimediaImageService
                .createPropertyMultimediaImage(propertyMultimediaId, propertyMultimediaImageDTO);

        URI url = URI.create(URL + "/" + createdImage.getId());
        return ResponseEntity.created(url).body(createdImage);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Object> deletePropertyMultimediaImage(
            @PathVariable("imageId") @NotNull UUID id) {
        propertyMultimediaImageService.deletePropertyMultimediaImage(id);

        return ResponseEntity.noContent().build();
    }
}
