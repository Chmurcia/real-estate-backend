
package cloud.uwu.realestatebackend.dtos.profile.profileNotification;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileNotificationResponseDTO {

    private UUID id;

    private UUID profileId;

    private String notificationName;

    private UUID notificationId;

    private String notificationDescription;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
