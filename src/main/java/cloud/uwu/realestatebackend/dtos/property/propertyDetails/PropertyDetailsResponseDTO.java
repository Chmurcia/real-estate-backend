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

    private Integer totalFloors;

    private Integer yearBuilt;

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
