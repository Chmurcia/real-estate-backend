package cloud.uwu.realestatebackend.mappers.user;

import cloud.uwu.realestatebackend.dtos.user.userNotification.UserNotificationDTO;
import cloud.uwu.realestatebackend.dtos.user.userNotification.UserNotificationResponseDTO;
import cloud.uwu.realestatebackend.entities.user.User;
import cloud.uwu.realestatebackend.entities.user.UserNotification;
import cloud.uwu.realestatebackend.entities.user.userEnums.NotificationReferName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserNotificationMapperTest {

    UserNotificationMapper userNotificationMapper = Mappers
            .getMapper(UserNotificationMapper.class);

    @Test
    void userNotificationToUserNotificationDTO() {
        UUID userId = UUID.randomUUID();
        UUID referId = UUID.randomUUID();

        UserNotification userNotification = UserNotification.builder()
                .user(User.builder().id(userId).build())
                .referName(NotificationReferName.POST_COMMENT)
                .referId(referId)
                .isSeen(true)
                .build();

        UserNotificationDTO userNotificationDTO = UserNotificationDTO.builder()
                .userId(userId)
                .referName(NotificationReferName.POST_COMMENT)
                .referId(referId)
                .isSeen(true)
                .build();

        UserNotificationDTO mappedUserNotification = userNotificationMapper
                .userNotificationToUserNotificationDTO(userNotification);

        assertThat(mappedUserNotification).isNotNull();

        assertEquals(userNotificationDTO.getUserId(),
                mappedUserNotification.getUserId());

        assertEquals(userNotificationDTO.getReferName(),
                mappedUserNotification.getReferName());

        assertEquals(userNotificationDTO.getReferId(),
                mappedUserNotification.getReferId());

        assertEquals(userNotificationDTO.getIsSeen(),
                mappedUserNotification.getIsSeen());
    }

    @Test
    void userNotificationToUserNotificationResponseDTO() {
        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID referId = UUID.randomUUID();

        UserNotification userNotification = UserNotification.builder()
                .id(id)
                .user(User.builder().id(userId).build())
                .referName(NotificationReferName.POST_COMMENT)
                .referId(referId)
                .isSeen(true)
                .build();

        UserNotificationResponseDTO userNotificationResponseDTO = UserNotificationResponseDTO
                .builder()
                .id(id)
                .userId(userId)
                .referName(NotificationReferName.POST_COMMENT)
                .referId(referId)
                .isSeen(true)
                .build();

        UserNotificationResponseDTO mappedUserNotificationResponse = userNotificationMapper
                .userNotificationToUserNotificationResponseDTO(userNotification);

        assertThat(userNotificationResponseDTO).isNotNull();

        assertEquals(userNotificationResponseDTO.getId(),
                mappedUserNotificationResponse.getId());

        assertEquals(userNotificationResponseDTO.getUserId(),
                mappedUserNotificationResponse.getUserId());

        assertEquals(userNotificationResponseDTO.getReferName(),
                mappedUserNotificationResponse.getReferName());

        assertEquals(userNotificationResponseDTO.getReferId(),
                mappedUserNotificationResponse.getReferId());

        assertEquals(userNotificationResponseDTO.getIsSeen(),
                mappedUserNotificationResponse.getIsSeen());
    }
}