package cloud.uwu.realestatebackend.dtos.other.post;

import cloud.uwu.realestatebackend.dtos.property.propertyAreas.PropertyAreasDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyRooms.PropertyRoomsDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DetailsDTO {
    @Valid
    @NotNull
    @JsonProperty("property_details")
    PropertyDetailsDTO propertyDetailsDTO;

    @Valid
    @NotNull
    @JsonProperty("property_areas")
    PropertyAreasDTO propertyAreasDTO;

    @Valid
    @NotNull
    @JsonProperty("property_rooms")
    PropertyRoomsDTO propertyRoomsDTO;
}
