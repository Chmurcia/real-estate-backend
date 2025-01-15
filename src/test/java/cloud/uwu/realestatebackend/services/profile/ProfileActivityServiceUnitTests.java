package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityPatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileActivity;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileActivityMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileActivityRepository;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileActivityServiceUnitTests {

    @Mock
    private ProfileActivityRepository profileActivityRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileActivityMapper profileActivityMapper;

    @InjectMocks
    private ProfileActivityService profileActivityService;

    @Test
    void getProfileActivitiesByProfileId() {
        UUID id = UUID.randomUUID();

        int page = 0;
        int size = 50;

        PageRequest pageable = PageRequest.of(page, size);

        ProfileActivity profileActivity1 = ProfileActivity.builder()
                .id(UUID.randomUUID()).build();
        ProfileActivity profileActivity2 = ProfileActivity.builder()
                .id(UUID.randomUUID()).build();

        Page<ProfileActivity> profileActivities = new PageImpl<>(
                List.of(profileActivity1, profileActivity2),
                pageable, 2
        );

        when(profileRepository.findById(id))
                .thenReturn(Optional.of(Profile.builder().build()));

        when(profileActivityRepository.getProfileActivitiesByProfileId(id, pageable))
                .thenReturn(profileActivities);

        when(profileActivityMapper
                .profileActivityToProfileActivityResponseDTO(any(ProfileActivity.class)))
                .thenReturn(ProfileActivityResponseDTO.builder()
                        .id(UUID.randomUUID()).build());

        Page<ProfileActivityResponseDTO> profileActivityResponseDTOS = profileActivityService
                .getProfileActivitiesByProfileId(id, page, size);

        verify(profileRepository).findById(id);

        assertThat(profileActivityResponseDTOS).isNotNull();
        assertEquals(profileActivityResponseDTOS.getContent().size(), 2);
    }

    @Test
    void getProfileActivitiesByProfileId_ShouldReturn1Element() {
        UUID id = UUID.randomUUID();

        int page = 0;
        int size = 1;

        PageRequest pageable = PageRequest.of(page, size);

        ProfileActivity profileActivity1 = ProfileActivity.builder()
                .id(UUID.randomUUID()).build();

        Page<ProfileActivity> profileActivities = new PageImpl<>(
                List.of(profileActivity1),
                pageable, 2
        );

        when(profileRepository.findById(id))
                .thenReturn(Optional.of(Profile.builder().build()));

        when(profileActivityRepository.getProfileActivitiesByProfileId(id, pageable))
                .thenReturn(profileActivities);

        when(profileActivityMapper
                .profileActivityToProfileActivityResponseDTO(any(ProfileActivity.class)))
                .thenReturn(ProfileActivityResponseDTO.builder()
                        .id(UUID.randomUUID()).build());

        Page<ProfileActivityResponseDTO> profileActivityResponseDTOS = profileActivityService
                .getProfileActivitiesByProfileId(id, page, size);

        verify(profileRepository).findById(id);

        assertThat(profileActivityResponseDTOS).isNotNull();
        assertEquals(profileActivityResponseDTOS.getContent().size(), 1);
    }

    @Test
    void getProfileActivitiesByProfileIdProfile_ShouldThrowNotFoundException() {
        when(profileRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> profileActivityService
                .getProfileActivitiesByProfileId(UUID.randomUUID(), 0, 50));
    }

    @Test
    void getProfileActivityById() {
        UUID id = UUID.randomUUID();

        ProfileActivity profileActivity = ProfileActivity.builder().id(id).build();
        ProfileActivityResponseDTO profileActivityResponseDTO = ProfileActivityResponseDTO
                .builder().id(id).build();

        when(profileActivityRepository.findById(id))
                .thenReturn(Optional.of(profileActivity));

        when(profileActivityMapper
                .profileActivityToProfileActivityResponseDTO(profileActivity))
                .thenReturn(profileActivityResponseDTO);

        ProfileActivityResponseDTO foundProfileActivity = profileActivityService
                .getProfileActivityById(id);

        verify(profileActivityRepository).findById(id);

        assertThat(foundProfileActivity).isNotNull();
        assertEquals(profileActivity.getId(), foundProfileActivity.getId());
    }

    @Test
    void getProfileActivityById_ShouldThrowNotFoundException() {
        when(profileActivityRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> profileActivityService
                .getProfileActivityById(UUID.randomUUID()));
    }

    @Test
    void createProfileActivity() {
        UUID profileId = UUID.randomUUID();
        UUID profileActivityId = UUID.randomUUID();

        Profile profile = Profile.builder().id(profileId).build();

        ProfileActivityDTO profileActivityDTO = ProfileActivityDTO.builder()
                .activityTitle("Title uwu")
                .activityDescription("Description uwu")
                .activityDate(ZonedDateTime.of(2024, 7, 14, 14,
                        12, 10, 9, ZoneId.of("Europe/Warsaw")))
                .build();

        ProfileActivity profileActivity = ProfileActivity.builder()
                .id(profileActivityId)
                .profile(profile)
                .activityTitle(profileActivityDTO.getActivityTitle())
                .activityDescription(profileActivityDTO.getActivityDescription())
                .activityDate(profileActivityDTO.getActivityDate())
                .build();

        ProfileActivityResponseDTO profileActivityResponseDTO = ProfileActivityResponseDTO
                .builder()
                .id(profileActivityId)
                .activityTitle(profileActivityDTO.getActivityTitle())
                .activityDescription(profileActivityDTO.getActivityDescription())
                .activityDate(profileActivityDTO.getActivityDate())
                .build();

        when(profileRepository.findById(profileId))
                .thenReturn(Optional.of(profile));

        when(profileActivityRepository.saveAndFlush(any(ProfileActivity.class)))
                .thenReturn(profileActivity);

        when(profileActivityMapper
                .profileActivityToProfileActivityResponseDTO(any(ProfileActivity.class)))
                .thenReturn(profileActivityResponseDTO);

        ProfileActivityResponseDTO savedProfileActivity = profileActivityService
                .createProfileActivity(profileId, profileActivityDTO);

        assertThat(savedProfileActivity).isNotNull();
        assertEquals(profileActivityDTO.getActivityTitle(),
                savedProfileActivity.getActivityTitle());
        assertEquals(profileActivityDTO.getActivityDescription(),
                savedProfileActivity.getActivityDescription());
        assertEquals(profileActivityDTO.getActivityDate(),
                savedProfileActivity.getActivityDate());
    }

    @Test
    void createProfileActivityProfileId_ShouldThrowNullException() {
        UUID id = UUID.randomUUID();

        assertThrows(NotFoundException.class, () -> profileActivityService
                .createProfileActivity(id, ProfileActivityDTO.builder().build()));
    }

    @Test
    void updateProfileActivity() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2024, 7,
                14, 14, 12, 10,
                9, ZoneId.of("Europe/Warsaw"));

        ProfileActivity profileActivity = ProfileActivity.builder()
                .id(UUID.randomUUID())
                .activityTitle("Title")
                .activityDescription("Description")
                .activityDate(zonedDateTime)
                .build();

        ProfileActivityDTO profileActivityDTO = ProfileActivityDTO.builder()
                .activityTitle("Title uwu")
                .activityDescription("Description uwu")
                .activityDate(zonedDateTime)
                .build();

        when(profileActivityRepository.findById(profileActivity.getId()))
                .thenReturn(Optional.of(profileActivity));

        profileActivityService.updateProfileActivity(profileActivity.getId(),
                profileActivityDTO);

        verify(profileActivityRepository).save(profileActivity);
    }

    @Test
    void updateProfileActivity_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileActivityService.updateProfileActivity(UUID.randomUUID(),
                        ProfileActivityDTO.builder().build()));
    }

    @Test
    void patchProfileActivity() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2024, 7,
                14, 14, 12, 10,
                9, ZoneId.of("Europe/Warsaw"));

        ProfileActivity profileActivity = ProfileActivity.builder()
                .id(UUID.randomUUID())
                .activityTitle("Title")
                .activityDescription("Description")
                .activityDate(zonedDateTime)
                .build();

        ProfileActivityPatchDTO profileActivityPatchDTO = ProfileActivityPatchDTO.builder()
                .activityTitle("Title uwu")
                .activityDescription("Description uwu")
                .activityDate(zonedDateTime)
                .build();

        when(profileActivityRepository.findById(profileActivity.getId()))
                .thenReturn(Optional.of(profileActivity));

        profileActivityService.patchProfileActivity(profileActivity.getId(),
                profileActivityPatchDTO);

        verify(profileActivityRepository).save(profileActivity);
    }

    @Test
    void patchProfileActivity_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileActivityService.patchProfileActivity(UUID.randomUUID(),
                        ProfileActivityPatchDTO.builder().build()));
    }

    @Test
    void deleteProfileActivity() {
        UUID id = UUID.randomUUID();

        when(profileActivityRepository.findById(id))
                .thenReturn(Optional.of(ProfileActivity.builder().build()));

        profileActivityService.deleteProfileActivity(id);

        verify(profileActivityRepository).deleteById(id);
    }

    @Test
    void deleteProfileActivity_ShouldThrowNotFoundException() {

        assertThrows(NotFoundException.class, () ->
                profileActivityService.deleteProfileActivity(UUID.randomUUID()));

    }
}