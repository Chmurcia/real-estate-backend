package cloud.uwu.realestatebackend.dtos.property.statistics.propertyAccessibility;

import cloud.uwu.realestatebackend.entities.property.propertyEnum.AccessibilityType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyAccessibilityResponseDTO {
    private UUID id;

    @JsonProperty("accessibility_type")
    private AccessibilityType accessibilityType;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
