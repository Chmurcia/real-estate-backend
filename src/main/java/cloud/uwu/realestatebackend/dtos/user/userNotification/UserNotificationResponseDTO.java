package cloud.uwu.realestatebackend.dtos.user.userNotification;

import cloud.uwu.realestatebackend.entities.user.userEnums.NotificationReferName;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNotificationResponseDTO {

    private UUID id;

    private UUID userId;

    @Enumerated(EnumType.STRING)
    private NotificationReferName referName;

    private UUID referId;

    private Boolean isSeen;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}
