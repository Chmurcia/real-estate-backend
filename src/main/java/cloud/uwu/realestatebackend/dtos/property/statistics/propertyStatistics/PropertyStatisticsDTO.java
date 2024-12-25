package cloud.uwu.realestatebackend.dtos.property.statistics.propertyStatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyStatisticsDTO {
    @PastOrPresent(message = "last_visited_at must be now or in the past")
    @JsonProperty("last_visited_at")
    private ZonedDateTime lastVisitedAt;
}
