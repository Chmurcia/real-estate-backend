package cloud.uwu.realestatebackend.dtos.user.userNotification;

import cloud.uwu.realestatebackend.entities.user.userEnums.NotificationReferName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNotificationDTO {

    @NotNull(message = "Id of user must be defined")
    private UUID userId;

    @NotBlank(message = "Name of reference must be defined")
    @Enumerated(EnumType.STRING)
    private NotificationReferName referName;

    @NotNull(message = "Id of reference must be defined")
    private UUID referId;

    @NotNull(message = "isSeen must be defined")
    private Boolean isSeen;

}

