package cloud.uwu.realestatebackend.entities.profile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileStatistics {
    @Id
    @UuidGenerator
    private UUID id;

    @OneToOne(mappedBy = "profileStatistics")
    private Profile profile;

    private Integer totalRatings;

    private Integer totalOffers;

    private Integer totalFavorites;

    private Integer totalViews;

    private Integer totalTrusts;

    private Integer totalPosts;

    private Integer totalActionPoints;

}
