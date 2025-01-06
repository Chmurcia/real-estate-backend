
package cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics;

import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAccessibility.PropertyAccessibilityResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyAmenity.PropertyAmenityResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts.PropertyCountsResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyTrustLevel.PropertyTrustLevelResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyStatisticsResponseDTO {
    private UUID id;

    private Double rating;

    @JsonProperty("last_visited_at")
    private ZonedDateTime lastVisitedAt;

    @JsonProperty("accessibilities")
    private List<PropertyAccessibilityResponseDTO> propertyAccessibilityResponseDTOS;

    @JsonProperty("amenities")
    private List<PropertyAmenityResponseDTO> propertyAmenityResponseDTOS;

    @JsonProperty("trust_levels")
    private List<PropertyTrustLevelResponseDTO> propertyTrustLevelResponseDTOS;

    @JsonProperty("counts")
    private PropertyCountsResponseDTO propertyCountsResponseDTO;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
