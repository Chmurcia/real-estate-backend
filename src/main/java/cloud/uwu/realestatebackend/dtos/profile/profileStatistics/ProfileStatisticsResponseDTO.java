package cloud.uwu.realestatebackend.dtos.profile.profileStatistics;

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

    private Integer totalRatings;

    private Integer totalOffers;

    private Integer totalViews;

    private Integer totalTrusts;

    private Integer totalPosts;

    private Integer totalActionPoints;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
