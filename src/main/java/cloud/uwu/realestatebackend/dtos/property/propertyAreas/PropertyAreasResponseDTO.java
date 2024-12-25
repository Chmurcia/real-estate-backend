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

    private Double totalArea;

    private Double buildingArea;

    private Double livingArea;

    private Double gardenArea;

    private Double garageArea;

    private Double basementArea;

    private Double atticArea;

    private Double poolArea;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
