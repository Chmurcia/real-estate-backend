package cloud.uwu.realestatebackend.dtos.other.post;

import cloud.uwu.realestatebackend.dtos.property.neighbourhood.neighbourhood.PropertyNeighbourhoodDTO;
import cloud.uwu.realestatebackend.dtos.property.property.PropertyDTO;
import cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics.PropertyStatisticsDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FullPropertyDTO {
    @Valid
    @NotNull
    @JsonProperty("property")
    PropertyDTO propertyDTO;

    @Valid
    @NotNull
    @JsonProperty("neighbourhood")
    PropertyNeighbourhoodDTO propertyNeighbourhoodDTO;

    @Valid
    @NotNull
    @JsonProperty("details")
    DetailsDTO detailsDTO;

    @Valid
    @NotNull
    @JsonProperty("location")
    LocationDTO locationDTO;

    @Valid
    @NotNull
    @JsonProperty("statistics")
    private PropertyStatisticsDTO propertyStatisticsDTO;
}
