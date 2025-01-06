package cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyCountsDTO {
    @NotNull(message = "property_statistics_id must be defined")
    @JsonProperty("property_statistics_id")
    private UUID propertyStatisticsId;

    @NotNull(message = "visits must be defined")
    @Min(value = 0, message = "visits must be at least 0")
    private Integer visits;

    @NotNull(message = "likes must be defined")
    @Min(value = 0, message = "likes must be at least 0")
    private Integer likes;

    @NotNull(message = "dislikes must be defined")
    @Min(value = 0, message = "dislikes must be at least 0")
    private Integer dislikes;
}
