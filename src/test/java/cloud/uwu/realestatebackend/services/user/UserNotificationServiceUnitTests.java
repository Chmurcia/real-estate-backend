package cloud.uwu.realestatebackend.services.user;

import cloud.uwu.realestatebackend.dtos.user.userNotification.UserNotificationDTO;
import cloud.uwu.realestatebackend.dtos.user.userNotification.UserNotificationPatchDTO;
import cloud.uwu.realestatebackend.dtos.user.userNotification.UserNotificationResponseDTO;
import cloud.uwu.realestatebackend.entities.user.User;
import cloud.uwu.realestatebackend.entities.user.UserNotification;
import cloud.uwu.realestatebackend.entities.user.userEnums.NotificationReferName;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.user.UserNotificationMapper;
import cloud.uwu.realestatebackend.repositories.user.UserNotificationRepository;
import cloud.uwu.realestatebackend.repositories.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserNotificationServiceUnitTests {

    @Mock
    private UserNotificationRepository userNotificationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserNotificationMapper userNotificationMapper;

    @InjectMocks
    private UserNotificationService userNotificationService;

    @Test
    void getUserNotificationById() {
        UUID id = UUID.randomUUID();

        User user = User.builder().build();

        UserNotification userNotification = UserNotification.builder()
                .user(user)
                .referName(NotificationReferName.POST_COMMENT)
                .referId(UUID.randomUUID())
                .isSeen(false)
                .build();

        UserNotificationResponseDTO userNotificationResponseDTO =
                UserNotificationResponseDTO.builder()
                .id(userNotification.getId())
                .userId(userNotification.getUser().getId())
                .referName(userNotification.getReferName())
                .referId(userNotification.getReferId())
                .isSeen(userNotification.getIsSeen())
                .createdAt(userNotification.getCreatedAt())
                .updatedAt(userNotification.getUpdatedAt())
                .build();

        when(userNotificationRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(userNotification));
        when(userNotificationMapper
                .userNotificationToUserNotificationResponseDTO(any(UserNotification.class)))
                .thenReturn(userNotificationResponseDTO);

        UserNotificationResponseDTO foundUserNotification = userNotificationService
                .getUserNotificationById(id);

        verify(userNotificationRepository).findById(id);

        assertThat(foundUserNotification).isNotNull();
        assertEquals(foundUserNotification.getReferName(), userNotification.getReferName());
        assertEquals(foundUserNotification.getReferId(), userNotification.getReferId());
        assertNotEquals(foundUserNotification.getReferName(), NotificationReferName.CHAT_MESSAGE);
        assertNotEquals(foundUserNotification.getReferId(), UUID.randomUUID());
    }

    @Test
    void getUserNotificationById_ShouldThrowNotFoundException() {
        when(userNotificationRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userNotificationService
                .getUserNotificationById(UUID.randomUUID()));
    }

    @Test
    void getNotificationsByUserId() {
        UUID id = UUID.randomUUID();

        User user = User.builder().id(id).build();

        List<UserNotification> userNotifications = new ArrayList<>();
        userNotifications.add(UserNotification.builder().id(UUID.randomUUID()).build());
        userNotifications.add(UserNotification.builder().id(UUID.randomUUID()).build());
        userNotifications.add(UserNotification.builder().id(UUID.randomUUID()).build());

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
        when(userNotificationRepository.getUserNotificationsByUserId(any(UUID.class)))
                .thenReturn(userNotifications);
        when(userNotificationMapper
                .userNotificationToUserNotificationResponseDTO(any(UserNotification.class)))
                .thenReturn(UserNotificationResponseDTO.builder().build());

        List<UserNotificationResponseDTO> foundUserNotifications = userNotificationService
                .getNotificationsByUserId(id);

        verify(userRepository).findById(id);
        verify(userNotificationRepository).getUserNotificationsByUserId(id);

        assertThat(foundUserNotifications).isNotNull();
        assertThat(foundUserNotifications.size()).isGreaterThan(0);
        assertEquals(3, foundUserNotifications.size());
    }

    @Test
    void getNotificationsByUserId_ShouldThrowNotFoundException() {
        when(userRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userNotificationService
                .getNotificationsByUserId(UUID.randomUUID()));
    }

    @Test
    void createUserNotification() {
        User user = User.builder().id(UUID.randomUUID()).build();

        UserNotificationDTO userNotificationDTO = UserNotificationDTO.builder()
                .userId(user.getId())
                .referName(NotificationReferName.POST_COMMENT)
                .referId(UUID.randomUUID())
                .isSeen(false)
                .build();

        UserNotification userNotification = UserNotification.builder()
                .id(UUID.randomUUID())
                .user(user)
                .referName(NotificationReferName.POST_COMMENT)
                .referId(UUID.randomUUID())
                .isSeen(false)
                .build();

        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));
        when(userNotificationRepository.save(any(UserNotification.class)))
                .thenReturn(userNotification);

        UserNotificationResponseDTO createdUserNotification = userNotificationService
                .createUserNotification(userNotificationDTO);

        verify(userRepository).findById(user.getId());
        verify(userNotificationRepository).save(any(UserNotification.class));

        assertThat(createdUserNotification).isNotNull();
        assertEquals(createdUserNotification.getUserId(), userNotification.getUser().getId());
        assertNotEquals(createdUserNotification.getUserId(), UUID.randomUUID());
        assertEquals(createdUserNotification.getReferId(), userNotification.getReferId());
        assertNotEquals(createdUserNotification.getReferId(), UUID.randomUUID());
        assertEquals(createdUserNotification.getReferName(), userNotification.getReferName());
        assertNotEquals(createdUserNotification.getReferName(), NotificationReferName.CHAT_MESSAGE);
    }

    @Test
    void updateUserNotification() {
        UUID id = UUID.randomUUID();
        User user = User.builder().id(id).build();

        UserNotificationDTO userNotificationDTO = UserNotificationDTO.builder()
                .userId(user.getId())
                .referName(NotificationReferName.POST_COMMENT)
                .referId(UUID.randomUUID())
                .isSeen(true)
                .build();

        UserNotification userNotification = UserNotification.builder()
                .id(UUID.randomUUID())
                .user(user)
                .referName(NotificationReferName.POST_COMMENT)
                .referId(UUID.randomUUID())
                .isSeen(false)
                .build();

        when(userNotificationRepository.findById(userNotification.getId()))
                .thenReturn(Optional.of(userNotification));
        when(userNotificationRepository.save(any(UserNotification.class)))
                .thenReturn(userNotification);

        userNotificationService.updateUserNotification(userNotification.getId(), userNotificationDTO);

        verify(userNotificationRepository).save(any(UserNotification.class));
        verify(userNotificationRepository).findById(userNotification.getId());
    }

    @Test
    void updateUserNotification_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> userNotificationService
                .updateUserNotification(UUID.randomUUID(),
                        UserNotificationDTO.builder().build()));
    }

    @Test
    void updateUserNotificationReferId_ShouldThrowNullException() {
        UUID id = UUID.randomUUID();
        User user = User.builder().id(id).build();
        UserNotification userNotification = UserNotification.builder().build();

        when(userNotificationRepository.findById(id))
                .thenReturn(Optional.of(userNotification));

        UserNotificationDTO userNotificationDTO = UserNotificationDTO.builder()
                .userId(user.getId())
                .referName(NotificationReferName.POST_COMMENT)
                .isSeen(false)
                .build();

        assertThrows(NullException.class, () -> userNotificationService
                .updateUserNotification(userNotificationDTO.getUserId(), userNotificationDTO));
    }

    @Test
    void updateUserNotificationReferName_ShouldThrowNullException() {
        UUID id = UUID.randomUUID();
        User user = User.builder().id(id).build();
        UserNotification userNotification = UserNotification.builder().build();

        when(userNotificationRepository.findById(id))
                .thenReturn(Optional.of(userNotification));

        UserNotificationDTO userNotificationDTO = UserNotificationDTO.builder()
                .userId(user.getId())
                .referId(UUID.randomUUID())
                .isSeen(false)
                .build();

        assertThrows(NullException.class, () -> userNotificationService
                .updateUserNotification(userNotificationDTO.getUserId(), userNotificationDTO));
    }

    @Test
    void updateUserNotificationIsSeen_ShouldThrowNullException() {
        UUID id = UUID.randomUUID();
        User user = User.builder().id(id).build();
        UserNotification userNotification = UserNotification.builder().build();

        when(userNotificationRepository.findById(id))
                .thenReturn(Optional.of(userNotification));

        UserNotificationDTO userNotificationDTO = UserNotificationDTO.builder()
                .userId(user.getId())
                .referName(NotificationReferName.POST_COMMENT)
                .referId(UUID.randomUUID())
                .build();

        assertThrows(NullException.class, () -> userNotificationService
                .updateUserNotification(userNotificationDTO.getUserId(), userNotificationDTO));
    }

    @Test
    void patchUserNotification() {
        UUID id = UUID.randomUUID();
        User user = User.builder().id(id).build();

        UserNotificationPatchDTO userNotificationPatchDTO = UserNotificationPatchDTO.builder()
                .referName(NotificationReferName.POST_COMMENT)
                .referId(UUID.randomUUID())
                .isSeen(false)
                .build();

        UserNotification userNotification = UserNotification.builder()
                .id(UUID.randomUUID())
                .user(user)
                .referName(NotificationReferName.POST_COMMENT)
                .referId(UUID.randomUUID())
                .isSeen(false)
                .build();

        when(userNotificationRepository.findById(userNotification.getId()))
                .thenReturn(Optional.of(userNotification));

        userNotificationService
                .patchUserNotification(userNotification.getId(), userNotificationPatchDTO);

        verify(userNotificationRepository).save(any(UserNotification.class));
        verify(userNotificationRepository).findById(userNotification.getId());
}

    @Test
    void patchUserNotification_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> userNotificationService
                .patchUserNotification(UUID.randomUUID(),
                        UserNotificationPatchDTO.builder().build()));
    }

    @Test
    void deleteUserNotification() {
        UUID id = UUID.randomUUID();

        UserNotification userNotification = UserNotification.builder()
                .id(id)
                .build();

        when(userNotificationRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(userNotification));

        userNotificationService.deleteUserNotification(userNotification.getId());

        verify(userNotificationRepository).deleteById(userNotification.getId());
    }

    @Test
    void deleteUserNotification_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> userNotificationService
                .deleteUserNotification(UUID.randomUUID()));
    }
}