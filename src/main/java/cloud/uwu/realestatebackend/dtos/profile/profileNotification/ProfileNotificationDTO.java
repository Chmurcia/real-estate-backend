package cloud.uwu.realestatebackend.dtos.profile.profileNotification;

import cloud.uwu.realestatebackend.entities.profile.Profile;
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
public class ProfileNotificationDTO {
    @JsonProperty("profile_id")
    private UUID profileId;

    @NotNull(message = "notificationId must be defined")
    @JsonProperty("notification_id")
    private UUID notificationId;

    @NotBlank(message = "notificationName must be defined")
    @Size(min = 1, max = 150, message = "notificationName must be between 1 and 50 characters")
    @JsonProperty("notification_name")
    private String notificationName;

    @NotBlank(message = "notificationDescription must be defined")
    @Size(min = 1, max = 150, message = "notificationDescription must be between 1 and 150 characters")
    @JsonProperty("notification_description")
    private String notificationDescription;
}
