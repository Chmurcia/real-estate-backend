package cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood;

import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview.PropertyNeighbourhoodReviewResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyNeighbourhoodResponseDTO {
    private UUID id;

    @JsonProperty("noise_level")
    private Integer noiseLevel;

    @JsonProperty("quality_level")
    private Integer qualityLevel;

    @JsonProperty("safety_level")
    private Integer safetyLevel;

    private List<PropertyNeighbourhoodReviewResponseDTO> reviews;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
