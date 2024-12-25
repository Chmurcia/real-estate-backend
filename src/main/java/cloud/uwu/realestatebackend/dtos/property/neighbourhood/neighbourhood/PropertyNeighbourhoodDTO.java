package cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyNeighbourhoodDTO {
    @NotNull(message = "noise_level must be defined")
    @Max(value = 10, message = "noise_level must be at most 10")
    @Min(value = 1, message = "noise_level must be at least 1")
    @JsonProperty("noise_level")
    private Integer noiseLevel;

    @NotNull(message = "quality_level must be defined")
    @Max(value = 10, message = "quality_level must be at most 10")
    @Min(value = 1, message = "quality_level must be at least 1")
    @JsonProperty("quality_level")
    private Integer qualityLevel;

    @NotNull(message = "safety_level must be defined")
    @Max(value = 10, message = "safety_level must be at most 10")
    @Min(value = 1, message = "safety_level must be at least 1")
    @JsonProperty("safety_level")
    private Integer safetyLevel;
}
