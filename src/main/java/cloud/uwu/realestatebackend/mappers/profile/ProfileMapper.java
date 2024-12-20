package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profile.ProfileDTO;
import cloud.uwu.realestatebackend.dtos.profile.profile.ProfileResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDTO profileToProfileDTO(Profile profile);

    @Mapping(source = "profileSettings.id", target = "profileSettingsId")
    @Mapping(source = "profileStatistics.id", target = "profileStatisticsId")
    ProfileResponseDTO profileToProfileResponseDTO(Profile profile);
}
