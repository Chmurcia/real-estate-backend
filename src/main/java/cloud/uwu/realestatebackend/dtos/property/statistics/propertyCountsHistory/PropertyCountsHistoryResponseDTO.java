package cloud.uwu.realestatebackend.dtos.property.statistics.propertyCountsHistory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyCountsHistoryResponseDTO {
    private UUID id;

    private Integer visits;

    private Integer likes;

    private Integer dislikes;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}
