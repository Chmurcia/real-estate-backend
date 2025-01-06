package cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhoodReview;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyNeighbourhoodReviewDTO {
    @NotNull(message = "neighbourhood_id must be defined")
    @JsonProperty("neighbourhood_id")
    private UUID neighbourhoodId;

    @NotNull(message = "reviewer_id must de defined")
    @JsonProperty("reviewer_id")
    private UUID reviewerId;

    @NotNull(message = "rate must de defined")
    @Max(value = 10, message = "rate must be at most 10")
    @Min(value = 0, message = "rate must be at least 0")
    private Integer rate;

    @NotBlank(message = "description must de defined")
    @Size(min = 1, max = 100, message = "description must contain between 1 and 100 characters")
    private String description;

    @NotNull(message = "noise_level_rate must be defined")
    @Max(value = 10, message = "noise_level_rate must be at most 10")
    @Min(value = 0, message = "noise_level_rate must be at least 0")
    @JsonProperty("noise_level_rate")
    private Integer noiseLevelRate;

    @NotNull(message = "quality_level_rate must be defined")
    @Max(value = 10, message = "quality_level_rate must be at most 10")
    @Min(value = 0, message = "quality_level_rate must be at least 0")
    @JsonProperty("quality_level_rate")
    private Integer qualityLevelRate;

    @NotNull(message = "safety_level_rate must be defined")
    @Max(value = 10, message = "safety_level_rate must be at most 10")
    @Min(value = 0, message = "safety_level_rate must be at least 0")
    @JsonProperty("safety_level_rate")
    private Integer safetyLevelRate;
}
