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
public class PropertyReviewPatchDTO {
    @Min(value = 0, message = "rate must be at least 0")
    @Max(value = 10, message = "rate must be at most 10")
    private Double rate;

    @Size(max = 200, message = "description must contain at most 200 characters")
    private String description;
}
