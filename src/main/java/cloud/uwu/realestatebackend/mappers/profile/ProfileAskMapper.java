package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileAsk.ProfileAskDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileAsk.ProfileAskResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileAsk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileAskMapper {
    @Mapping(source = "profile.id", target = "profileId")
    ProfileAskDTO profileAskToProfileAskDTO(ProfileAsk profileAsk);

    @Mapping(source = "profile.id", target = "profileId")
    ProfileAskResponseDTO profileAskToProfileAskResponseDTO(ProfileAsk profileAsk);
}
