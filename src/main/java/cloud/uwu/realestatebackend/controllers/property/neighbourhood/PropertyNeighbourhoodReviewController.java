package cloud.uwu.realestatebackend.controllers.property.neighbourhood;

import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview.PropertyNeighbourhoodReviewDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview.PropertyNeighbourhoodReviewPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview.PropertyNeighbourhoodReviewResponseDTO;
import cloud.uwu.realestatebackend.services.property.neighbourhood.PropertyNeighbourhoodReviewService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.neighbourhood.PropertyNeighbourhoodController.NeighbourhoodURL;

@RestController
@RequestMapping(PropertyNeighbourhoodReviewController.URL)
@RequiredArgsConstructor
public class PropertyNeighbourhoodReviewController {

    public static final String URL = NeighbourhoodURL + "/review";

    private final PropertyNeighbourhoodReviewService propertyNeighbourhoodReviewService;

    @GetMapping("/{neighbourhoodReviewId}")
    public ResponseEntity<PropertyNeighbourhoodReviewResponseDTO> getPropertyNeighbourhoodReviewById(
            @PathVariable("neighbourhoodReviewId") @NotNull UUID id) {
        PropertyNeighbourhoodReviewResponseDTO foundNeighbourhoodReview =
                propertyNeighbourhoodReviewService.getPropertyNeighbourhoodReviewById(id);

        return ResponseEntity.ok(foundNeighbourhoodReview);
    }

    @GetMapping("/neighbourhood/{neighbourhoodId}")
    public ResponseEntity<Page<PropertyNeighbourhoodReviewResponseDTO>> getPropertyNeighbourhoodReviewsByNeighbourhoodId(
            @PathVariable("neighbourhoodId") @NotNull UUID id,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<PropertyNeighbourhoodReviewResponseDTO> foundReviews =
                propertyNeighbourhoodReviewService
                        .getPropertyNeighbourhoodReviewsByNeighbourhoodId(id, page, size);

        return ResponseEntity.ok(foundReviews);
    }

    @PostMapping
    public ResponseEntity<PropertyNeighbourhoodReviewResponseDTO> createPropertyNeighbourhoodReview(
            @RequestParam UUID neighbourhoodId,
            @RequestBody @Validated PropertyNeighbourhoodReviewDTO propertyNeighbourhoodReviewDTO) {
        PropertyNeighbourhoodReviewResponseDTO createdReview = propertyNeighbourhoodReviewService
                .createPropertyNeighbourhoodReview(neighbourhoodId, propertyNeighbourhoodReviewDTO);

        URI url = URI.create(URL + "/" + createdReview.getId());
        return ResponseEntity.created(url).body(createdReview);
    }

    @PutMapping("/{neighbourhoodReviewId}")
    public ResponseEntity<Object> updatePropertyNeighbourhoodReview(
            @PathVariable("neighbourhoodReviewId") @NotNull UUID id,
            @RequestBody @Validated PropertyNeighbourhoodReviewDTO propertyNeighbourhoodReviewDTO) {
        propertyNeighbourhoodReviewService.updatePropertyNeighbourhoodReview(id,
                propertyNeighbourhoodReviewDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{neighbourhoodReviewId}")
    public ResponseEntity<Object> patchPropertyNeighbourhoodReview(
            @PathVariable("neighbourhoodReviewId") @NotNull UUID id,
            @RequestBody @Validated PropertyNeighbourhoodReviewPatchDTO propertyNeighbourhoodReviewPatchDTO) {
        propertyNeighbourhoodReviewService.patchPropertyNeighbourhoodReview(id,
                propertyNeighbourhoodReviewPatchDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{neighbourhoodReviewId}")
    public ResponseEntity<Object> deletePropertyNeighbourhoodReview(
            @PathVariable("neighbourhoodReviewId") @NotNull UUID id) {
        propertyNeighbourhoodReviewService
                 .deletePropertyNeighbourhoodReview(id);

        return ResponseEntity.noContent().build();
    }
}
