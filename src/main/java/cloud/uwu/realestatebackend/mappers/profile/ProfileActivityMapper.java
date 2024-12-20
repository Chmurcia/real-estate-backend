package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileActivityMapper {
    @Mapping(source = "profile.id", target = "profileId")
    ProfileActivityDTO profileActivityToProfileActivityDTO(ProfileActivity profileActivity);

    @Mapping(source = "profile.id", target = "profileId")
    ProfileActivityResponseDTO profileActivityToProfileActivityResponseDTO(ProfileActivity profileActivity);
}
