
package cloud.uwu.realestatebackend.dtos.profile.profileNotification;

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
    @Size(min = 1, max = 50, message = "notificationName must be between 1 and 50 characters")
    private String notificationName;

    private UUID notificationId;

    @Size(min = 1, max = 150, message = "notificationDescription must be between 1 and 150 characters")
    private String notificationDescription;
}
