package cloud.uwu.realestatebackend.dtos.property.statistics.propertyCountsHistory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyCountsHistoryDTO {
    @NotNull(message = "visits must be defined")
    @Min(value = 1, message = "visits must be at least 1")
    private Integer visits;

    @NotNull(message = "likes must be defined")
    @Min(value = 1, message = "likes must be at least 1")
    private Integer likes;

    @NotNull(message = "dislikes must be defined")
    @Min(value = 1, message = "dislikes must be at least 1")
    private Integer dislikes;
}
