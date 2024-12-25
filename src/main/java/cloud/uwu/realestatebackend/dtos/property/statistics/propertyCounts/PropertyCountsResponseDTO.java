package cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyCountsResponseDTO {
    private UUID id;

    private Integer visits;

    private Integer likes;

    private Integer dislikes;

    private Integer comments;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
