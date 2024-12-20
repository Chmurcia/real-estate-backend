
package cloud.uwu.realestatebackend.dtos.profile.profileNotification;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileNotificationPatchDTO {
    @JsonProperty("notification_id")
    private UUID notificationId;

    @Size(min = 1, max = 50, message = "notificationName must be between 1 and 50 characters")
    @JsonProperty("notification_name")
    private String notificationName;

    @Size(min = 1, max = 150, message = "notificationDescription must be between 1 and 150 characters")
    @JsonProperty("notification_description")
    private String notificationDescription;
}
