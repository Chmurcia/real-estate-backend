package cloud.uwu.realestatebackend.dtos.other.filters;

import cloud.uwu.realestatebackend.entities.property.propertyEnum.ConditionStatus;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.OfferStatus;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.PropertyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PropertyFilterDTO {
    private String country;
    private String state;
    private String city;

    @JsonProperty("max_price")
    private Double maxPrice;
    @JsonProperty("min_price")
    private Double minPrice;

    @JsonProperty("year_built")
    private String yearBuilt;

    @JsonProperty("max_rating")
    private Double maxRating;
    @JsonProperty("min_rating")
    private Double minRating;

    @JsonProperty("max_likes")
    private Integer maxLikes;
    @JsonProperty("min_likes")
    private Integer minLikes;


    @JsonProperty("max_dislikes")
    private Integer maxDislikes;
    @JsonProperty("min_dislikes")
    private Integer minDislikes;

    @JsonProperty("max_visits")
    private Integer maxVisits;
    @JsonProperty("min_visits")
    private Integer minVisits;

    @JsonProperty("max_total_area")
    private Double maxTotalArea;
    @JsonProperty("min_total_area")
    private Double minTotalArea;

    @JsonProperty("max_building_area")
    private Double maxBuildingArea;
    @JsonProperty("min_building_area")
    private Double minBuildingArea;

    @JsonProperty("min_living_area")
    private Double minLivingArea;
    @JsonProperty("max_living_area")
    private Double maxLivingArea;

    @JsonProperty("offer_status")
    private OfferStatus offerStatus;
    @JsonProperty("condition_status")
    private ConditionStatus conditionStatus;
    @JsonProperty("property_type")
    private PropertyType propertyType;
    //
    @JsonProperty("max_total_rooms")
    private Integer maxTotalRooms;
    @JsonProperty("min_total_rooms")
    private Integer minTotalRooms;

    @JsonProperty("max_total_bedrooms")
    private Integer maxTotalBedrooms;
    @JsonProperty("min_total_bedrooms")
    private Integer minTotalBedrooms;

    @JsonProperty("max_total_kitchens")
    private Integer maxTotalKitchens;
    @JsonProperty("min_total_kitchens")
    private Integer minTotalKitchens;

}
