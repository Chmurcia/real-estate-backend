package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRateDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRateResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileRateMapper {
    ProfileRateDTO profileRateToProfileRateDTO(ProfileRate profileRate);

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    ProfileRateResponseDTO profileRateToProfileRateResponseDTO(ProfileRate profileRate);
}
