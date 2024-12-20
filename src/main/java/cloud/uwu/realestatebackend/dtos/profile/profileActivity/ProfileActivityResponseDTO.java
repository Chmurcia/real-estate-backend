package cloud.uwu.realestatebackend.dtos.profile.profileActivity;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileActivityResponseDTO {
    private UUID id;

    private UUID profileId;

    private String activityTitle;

    private String activityDescription;

    private ZonedDateTime activityDate;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
