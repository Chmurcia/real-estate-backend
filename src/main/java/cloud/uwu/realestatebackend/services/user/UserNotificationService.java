package cloud.uwu.realestatebackend.services.user;

import cloud.uwu.realestatebackend.dtos.user.userNotification.UserNotificationDTO;
import cloud.uwu.realestatebackend.dtos.user.userNotification.UserNotificationPatchDTO;
import cloud.uwu.realestatebackend.dtos.user.userNotification.UserNotificationResponseDTO;
import cloud.uwu.realestatebackend.entities.user.User;
import cloud.uwu.realestatebackend.entities.user.UserNotification;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.user.UserNotificationMapper;
import cloud.uwu.realestatebackend.repositories.user.UserNotificationRepository;
import cloud.uwu.realestatebackend.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserNotificationService {
    private final UserNotificationRepository userNotificationRepository;
    private final UserRepository userRepository;
    private final UserNotificationMapper userNotificationMapper;

    public UserNotificationResponseDTO getUserNotificationById(UUID id) {
        UserNotification userNotification = getUserNotification(id);

        return userNotificationMapper
                .userNotificationToUserNotificationResponseDTO(userNotification);
    }

    public List<UserNotificationResponseDTO> getNotificationsByUserId(UUID id) {
        getUser(id);
        return userNotificationRepository.getUserNotificationsByUserId(id).stream()
                .map(userNotificationMapper::userNotificationToUserNotificationResponseDTO)
                .toList();
    }

    public UserNotificationResponseDTO createUserNotification(
            UserNotificationDTO userNotificationDTO) {
        User user = getUser(userNotificationDTO.getUserId());

        UserNotification userNotification = UserNotification.builder()
                .user(user)
                .referName(userNotificationDTO.getReferName())
                .referId(userNotificationDTO.getReferId())
                .isSeen(userNotificationDTO.getIsSeen())
                .build();

        UserNotification savedUser = userNotificationRepository
                .save(userNotification);

        return UserNotificationResponseDTO.builder()
                .id(savedUser.getId())
                .userId(savedUser.getUser().getId())
                .referName(savedUser.getReferName())
                .referId(savedUser.getReferId())
                .build();
    }

    public void updateUserNotification(UUID id,
                                       UserNotificationDTO userNotificationDTO) {
        UserNotification userNotification = getUserNotification(id);

        if (userNotificationDTO.getReferId() != null) {
            userNotification.setReferId(userNotificationDTO.getReferId());
        } else {
            throw new NullException("referId is null");
        }

        if (userNotificationDTO.getReferName() != null  && StringUtils
                .hasText(userNotificationDTO.getReferName().toString())) {
            userNotification.setReferName(userNotificationDTO.getReferName());
        } else {
            throw new NullException("referName is null");
        }

        if (userNotificationDTO.getIsSeen() != null) {
            userNotification.setIsSeen(userNotificationDTO.getIsSeen());
        } else {
            throw new NullException("isSeen is null");
        }

        userNotificationRepository.save(userNotification);
    }

    public void patchUserNotification(UUID id,
                                      UserNotificationPatchDTO userNotificationPatchDTO) {
        UserNotification userNotification = getUserNotification(id);

        if (userNotificationPatchDTO.getReferId() != null) {
            userNotification.setReferId(userNotificationPatchDTO.getReferId());
        }

        if (userNotificationPatchDTO.getReferName() != null  && StringUtils
                .hasText(userNotificationPatchDTO.getReferName().toString())) {
            userNotification.setReferName(userNotificationPatchDTO.getReferName());
        }

        if (userNotificationPatchDTO.getIsSeen() != null) {
            userNotification.setIsSeen(userNotificationPatchDTO.getIsSeen());
        }

        userNotificationRepository.save(userNotification);
    }

    public void deleteUserNotification(UUID id) {
        getUserNotification(id);
        userNotificationRepository.deleteById(id);
    }

    //

    private UserNotification getUserNotification(UUID id) {
        return userNotificationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("userNotification not found")
        );
    }

    private User getUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found"));
    }
}
