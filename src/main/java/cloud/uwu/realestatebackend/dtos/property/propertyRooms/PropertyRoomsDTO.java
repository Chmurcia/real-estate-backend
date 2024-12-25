package cloud.uwu.realestatebackend.dtos.property.propertyRooms;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyRoomsDTO {
    // Basic rooms
    @NotNull(message = "total_rooms must be defined")
    @Min(value = 0, message = "total_rooms must be at least 0")
    @JsonProperty("total_rooms")
    private Integer totalRooms;

    @NotNull(message = "total_bedrooms must be defined")
    @Min(value = 0, message = "total_bedrooms must be at least 0")
    @JsonProperty("total_bedrooms")
    private Integer totalBedrooms;

    @NotNull(message = "total_kitchens must be defined")
    @Min(value = 0, message = "total_kitchens must be at least 0")
    @JsonProperty("total_kitchens")
    private Integer totalKitchens;

    @NotNull(message = "total_bathrooms must be defined")
    @Min(value = 0, message = "total_bathrooms must be at least 0")
    @JsonProperty("total_bathrooms")
    private Integer totalBathrooms;

    @NotNull(message = "total_living_rooms must be defined")
    @Min(value = 0, message = "total_living_rooms must be at least 0")
    @JsonProperty("total_living_rooms")
    private Integer totalLivingRooms;

    @NotNull(message = "total_dining_rooms must be defined")
    @Min(value = 0, message = "total_dining_rooms must be at least 0")
    @JsonProperty("total_dining_rooms")
    private Integer totalDiningRooms;

    @NotNull(message = "total_balconies must be defined")
    @Min(value = 0, message = "total_balconies must be at least 0")
    @JsonProperty("total_balconies")
    private Integer totalBalconies;

    // Utility rooms

    @NotNull(message = "total_garages must be defined")
    @Min(value = 0, message = "total_garages must be at least 0")
    @JsonProperty("total_garages")
    private Integer totalGarages;

    @NotNull(message = "total_basements must be defined")
    @Min(value = 0, message = "total_basements must be at least 0")
    @JsonProperty("total_basements")
    private Integer totalBasements;

    @NotNull(message = "total_attics must be defined")
    @Min(value = 0, message = "total_attics must be at least 0")
    @JsonProperty("total_attics")
    private Integer totalAttics;

    @NotNull(message = "total_storage_rooms must be defined")
    @Min(value = 0, message = "total_storage_rooms must be at least 0")
    @JsonProperty("total_storage_rooms")
    private Integer totalStorageRooms;

    @NotNull(message = "total_closets must be defined")
    @Min(value = 0, message = "total_closets must be at least 0")
    @JsonProperty("total_closets")
    private Integer totalClosets;

    // Special rooms

    @NotNull(message = "total_theater_rooms must be defined")
    @Min(value = 0, message = "total_theater_rooms must be at least 0")
    @JsonProperty("total_theater_rooms")
    private Integer totalTheaterRooms;

    @NotNull(message = "total_saunas must be defined")
    @Min(value = 0, message = "total_saunas must be at least 0")
    @JsonProperty("total_saunas")
    private Integer totalSaunas;

    @NotNull(message = "total_home_offices must be defined")
    @Min(value = 0, message = "total_home_offices must be at least 0")
    @JsonProperty("total_home_offices")
    private Integer totalHomeOffices;

    @NotNull(message = "total_gyms must be defined")
    @Min(value = 0, message = "total_gyms must be at least 0")
    @JsonProperty("total_gyms")
    private Integer totalGyms;

    @NotNull(message = "total_libraries must be defined")
    @Min(value = 0, message = "total_libraries must be at least 0")
    @JsonProperty("total_libraries")
    private Integer totalLibraries;
}
