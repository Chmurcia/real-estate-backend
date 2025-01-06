package cloud.uwu.realestatebackend.dtos.property.statistics.propertyAmenity;

import cloud.uwu.realestatebackend.entities.property.propertyEnum.AmenityType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyAmenityDTO {
    @JsonProperty("property_statistics_id")
    private UUID propertyStatisticsId;

    @NotNull(message = "amenity_type must be defined")
    @JsonProperty("amenity_type")
    private AmenityType amenityType;
}
