package cloud.uwu.realestatebackend.dtos.property.statistics.propertyAmenity;

import cloud.uwu.realestatebackend.entities.property.propertyEnum.AmenityType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyAmenityDTO {
    @NotNull(message = "amenity_type must be defined")
    @JsonProperty("amenity_type")
    private AmenityType amenityType;
}
