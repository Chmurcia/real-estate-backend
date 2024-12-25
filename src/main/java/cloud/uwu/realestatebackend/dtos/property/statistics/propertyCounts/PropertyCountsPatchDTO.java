package cloud.uwu.realestatebackend.dtos.property.statistics.propertyCounts;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyCountsPatchDTO {
    @Min(value = 0, message = "visits must be at least 0")
    private Integer visits;

    @Min(value = 0, message = "likes must be at least 0")
    private Integer likes;

    @Min(value = 0, message = "dislikes must be at least 0")
    private Integer dislikes;

    @Min(value = 0, message = "comments must be at least 0")
    private Integer comments;
}
