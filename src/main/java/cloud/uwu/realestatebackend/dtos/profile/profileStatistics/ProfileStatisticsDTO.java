package cloud.uwu.realestatebackend.dtos.profile.profileStatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileStatisticsDTO {
    @NotNull(message = "total_ratings must be defined")
    @Min(value = 0, message = "total_ratings must be at least 0")
    @JsonProperty("total_ratings")
    private Integer totalRatings;

    @NotNull(message = "total_offers must be defined")
    @Min(value = 0, message = "total_offers must be at least 0")
    @JsonProperty("total_offers")
    private Integer totalOffers;

    @NotNull(message = "total_views must be defined")
    @Min(value = 0, message = "total_views must be at least 0")
    @JsonProperty("total_views")
    private Integer totalViews;

    @NotNull(message = "total_trusts must be defined")
    @Min(value = 0, message = "total_trusts must be at least 0")
    @JsonProperty("total_trusts")
    private Integer totalTrusts;

    @NotNull(message = "total_posts must be defined")
    @Min(value = 0, message = "total_posts must be at least 0")
    @JsonProperty("total_posts")
    private Integer totalPosts;

    @NotNull(message = "total_action_points must be defined")
    @Min(value = 0, message = "total_action_points must be at least 0")
    @JsonProperty("total_action_points")
    private Integer totalActionPoints;
}

