package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileSettings.ProfileSettingsDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileSettings.ProfileSettingsResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileSettings;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileSettingsMapper {
    ProfileSettingsDTO profileSettingsToProfileSettingsDTO(ProfileSettings profileSettings);

    ProfileSettingsResponseDTO profileSettingsToProfileSettingsResponseDTO(ProfileSettings profileSettings);
}
