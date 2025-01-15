package cloud.uwu.realestatebackend.dtos.property.property;

import cloud.uwu.realestatebackend.dtos.property.location.location.PropertyLocationResponseDTO;
import cloud.uwu.realestatebackend.dtos.property.multimedia.PropertyMultimediaResponseDTO;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.OfferStatus;
import cloud.uwu.realestatebackend.entities.property.propertyEnum.PropertyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertySmallResponseDTO {
    private UUID id;

    @JsonProperty("property_type")
    private PropertyType propertyType;

    @JsonProperty("offer_status")
    private OfferStatus offerStatus;

    private String title;

    private String description;

    private Double price;

    private Integer visits;

    @JsonProperty("total_area")
    private Double totalArea;

    @JsonProperty("total_rooms")
    private Double totalRooms;

    @JsonProperty("location")
    private PropertyLocationResponseDTO propertyLocationResponseDTO;

    @JsonProperty("multimedia")
    private PropertyMultimediaResponseDTO propertyMultimediaResponseDTO;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
