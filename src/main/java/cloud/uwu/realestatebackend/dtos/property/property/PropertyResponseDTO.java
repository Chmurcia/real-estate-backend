package cloud.uwu.realestatebackend.dtos.property.property;

import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.multimedia.PropertyMultimediaResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDetails.PropertyDetailsResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyDistanceTo.PropertyDistanceToResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyPriceRecord.PropertyPriceRecordResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.propertyReview.PropertyReviewResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsResponseDTO;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.OfferStatus;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.PropertyType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyResponseDTO {
    private UUID id;

    @JsonProperty("property_type")
    private PropertyType propertyType;

    @JsonProperty("offer_status")
    private OfferStatus offerStatus;

    private String title;

    private String description;

    private Double price;

    @JsonProperty("details")
    private PropertyDetailsResponseDTO detailsResponseDTO;

    @JsonProperty("neighbourhood")
    private PropertyNeighbourhoodResponseDTO neighbourhoodResponseDTO;

    @JsonProperty("distances_to_places")
    private List<PropertyDistanceToResponseDTO> distanceToResponseDTOS;

    @JsonProperty("reviews")
    private List<PropertyReviewResponseDTO> reviewResponseDTOS;

    @JsonProperty("location")
    private PropertyLocationResponseDTO propertyLocationResponseDTO;

    @JsonProperty("statistics")
    private PropertyStatisticsResponseDTO propertyStatisticsResponseDTO;

    @JsonProperty("multimedia")
    private PropertyMultimediaResponseDTO propertyMultimediaResponseDTO;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
