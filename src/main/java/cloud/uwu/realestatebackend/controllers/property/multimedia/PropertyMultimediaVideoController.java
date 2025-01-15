package cloud.uwu.realestatebackend.controllers.property.multimedia;

import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaVideo.PropertyMultimediaVideoDTO;
import cloud.uwu.realestatebackend.dtos.property.multimedia.multimediaVideo.PropertyMultimediaVideoResponseDTO;
import cloud.uwu.realestatebackend.services.property.multimedia.PropertyMultimediaVideoService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.multimedia.PropertyMultimediaController.MultimediaURL;

@RestController
@RequestMapping(PropertyMultimediaVideoController.URL)
@RequiredArgsConstructor
public class PropertyMultimediaVideoController {
    public static final String URL = MultimediaURL + "/video";

    private final PropertyMultimediaVideoService propertyMultimediaVideoService;

    @PostMapping
    public ResponseEntity<PropertyMultimediaVideoResponseDTO> createPropertyMultimediaVideo(
            @RequestParam UUID propertyMultimediaId,
            @RequestBody @Validated PropertyMultimediaVideoDTO propertyMultimediaVideoDTO) {
        PropertyMultimediaVideoResponseDTO createdVideo = propertyMultimediaVideoService
                .createPropertyMultimediaVideo(propertyMultimediaId, propertyMultimediaVideoDTO);

        URI url = URI.create(URL + "/" + createdVideo.getId());
        return ResponseEntity.created(url).body(createdVideo);
    }

    @DeleteMapping("/{videoId}")
    public ResponseEntity<Object> deletePropertyMultimediaVideo(
            @PathVariable("videoId") @NotNull UUID id) {
        propertyMultimediaVideoService.deletePropertyMultimediaVideo(id);

        return ResponseEntity.noContent().build();
    }
}
