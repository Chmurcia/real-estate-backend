package cloud.uwu.realestatebackend.dtos.property.statistics.propertyTrustLevel;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyTrustLevelResponseDTO {
    private UUID id;

    @JsonProperty("evaluator_id")
    private UUID evaluatorId;


    @JsonProperty("trust_level")
    private Integer trustLevel;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
