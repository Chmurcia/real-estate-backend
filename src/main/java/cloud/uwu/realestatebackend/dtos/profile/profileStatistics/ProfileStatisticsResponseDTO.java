package cloud.uwu.realestatebackend.dtos.profile.profileStatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileStatisticsResponseDTO {
    private UUID id;

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

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}

