package cloud.uwu.realestatebackend.mappers.user;

import cloud.uwu.realestatebackend.dtos.user.userNotification.UserNotificationDTO;
import cloud.uwu.realestatebackend.dtos.user.userNotification.UserNotificationResponseDTO;
import cloud.uwu.realestatebackend.entities.user.UserNotification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserNotificationMapper {
    @Mapping(source = "user.id", target = "userId")
    UserNotificationDTO userNotificationToUserNotificationDTO(UserNotification userNotification);
    UserNotification userNotificationDTOToUserNotification(UserNotificationDTO userNotificationDTO);

    @Mapping(source = "user.id", target = "userId")
    UserNotificationResponseDTO userNotificationToUserNotificationResponseDTO(UserNotification userNotification);
}
