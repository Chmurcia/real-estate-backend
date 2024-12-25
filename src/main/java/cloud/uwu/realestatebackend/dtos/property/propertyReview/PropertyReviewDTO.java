package cloud.uwu.realestatebackend.dtos.property.propertyReview;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyReviewDTO {

    @NotNull(message = "evaluator_id must be defined")
    @JsonProperty("evaluator_id")
    private UUID evaluatorId;

    @NotNull(message = "rate must be defined")
    @Min(value = 0, message = "rate must be at least 0")
    @Max(value = 10, message = "rate must be at most 10")
    private Double rate;

    @NotBlank(message = "description must be defined")
    @Size(max = 200, message = "description must contain at most 200 characters")
    private String description;

}
