package cloud.uwu.realestatebackend.dtos.property.propertyRooms;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyRoomsResponseDTO {
    private UUID id;

    // Basic rooms
    @JsonProperty("total_rooms")
    private Integer totalRooms;

    @JsonProperty("total_bedrooms")
    private Integer totalBedrooms;

    @JsonProperty("total_kitchens")
    private Integer totalKitchens;

    @JsonProperty("total_bathrooms")
    private Integer totalBathrooms;

    @JsonProperty("total_living_rooms")
    private Integer totalLivingRooms;

    @JsonProperty("total_dining_rooms")
    private Integer totalDiningRooms;

    @JsonProperty("total_balconies")
    private Integer totalBalconies;

    // Utility rooms
    @JsonProperty("total_garages")
    private Integer totalGarages;

    @JsonProperty("total_basements")
    private Integer totalBasements;

    @JsonProperty("total_attics")
    private Integer totalAttics;

    @JsonProperty("total_storage_rooms")
    private Integer totalStorageRooms;

    @JsonProperty("total_closets")
    private Integer totalClosets;

    // Special rooms
    @JsonProperty("total_theater_rooms")
    private Integer totalTheaterRooms;

    @JsonProperty("total_saunas")
    private Integer totalSaunas;

    @JsonProperty("total_home_offices")
    private Integer totalHomeOffices;

    @JsonProperty("total_gyms")
    private Integer totalGyms;

    @JsonProperty("total_libraries")
    private Integer totalLibraries;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
