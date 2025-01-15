package cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
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
public class PropertyDistanceToDTO {
    @NotNull(message = "distance must be defined")
    @Min(value = 0, message = "distance must be at least 0")
    private Double distance;

    @NotBlank(message = "destination must be defined")
    @Size(max = 100, message = "destination must contain at most 100 characters")
    private String destination;
}
