package cloud.uwu.realestatebackend.dtos.property.propertyAreas;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyAreasPatchDTO {
    @Min(value = 0, message = "total_area must be at least 0")
    @JsonProperty("total_area")
    private Double totalArea;

    @Min(value = 0, message = "building_area must be at least 0")
    @JsonProperty("building_area")
    private Double buildingArea;

    @Min(value = 0, message = "living_area must be at least 0")
    @JsonProperty("living_area")
    private Double livingArea;

    @Min(value = 0, message = "garden_area must be at least 0")
    @JsonProperty("garden_area")
    private Double gardenArea;

    @Min(value = 0, message = "garage_area must be at least 0")
    @JsonProperty("garage_area")
    private Double garageArea;

    @Min(value = 0, message = "basement_area must be at least 0")
    @JsonProperty("basement_area")
    private Double basementArea;

    @Min(value = 0, message = "attic_area must be at least 0")
    @JsonProperty("attic_area")
    private Double atticArea;

    @Min(value = 0, message = "pool_area must be at least 0")
    @JsonProperty("pool_area")
    private Double poolArea;
}
