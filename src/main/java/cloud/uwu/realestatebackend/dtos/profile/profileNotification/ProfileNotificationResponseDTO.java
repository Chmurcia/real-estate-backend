
package cloud.uwu.realestatebackend.dtos.profile.profileNotification;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("profile_id")
    private UUID profileId;

    @JsonProperty("notification_name")
    private String notificationName;

    @JsonProperty("notification_id")
    private UUID notificationId;

    @JsonProperty("notification_description")
    private String notificationDescription;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
