package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRateDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRateResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileRateMapper {
    @Mapping(source = "profile.id", target = "profileId")
    ProfileRateDTO profileRateToProfileRateDTO(ProfileRate profileRate);

    @Mapping(source = "profile.id", target = "profileId")
    ProfileRateResponseDTO profileRateToProfileRateResponseDTO(ProfileRate profileRate);
}
