package cloud.uwu.realestatebackend.dtos.property.propertyDetails;

import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.ConditionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDetailsResponseDTO {
    private UUID id;

    @JsonProperty("total_floors")
    private Integer totalFloors;

    @JsonProperty("year_built")
    private Integer yearBuilt;

    @JsonProperty("condition_status")
    private ConditionStatus conditionStatus;

    @JsonProperty("areas")
    private PropertyAreasResponseDTO propertyAreasResponseDTO;

    @JsonProperty("rooms")
    private PropertyRoomsResponseDTO propertyRoomsResponseDTO;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
