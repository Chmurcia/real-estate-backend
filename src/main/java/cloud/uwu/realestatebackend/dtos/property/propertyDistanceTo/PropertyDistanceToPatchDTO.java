package cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDistanceToPatchDTO {
    @Min(value = 0, message = "distance must be at least 0")
    private Double distance;

    @Size(max = 100, message = "destination must contain at most 100 characters")
    private String destination;
}
