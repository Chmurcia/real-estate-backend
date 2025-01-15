package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileNotification.ProfileNotificationDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileNotification.ProfileNotificationResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileNotification;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileNotificationMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileNotificationRepository;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileNotificationServiceTestUnitTests {

    @Mock
    private ProfileNotificationRepository profileNotificationRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileNotificationMapper profileNotificationMapper;

    @InjectMocks
    private ProfileNotificationService profileNotificationService;

    @Test
    void getProfileNotificationById() {
        ProfileNotification profileNotification = ProfileNotification.builder()
                .id(UUID.randomUUID())
                .profile(Profile.builder().id(UUID.randomUUID()).build())
                .notificationName("Name")
                .notificationId(UUID.randomUUID())
                .notificationDescription("Description")
                .build();

        ProfileNotificationResponseDTO profileNotificationResponseDTO =
                ProfileNotificationResponseDTO.builder()
                        .id(UUID.randomUUID())
                        .profileId(profileNotification.getProfile()
                                .getId())
                        .notificationName(profileNotification
                                .getNotificationName())
                        .notificationId(profileNotification
                                .getNotificationId())
                        .notificationDescription(profileNotification
                                .getNotificationDescription())
                        .build();

        when(profileNotificationRepository.findById(profileNotification.getId()))
                .thenReturn(Optional.of(profileNotification));

        when(profileNotificationMapper
                .profileNotificationToProfileNotificationResponseDTO(
                        any(ProfileNotification.class
                        ))).thenReturn(profileNotificationResponseDTO);

        ProfileNotificationResponseDTO foundProfileNotification = profileNotificationService
                .getProfileNotificationById(profileNotification.getId());

        assertThat(foundProfileNotification).isNotNull();
        assertEquals(foundProfileNotification.getProfileId(),
                profileNotification.getProfile().getId());
        assertEquals(foundProfileNotification.getNotificationName(),
                profileNotification.getNotificationName());
        assertEquals(foundProfileNotification.getNotificationDescription(),
                profileNotification.getNotificationDescription());
        assertEquals(foundProfileNotification.getNotificationId(),
                profileNotification.getNotificationId());
    }

    @Test
    void getProfileNotificationById_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileNotificationService
                        .getProfileNotificationById(UUID.randomUUID()));
    }

    @Test
    void getProfileNotificationsByProfileId() {
        Profile profile = Profile.builder()
                .id(UUID.randomUUID())
                .build();

        List<ProfileNotification> notifications = Arrays.asList(
                ProfileNotification.builder().build(),
                ProfileNotification.builder().build()
        );

        when(profileRepository.findById(profile.getId()))
                .thenReturn(Optional.of(profile));

        when(profileNotificationRepository
                .getProfileNotificationsByProfileId(profile.getId()))
                .thenReturn(notifications);

        when(profileNotificationMapper
                .profileNotificationToProfileNotificationResponseDTO
                        (
                                any(ProfileNotification.class)
                        ))
                .thenReturn(ProfileNotificationResponseDTO.builder().build());

        List<ProfileNotificationResponseDTO> profileNotificationResponseDTOS =
                profileNotificationService
                        .getProfileNotificationsByProfileId(profile.getId());

        assertThat(profileNotificationResponseDTOS).isNotNull();
        assertEquals(profileNotificationResponseDTOS.size(), notifications.size());
    }

    @Test
    void getProfileNotificationsByProfileId_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileNotificationService
                        .getProfileNotificationsByProfileId(UUID.randomUUID()));
    }

    @Test
    void createProfileNotification() {
        Profile profile = Profile.builder()
                .id(UUID.randomUUID())
                .build();

        ProfileNotificationDTO profileNotificationDTO = ProfileNotificationDTO
                .builder()
                .notificationName("Name")
                .notificationDescription("Description")
                .notificationId(UUID.randomUUID())
                .build();

        ProfileNotification savedProfileNotification =
                ProfileNotification.builder()
                        .profile(profile)
                        .notificationName(profileNotificationDTO
                                .getNotificationName())
                        .notificationDescription(profileNotificationDTO
                                .getNotificationDescription())
                        .notificationId(profileNotificationDTO
                                .getNotificationId())
                        .build();

        ProfileNotificationResponseDTO profileNotificationResponseDTO =
                ProfileNotificationResponseDTO.builder()
                        .profileId(profile.getId())
                        .notificationName(savedProfileNotification
                                .getNotificationName())
                        .notificationDescription(savedProfileNotification
                                .getNotificationDescription())
                        .notificationId(savedProfileNotification
                                .getNotificationId())
                        .build();

        when(profileRepository.findById(profile.getId()))
                .thenReturn(Optional.of(profile));

        when(profileNotificationRepository.saveAndFlush(any(ProfileNotification.class)))
                .thenReturn(savedProfileNotification);

        when(profileNotificationMapper
                .profileNotificationToProfileNotificationResponseDTO(
                        any(ProfileNotification.class)
                ))
                .thenReturn(profileNotificationResponseDTO);

        ProfileNotificationResponseDTO createdProfileNotification = profileNotificationService
                .createProfileNotification(profile.getId(), profileNotificationDTO);

        verify(profileNotificationRepository).saveAndFlush(any(ProfileNotification.class));

        assertThat(createdProfileNotification).isNotNull();
        assertEquals(createdProfileNotification.getNotificationName(),
                profileNotificationDTO.getNotificationName());
        assertEquals(createdProfileNotification.getNotificationDescription(),
                profileNotificationDTO.getNotificationDescription());
        assertEquals(createdProfileNotification.getNotificationId(),
                profileNotificationDTO.getNotificationId());
    }

    @Test
    void createProfileNotification_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileNotificationService
                        .createProfileNotification(UUID.randomUUID(),
                                ProfileNotificationDTO.builder().build()));
    }

    @Test
    void createProfileNotification_ShouldThrowNullException() {
        assertThrows(NotFoundException.class, () ->
                profileNotificationService
                        .createProfileNotification(UUID.randomUUID(),
                                ProfileNotificationDTO.builder().build()));
    }

    @Test
    void deleteProfileNotification() {
        when(profileNotificationRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(ProfileNotification.builder().build()));

        profileNotificationService.deleteProfileNotification(UUID.randomUUID());

        verify(profileNotificationRepository).deleteById(any(UUID.class));
    }

    @Test
    void deleteProfileNotification_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileNotificationService
                        .deleteProfileNotification(UUID.randomUUID()));
    }
}