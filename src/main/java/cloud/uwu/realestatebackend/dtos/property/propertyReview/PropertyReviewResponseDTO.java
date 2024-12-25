package cloud.uwu.realestatebackend.dtos.property.propertyReview;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyReviewResponseDTO {
    private UUID id;

    @JsonProperty("evaluator_id")
    private UUID evaluatorId;

    private Double rate;

    private String description;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;

}
