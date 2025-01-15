package cloud.uwu.realestatebackend.controllers.property;

import cloud.uwu.realestatebackend.dtos.property.propertyReview.PropertyReviewDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyReview.PropertyReviewPatchDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyReview.PropertyReviewResponseDTO;
import cloud.uwu.realestatebackend.services.property.PropertyReviewService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.property.PropertyController.PropertyURL;

@RestController
@RequestMapping(PropertyReviewController.URL)
@RequiredArgsConstructor
public class PropertyReviewController {
    public static final String URL = PropertyURL + "/review";


    private final PropertyReviewService propertyReviewService;

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<PropertyReviewResponseDTO>> getPropertyReviewsByPropertyId(
            @PathVariable("propertyId") @NotNull UUID id) {
        List<PropertyReviewResponseDTO> foundReviews = propertyReviewService
                .getPropertyReviewsByPropertyId(id);

        return ResponseEntity.ok(foundReviews);
    }

    @GetMapping("/{propertyReviewId}")
    public ResponseEntity<PropertyReviewResponseDTO> getPropertyReviewById(
            @PathVariable("propertyReviewId") @NotNull UUID id) {
        PropertyReviewResponseDTO foundReview = propertyReviewService
                .getPropertyReviewById(id);

        return ResponseEntity.ok(foundReview);
    }

    @PostMapping
    public ResponseEntity<PropertyReviewResponseDTO> createPropertyReview(
            @RequestParam UUID propertyId,
            @RequestBody @Validated PropertyReviewDTO propertyReviewDTO) {
        PropertyReviewResponseDTO createdReview = propertyReviewService
                .createPropertyReview(propertyId, propertyReviewDTO);

        URI url = URI.create(URL + "/" + createdReview.getId());
        return ResponseEntity.created(url).body(createdReview);
    }

    @PutMapping("/{propertyReviewId}")
    public ResponseEntity<Object> updatePropertyReview(
            @PathVariable("propertyReviewId") @NotNull UUID id,
            @RequestBody @Validated PropertyReviewDTO propertyReviewDTO) {
        propertyReviewService.updatePropertyReview(id, propertyReviewDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{propertyReviewId}")
    public ResponseEntity<Object> patchPropertyReview(
            @PathVariable("propertyReviewId") @NotNull UUID id,
            @RequestBody @Validated PropertyReviewPatchDTO propertyReviewPatchDTO) {
        propertyReviewService.patchPropertyReview(id, propertyReviewPatchDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{propertyReviewId}")
    public ResponseEntity<Object> deletePropertyReview(
            @PathVariable("propertyReviewId") UUID id) {
        propertyReviewService.deletePropertyReview(id);

        return ResponseEntity.noContent().build();
    }
}
