package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileActivityMapper {
    ProfileActivityDTO profileActivityToProfileActivityDTO(ProfileActivity profileActivity);

    ProfileActivityResponseDTO profileActivityToProfileActivityResponseDTO(ProfileActivity profileActivity);
}
