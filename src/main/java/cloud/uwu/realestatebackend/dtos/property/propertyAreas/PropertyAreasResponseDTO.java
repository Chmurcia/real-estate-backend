package cloud.uwu.realestatebackend.dtos.property.propertyAreas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyAreasResponseDTO {
    private UUID id;

    @JsonProperty("total_area")
    private Double totalArea;

    @JsonProperty("building_area")
    private Double buildingArea;

    @JsonProperty("living_area")
    private Double livingArea;

    @JsonProperty("garden_area")
    private Double gardenArea;

    @JsonProperty("garage_area")
    private Double garageArea;

    @JsonProperty("basement_area")
    private Double basementArea;

    @JsonProperty("attic_area")
    private Double atticArea;

    @JsonProperty("pool_area")
    private Double poolArea;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
