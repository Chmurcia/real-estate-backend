package cloud.uwu.realestatebackend.dtos.profile.profileStatistics;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
