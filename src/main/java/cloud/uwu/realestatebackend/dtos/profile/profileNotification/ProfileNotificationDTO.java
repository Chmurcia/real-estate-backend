package cloud.uwu.realestatebackend.dtos.profile.profileNotification;

import cloud.uwu.realestatebackend.entities.profile.Profile;
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
public class ProfileNotificationDTO {
    private UUID profileId;

    @NotBlank(message = "notificationName must be defined")
    @Size(min = 1, max = 150, message = "notificationName must be between 1 and 50 characters")
    private String notificationName;

    @NotNull(message = "notificationId must be defined")
    private UUID notificationId;

    @NotBlank(message = "notificationDescription must be defined")
    @Size(min = 1, max = 150, message = "notificationDescription must be between 1 and 150 characters")
    private String notificationDescription;
}
