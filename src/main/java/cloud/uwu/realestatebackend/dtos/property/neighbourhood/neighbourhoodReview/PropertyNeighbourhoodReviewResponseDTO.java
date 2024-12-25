package cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyNeighbourhoodReviewResponseDTO {
    private UUID id;

    @JsonProperty("reviewer_id")
    private UUID reviewerId;

    private Integer rate;

    private String description;

    @JsonProperty("noise_level_rate")
    private Integer noiseLevelRate;

    @JsonProperty("quality_level_rate")
    private Integer qualityLevelRate;

    @JsonProperty("safety_level_rate")
    private Integer safetyLevelRate;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
