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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileFollowServiceUnitTests {
    @Mock
    private ProfileFollowRepository profileFollowRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileFollowMapper profileFollowMapper;

    @InjectMocks
    private ProfileFollowService profileFollowService;

    @Test
    void getProfileFollowsByFollowerId() {
        UUID followerId = UUID.randomUUID();

        int page = 0;
        int size = 50;

        PageRequest pageable = PageRequest.of(page, size);

        ProfileFollow profileFollow1 = ProfileFollow.builder().build();

        ProfileFollow profileFollow2 = ProfileFollow.builder().build();

        Page<ProfileFollow> profileFollowsPage = new PageImpl<>(
                List.of(profileFollow1, profileFollow2),
                pageable, 2
        );

        when(profileRepository.findById(followerId))
                .thenReturn(Optional.of(Profile.builder().build()));

        when(profileFollowRepository
                .getProfileFollowsByFollower_Id(followerId, pageable))
                .thenReturn(profileFollowsPage);

        when(profileFollowMapper
                .profileFollowToProfileFollowResponseDTO(any(ProfileFollow.class)))
                .thenReturn(ProfileFollowResponseDTO.builder().build());

        Page<ProfileFollowResponseDTO> foundProfileFollows =
                profileFollowService.getProfileFollowsByFollowerId(followerId, page, size);

        verify(profileRepository).findById(followerId);
        verify(profileFollowRepository)
                .getProfileFollowsByFollower_Id(followerId, pageable);

        assertEquals(foundProfileFollows.getTotalElements(), 2);
    }

    @Test
    void getProfileFollowsByFollowerId_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileFollowService
                        .getProfileFollowsByFollowerId(UUID.randomUUID(), 0, 50));
    }

    @Test
    void getProfileFollowsByFollowingId() {
        UUID followingId = UUID.randomUUID();

        int page = 0;
        int size = 50;

        PageRequest pageable = PageRequest.of(page, size);

        ProfileFollow profileFollow1 = ProfileFollow.builder().build();

        ProfileFollow profileFollow2 = ProfileFollow.builder().build();

        Page<ProfileFollow> profileFollowsPage = new PageImpl<>(
                List.of(profileFollow1, profileFollow2),
                pageable, 2
        );

        when(profileRepository.findById(followingId))
                .thenReturn(Optional.of(Profile.builder().build()));

        when(profileFollowRepository
                .getProfileFollowsByFollowing_Id(followingId, pageable))
                .thenReturn(profileFollowsPage);

        when(profileFollowMapper
                .profileFollowToProfileFollowResponseDTO(any(ProfileFollow.class)))
                .thenReturn(ProfileFollowResponseDTO.builder().build());

        Page<ProfileFollowResponseDTO> foundProfileFollows =
                profileFollowService.getProfileFollowsByFollowingId(followingId, page, size);

        verify(profileRepository).findById(followingId);
        verify(profileFollowRepository)
                .getProfileFollowsByFollowing_Id(followingId, pageable);

        assertEquals(foundProfileFollows.getTotalElements(), 2);
    }

    @Test
    void getProfileFollowsByFollowingId_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileFollowService
                        .getProfileFollowsByFollowingId(UUID.randomUUID(), 0, 50));
    }

    @Test
    void createProfileFollow() {
        UUID followerId = UUID.randomUUID();
        UUID followingId = UUID.randomUUID();

        Profile follower = Profile.builder().id(followerId).build();
        Profile following = Profile.builder().id(followingId).build();

        ProfileFollowDTO profileFollowDTO = ProfileFollowDTO
                .builder()
                .followerId(followerId)
                .followingId(followingId)
                .build();

        ProfileFollow profileFollow  = ProfileFollow.builder()
                .follower(follower)
                .following(following)
                .build();

        ProfileFollowResponseDTO profileFollowResponseDTO =
                ProfileFollowResponseDTO.builder()
                        .followerId(follower.getId())
                        .followingId(following.getId())
                        .build();

        when(profileRepository.findById(followerId))
                .thenReturn(Optional.of(follower));

        when(profileRepository.findById(followingId))
                .thenReturn(Optional.of(following));

        when(profileFollowRepository.saveAndFlush(any(ProfileFollow.class)))
                .thenReturn(profileFollow);

        when(profileFollowMapper
                .profileFollowToProfileFollowResponseDTO(profileFollow))
                .thenReturn(profileFollowResponseDTO);

        ProfileFollowResponseDTO createdProfileFollow = profileFollowService
                .createProfileFollow(profileFollowDTO);

        verify(profileFollowRepository).saveAndFlush(any(ProfileFollow.class));

        assertEquals(createdProfileFollow.getFollowerId(),
                profileFollowDTO.getFollowerId());
        assertEquals(createdProfileFollow.getFollowingId(),
                profileFollowDTO.getFollowingId());
    }

    @Test
    void createProfileFollow_ShouldThrowAlreadyExistException() {
        UUID followerId = UUID.randomUUID();
        UUID followingId = UUID.randomUUID();

        ProfileFollowDTO profileFollowDTO = ProfileFollowDTO.builder()
                .followerId(followerId)
                .followingId(followingId)
                .build();

        when(profileRepository.findById(followerId))
                .thenReturn(Optional.of(Profile.builder().id(followerId).build()));

        when(profileRepository.findById(followingId))
                .thenReturn(Optional.of(Profile.builder().id(followingId).build()));

        when(profileFollowRepository
                .getProfileFollowByFollowerIdAndFollowingId(followerId, followingId))
                .thenReturn(Optional.of(ProfileFollow.builder().build()));

        assertThrows(AlreadyExistException.class, () ->
                profileFollowService.createProfileFollow(profileFollowDTO));
    }

    @Test
    void deleteProfileFollow() {
        UUID followerId = UUID.randomUUID();
        UUID followingId = UUID.randomUUID();
        UUID id = UUID.randomUUID();

        when(profileFollowRepository
                .getProfileFollowByFollowerIdAndFollowingId(followerId, followingId))
                .thenReturn(Optional.of(ProfileFollow.builder().id(id).build()));

        profileFollowService.deleteProfileFollow(followerId, followingId);

        verify(profileFollowRepository).deleteById(id);
    }

    @Test
    void deleteProfileFollow_ShouldThrow_Profile_NotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileFollowService
                        .deleteProfileFollow(UUID.randomUUID(), UUID.randomUUID()));
    }

    @Test
    void deleteProfileFollow_ShouldThrow_ProfileFollow_NotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileFollowService
                        .deleteProfileFollow(UUID.randomUUID(), UUID.randomUUID()));
    }
}