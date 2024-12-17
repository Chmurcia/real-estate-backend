package cloud.uwu.realestatebackend.dtos.user.userNotification;

import cloud.uwu.realestatebackend.entities.user.userEnums.NotificationReferName;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNotificationPatchDTO {

    @Enumerated(EnumType.STRING)
    private NotificationReferName referName;

    private UUID referId;

    private Boolean isSeen;
}
