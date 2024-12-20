package cloud.uwu.realestatebackend.dtos.profile.profileStatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileStatisticsPatchDTO {
    @JsonProperty("total_ratings")
    private Integer totalRatings;

    @JsonProperty("total_offers")
    private Integer totalOffers;

    @JsonProperty("total_views")
    private Integer totalViews;

    @JsonProperty("total_trusts")
    private Integer totalTrusts;

    @JsonProperty("total_posts")
    private Integer totalPosts;

    @JsonProperty("total_action_points")
    private Integer totalActionPoints;
}
