package cloud.uwu.realestatebackend.dtos.profile.profileStatistics;

import cloud.uwu.realestatebackend.entities.profile.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileStatisticsDTO {
    @NotNull
    @Min(value = 0, message = "Amount of ratings must be at least 0")
    private Integer totalRatings;

    @NotNull
    @Min(value = 0, message = "Amount of offers must be at least 0")
    private Integer totalOffers;

    @NotNull
    @Min(value = 0, message = "Amount of views must be at least 0")
    private Integer totalViews;

    @NotNull
    @Min(value = 0, message = "Amount of trusts must be at least 0")
    private Integer totalTrusts;

    @NotNull
    @Min(value = 0, message = "Amount of posts must be at least 0")
    private Integer totalPosts;

    @NotNull
    @Min(value = 0, message = "Amount of action points must be at least 0")
    private Integer totalActionPoints;
}
