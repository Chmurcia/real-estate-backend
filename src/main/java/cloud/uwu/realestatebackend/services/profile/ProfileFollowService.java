package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileFollow.ProfileFollowDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileFollow.ProfileFollowResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileFollow;
import cloud.uwu.realestatebackend.exceptions.AlreadyExistException;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileFollowMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileFollowRepository;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileFollowService {
    private final ProfileFollowRepository profileFollowRepository;
    private final ProfileRepository profileRepository;
    private final ProfileFollowMapper profileFollowMapper;

    public Page<ProfileFollowResponseDTO> getProfileFollowsByFollowerId(
            UUID followerId, Integer page, Integer size) {
        getProfile(followerId);

        page = (page != null) ? Math.max(page, 0) : 0;
        size = (size != null && size > 0) ? size : 50;

        PageRequest pageable = PageRequest.of(page, size);

        return profileFollowRepository.getProfileFollowsByFollower_Id(followerId, pageable)
                .map(profileFollowMapper::profileFollowToProfileFollowResponseDTO);
    }

    public Page<ProfileFollowResponseDTO> getProfileFollowsByFollowingId(
            UUID followingId, Integer page, Integer size) {
        getProfile(followingId);

        page = (page != null) ? Math.max(page, 0) : 0;
        size = (size != null && size > 0) ? size : 50;

        PageRequest pageable = PageRequest.of(page, size);

        return profileFollowRepository.getProfileFollowsByFollowing_Id(followingId, pageable)
                .map(profileFollowMapper::profileFollowToProfileFollowResponseDTO);
    }

    public ProfileFollowResponseDTO createProfileFollow(ProfileFollowDTO profileFollowDTO) {
        Profile follower = getProfile(profileFollowDTO.getFollowerId());
        Profile following = getProfile(profileFollowDTO.getFollowingId());

        checkProfileFollowAlreadyExists(follower.getId(), following.getId());

        ProfileFollow profileFollow = ProfileFollow.builder()
                .follower(follower)
                .following(following)
                .build();

        ProfileFollow savedProfileFollow = profileFollowRepository
                .saveAndFlush(profileFollow);

        return profileFollowMapper.profileFollowToProfileFollowResponseDTO(savedProfileFollow);
    }

    public void deleteProfileFollow(UUID followerId, UUID followingId) {
        ProfileFollow profileFollow = getProfileFollow(followerId, followingId);

        profileFollowRepository.deleteById(profileFollow.getId());
    }

    //

    private Profile getProfile(UUID id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profile not found"));
    }

    private ProfileFollow getProfileFollow(UUID followerId, UUID followingId) {
        return profileFollowRepository.getProfileFollowByFollowerIdAndFollowingId(followerId, followingId)
                .orElseThrow(() -> new NotFoundException("ProfileFollow not found"));
    }

    private void checkProfileFollowAlreadyExists(UUID followerId, UUID followingId) {
        profileFollowRepository
                .getProfileFollowByFollowerIdAndFollowingId(followerId, followingId)
                .ifPresent(p -> {
                    throw new AlreadyExistException("ProfileFollow already exists");
                });
    }
}
