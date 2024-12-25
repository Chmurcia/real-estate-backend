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
    @NotNull(message = "profile_id must be defined")
    @JsonProperty("profile_id")
    private UUID profileId;

    @NotNull(message = "notification_id must be defined")
    @JsonProperty("notification_id")
    private UUID notificationId;

    @NotBlank(message = "notification_name must be defined")
    @Size(min = 1, max = 150, message = "notification_name must contain between 1 and 50 characters")
    @JsonProperty("notification_name")
    private String notificationName;

    @NotBlank(message = "notification_description must be defined")
    @Size(min = 1, max = 150, message = "notification_description must contain between 1 and 150 characters")
    @JsonProperty("notification_description")
    private String notificationDescription;
}
