package cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyNeighbourhoodReviewDTO {
    @NotNull(message = "reviewer_id must de defined")
    @JsonProperty("reviewer_id")
    private UUID reviewerId;

    @NotNull(message = "rate must de defined")
    private Integer rate;

    @NotBlank(message = "description must de defined")
    @Size(min = 1, max = 100, message = "description must contain between 1 and 100 characters")
    private String description;

    @NotNull(message = "noise_level_rate must be defined")
    @JsonProperty("noise_level_rate")
    private Integer noiseLevelRate;

    @NotNull(message = "quality_level_rate must be defined")
    @JsonProperty("quality_level_rate")
    private Integer qualityLevelRate;

    @NotNull(message = "safety_level_rate must be defined")
    @JsonProperty("safety_level_rate")
    private Integer safetyLevelRate;
}
