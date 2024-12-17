package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profile.ProfileDTO;
import cloud.uwu.realestatebackend.dtos.profile.profile.ProfileResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDTO profileToProfileDTO(Profile profile);

    ProfileResponseDTO profileToProfileResponseDTO(Profile profile);
}
