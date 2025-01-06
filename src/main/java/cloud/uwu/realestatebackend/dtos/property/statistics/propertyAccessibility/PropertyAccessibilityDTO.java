package cloud.uwu.realestatebackend.dtos.property.statistics.propertyAccessibility;

import cloud.uwu.realestatebackend.entities.property.propertyEnum.AccessibilityType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyAccessibilityDTO {
    @NotNull(message = "property_statistics_id must be defined")
    @JsonProperty("property_statistics_id")
    private UUID propertyStatisticsId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "accessibility_type must be defined")
    @JsonProperty("accessibility_type")
    private AccessibilityType accessibilityType;
}
