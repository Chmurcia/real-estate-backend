package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileNotification.ProfileNotificationDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileNotification.ProfileNotificationResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileNotification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileNotificationMapper {
    @Mapping(source = "profile.id", target = "profileId")
    ProfileNotificationDTO profileNotificationToProfileNotificationDTO(ProfileNotification profileNotification);

    @Mapping(source = "profile.id", target = "profileId")
    ProfileNotificationResponseDTO profileNotificationToProfileNotificationResponseDTO(ProfileNotification profileNotification);
}
