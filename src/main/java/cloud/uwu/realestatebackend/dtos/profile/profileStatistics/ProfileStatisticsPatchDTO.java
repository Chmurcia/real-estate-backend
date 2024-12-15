package cloud.uwu.realestatebackend.dtos.profile.profileStatistics;

import cloud.uwu.realestatebackend.entities.profile.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileStatisticsDTO {
    private Integer totalRatings;

    private Integer totalOffers;

    private Integer totalFavorites;

    private Integer totalViews;

    private Integer totalTrusts;

    private Integer totalPosts;

    private Integer totalActionPoints;
}
