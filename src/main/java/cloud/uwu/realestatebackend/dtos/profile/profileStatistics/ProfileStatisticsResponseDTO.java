package cloud.uwu.realestatebackend.dtos.profile.profileStatistics;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileStatisticsPatchDTO {
    private Integer totalRatings;

    private Integer totalOffers;

    private Integer totalFavorites;

    private Integer totalViews;

    private Integer totalTrusts;

    private Integer totalPosts;

    private Integer totalActionPoints;
}
