package cloud.uwu.realestatebackend.dtos.property.statistics.propertyTrustLevel;

import cloud.uwu.realestatebackend.entities.property.statistics.PropertyStatistics;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyTrustLevelPatchDTO {
    @Min(value = 0, message = "trust_level must be at least 0")
    @Max(value = 10, message = "trust level must be at most 10")
    @JsonProperty("trust_level")
    private Integer trustLevel;
}
