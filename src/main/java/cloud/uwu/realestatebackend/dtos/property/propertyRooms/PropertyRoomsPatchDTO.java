package cloud.uwu.realestatebackend.dtos.property.propertyRooms;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyRoomsPatchDTO {
    // Basic rooms
    @Min(value = 0, message = "total_rooms must be at least 0")
    @JsonProperty("total_rooms")
    private Integer totalRooms;

    @Min(value = 0, message = "total_bedrooms must be at least 0")
    @JsonProperty("total_bedrooms")
    private Integer totalBedrooms;

    @Min(value = 0, message = "total_kitchens must be at least 0")
    @JsonProperty("total_kitchens")
    private Integer totalKitchens;

    @Min(value = 0, message = "total_bathrooms must be at least 0")
    @JsonProperty("total_bathrooms")
    private Integer totalBathrooms;

    @Min(value = 0, message = "total_living_rooms must be at least 0")
    @JsonProperty("total_living_rooms")
    private Integer totalLivingRooms;

    @Min(value = 0, message = "total_dining_rooms must be at least 0")
    @JsonProperty("total_dining_rooms")
    private Integer totalDiningRooms;

    @Min(value = 0, message = "total_balconies must be at least 0")
    @JsonProperty("total_balconies")
    private Integer totalBalconies;

    // Utility rooms
    @Min(value = 0, message = "total_garages must be at least 0")
    @JsonProperty("total_garages")
    private Integer totalGarages;

    @Min(value = 0, message = "total_basements must be at least 0")
    @JsonProperty("total_basements")
    private Integer totalBasements;

    @Min(value = 0, message = "total_attics must be at least 0")
    @JsonProperty("total_attics")
    private Integer totalAttics;

    @Min(value = 0, message = "total_storage_rooms must be at least 0")
    @JsonProperty("total_storage_rooms")
    private Integer totalStorageRooms;

    @Min(value = 0, message = "total_closets must be at least 0")
    @JsonProperty("total_closets")
    private Integer totalClosets;

    // Special rooms
    @Min(value = 0, message = "total_theater_rooms must be at least 0")
    @JsonProperty("total_theater_rooms")
    private Integer totalTheaterRooms;

    @Min(value = 0, message = "total_saunas must be at least 0")
    @JsonProperty("total_saunas")
    private Integer totalSaunas;

    @Min(value = 0, message = "total_home_offices must be at least 0")
    @JsonProperty("total_home_offices")
    private Integer totalHomeOffices;

    @Min(value = 0, message = "total_gyms must be at least 0")
    @JsonProperty("total_gyms")
    private Integer totalGyms;

    @Min(value = 0, message = "total_libraries must be at least 0")
    @JsonProperty("total_libraries")
    private Integer totalLibraries;
}
