package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileFollow.ProfileFollowResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileFollow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileFollowMapper {
    @Mapping(source = "follower.id", target = "followerId")
    @Mapping(source = "following.id", target = "followingId")
    ProfileFollowResponseDTO profileFollowToProfileFollowResponseDTO(ProfileFollow profileFollow);
}
