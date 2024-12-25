package cloud.uwu.realestatebackend.dtos.property.propertyAreas;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyAreasDTO {
    @NotNull(message = "total_area must be defined")
    @Min(value = 0, message = "total_area must be at least 0")
    @JsonProperty("total_area")
    private Double totalArea;

    @NotNull(message = "building_area must be defined")
    @Min(value = 0, message = "building_area must be at least 0")
    @JsonProperty("building_area")
    private Double buildingArea;

    @NotNull(message = "living_area must be defined")
    @Min(value = 0, message = "living_area must be at least 0")
    @JsonProperty("living_area")
    private Double livingArea;

    @NotNull(message = "garden_area must be defined")
    @Min(value = 0, message = "garden_area must be at least 0")
    @JsonProperty("garden_area")
    private Double gardenArea;

    @NotNull(message = "garage_area")
    @Min(value = 0, message = "garage_area must be at least 0")
    @JsonProperty("garage_area")
    private Double garageArea;

    @NotNull(message = "basement_area must be defined")
    @Min(value = 0, message = "basement_area must be at least 0")
    @JsonProperty("basement_area")
    private Double basementArea;

    @NotNull(message = "attic_area must be defined")
    @Min(value = 0, message = "attic_area must be at least 0")
    @JsonProperty("attic_area")
    private Double atticArea;

    @NotNull(message = "pool_area must be defined")
    @Min(value = 0, message = "pool_area must be at least 0")
    @JsonProperty("pool_area")
    private Double poolArea;
}
