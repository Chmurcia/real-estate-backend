package cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyCountsDTO {
    @NotNull(message = "visits must be defined")
    @Min(value = 0, message = "visits must be at least 0")
    private Integer visits;

    @NotNull(message = "likes must be defined")
    @Min(value = 0, message = "likes must be at least 0")
    private Integer likes;

    @NotNull(message = "dislikes must be defined")
    @Min(value = 0, message = "dislikes must be at least 0")
    private Integer dislikes;

    @NotNull(message = "comments must be defined")
    @Min(value = 0, message = "comments must be at least 0")
    private Integer comments;
}
