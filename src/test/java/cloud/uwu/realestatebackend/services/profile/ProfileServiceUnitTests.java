package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.profile.profile.ProfileDTO;
import cloud.uwu.realestatebackend.dtos.profile.profile.ProfilePatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profile.ProfileResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.Profile;
import cloud.uwu.realestatebackend.entities.profile.ProfileSettings;
import cloud.uwu.realestatebackend.entities.profile.ProfileStatistics;
import cloud.uwu.realestatebackend.entities.profile.profileEnums.Theme;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRepository;
import cloud.uwu.realestatebackend.repositories.profile.ProfileSettingsRepository;
import cloud.uwu.realestatebackend.repositories.profile.ProfileStatisticsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileServiceUnitTests {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileSettingsRepository profileSettingsRepository;

    @Mock
    private ProfileStatisticsRepository profileStatisticsRepository;

    @Mock
    private ProfileMapper profileMapper;

    @InjectMocks
    private ProfileService profileService;

    private LocalDateTime birthDate = LocalDateTime.of(2001, 8,
            9, 15, 3);

    @Test
    void getProfileById() {
        Profile profile = Profile.builder().id(UUID.randomUUID()).build();
        ProfileResponseDTO profileResponseDTO = ProfileResponseDTO.builder()
                .id(profile.getId())
                .build();
        when(profileRepository.findById(profile.getId()))
                .thenReturn(Optional.of(profile));

        when(profileMapper.profileToProfileResponseDTO(profile))
                .thenReturn(profileResponseDTO);

        ProfileResponseDTO foundProfile = profileService
                .getProfileById(profile.getId());

        assertThat(foundProfile).isNotNull();
        assertEquals(profile.getId(), foundProfile.getId());
    }

    @Test
    void getProfileByIdNotFound() {
        assertThrows(NotFoundException.class, () ->
                profileService.getProfileById(UUID.randomUUID()));
    }

    @Test
    void getProfilesByCountry() {
        int page = 0;
        int size = 50;

        PageRequest pageable = PageRequest.of(page, size);

        Profile profile1 = Profile.builder()
                .id(UUID.randomUUID())
                .build();
        Profile profile2 = Profile.builder()
                .id(UUID.randomUUID())
                .build();

        Page<Profile> profiles = new PageImpl<>(
                List.of(profile1, profile2),
                pageable, 2
        );

        when(profileRepository.getProfilesByCountry("Country", pageable))
                .thenReturn(profiles);

        when(profileMapper.profileToProfileResponseDTO(any(Profile.class)))
                .thenReturn(ProfileResponseDTO.builder()
                        .id(UUID.randomUUID())
                        .build());

        Page<ProfileResponseDTO> foundProfiles = profileService
                .getProfilesByCountry("Country", page, size);

        assertThat(foundProfiles).isNotNull();
        assertEquals(foundProfiles.getContent().size(), 2);
    }

    @Test
    void getProfilesByCountry_ShouldReturn0Elements() {
        int page = 0;
        int size = 50;

        PageRequest pageable = PageRequest.of(page, size);

        Page<Profile> profiles = new PageImpl<>(
                List.of(),
                pageable, 2
        );

        when(profileRepository.getProfilesByCountry("Country", pageable))
                .thenReturn(profiles);

        Page<ProfileResponseDTO> foundProfiles = profileService
                .getProfilesByCountry("Country", page, size);

        assertThat(foundProfiles).isNotNull();
        assertEquals(foundProfiles.getContent().size(), 0);
    }

    @Test
    void getProfilesByState() {
        int page = 0;
        int size = 50;

        PageRequest pageable = PageRequest.of(page, size);

        Profile profile1 = Profile.builder()
                .id(UUID.randomUUID())
                .build();
        Profile profile2 = Profile.builder()
                .id(UUID.randomUUID())
                .build();

        Page<Profile> profiles = new PageImpl<>(
                List.of(profile1, profile2),
                pageable, 2
        );

        when(profileRepository.getProfilesByState("State", pageable))
                .thenReturn(profiles);

        when(profileMapper.profileToProfileResponseDTO(any(Profile.class)))
                .thenReturn(ProfileResponseDTO.builder()
                        .id(UUID.randomUUID())
                        .build());

        Page<ProfileResponseDTO> foundProfiles = profileService
                .getProfilesByState("State", page, size);

        assertThat(foundProfiles).isNotNull();
        assertEquals(foundProfiles.getContent().size(), 2);
    }

    @Test
    void getProfilesByState_ShouldReturn0Elements() {
        int page = 0;
        int size = 50;

        PageRequest pageable = PageRequest.of(page, size);

        Page<Profile> profiles = new PageImpl<>(
                List.of(),
                pageable, 2
        );

        when(profileRepository.getProfilesByState("State", pageable))
                .thenReturn(profiles);

        Page<ProfileResponseDTO> foundProfiles = profileService
                .getProfilesByState("State", page, size);

        assertThat(foundProfiles).isNotNull();
        assertEquals(foundProfiles.getContent().size(), 0);
    }

    @Test
    void getProfilesByCity() {
        int page = 0;
        int size = 50;

        PageRequest pageable = PageRequest.of(page, size);

        Profile profile1 = Profile.builder()
                .id(UUID.randomUUID())
                .build();
        Profile profile2 = Profile.builder()
                .id(UUID.randomUUID())
                .build();

        Page<Profile> profiles = new PageImpl<>(
                List.of(profile1, profile2),
                pageable, 2
        );

        when(profileRepository.getProfilesByCity("City", pageable))
                .thenReturn(profiles);

        when(profileMapper.profileToProfileResponseDTO(any(Profile.class)))
                .thenReturn(ProfileResponseDTO.builder()
                        .id(UUID.randomUUID())
                        .build());

        Page<ProfileResponseDTO> foundProfiles = profileService
                .getProfilesByCity("City", page, size);

        assertThat(foundProfiles).isNotNull();
        assertEquals(foundProfiles.getContent().size(), 2);
    }

    @Test
    void getProfilesByCity_ShouldReturn0Elements() {
        int page = 0;
        int size = 50;

        PageRequest pageable = PageRequest.of(page, size);

        Page<Profile> profiles = new PageImpl<>(
                List.of(),
                pageable, 2
        );

        when(profileRepository.getProfilesByCity("City", pageable))
                .thenReturn(profiles);

        Page<ProfileResponseDTO> foundProfiles = profileService
                .getProfilesByCity("City", page, size);

        assertThat(foundProfiles).isNotNull();
        assertEquals(foundProfiles.getContent().size(), 0);
    }

    @Test
    void createProfile() {

        ProfileDTO profileDTO = ProfileDTO.builder()
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("111000999")
                .avatarURL("someURL")
                .bio("My Bio")
                .country("Poland")
                .state("Pomorskie")
                .city("Gdynia")
                .birthDate(birthDate)
                .build();

        Profile profile = Profile.builder()
                .id(UUID.randomUUID())
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("111000999")
                .avatarURL("someURL")
                .bio("My Bio")
                .country("Poland")
                .state("Pomorskie")
                .city("Gdynia")
                .birthDate(birthDate)
                .build();

        ProfileResponseDTO profileResponseDTO = ProfileResponseDTO.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .phoneNumber(profile.getPhoneNumber())
                .avatarURL(profile.getAvatarURL())
                .bio(profile.getBio())
                .country(profile.getCountry())
                .state(profile.getState())
                .city(profile.getCity())
                .birthDate(profile.getBirthDate())
                .build();

        ProfileSettings profileSettings = ProfileSettings.builder()
                .profileVisibility(true)
                .theme(Theme.LIGHT)
                .build();

        ProfileStatistics profileStatistics = ProfileStatistics.builder()
                .totalRatings(0)
                .totalOffers(0)
                .totalViews(0)
                .totalTrusts(0)
                .totalPosts(0)
                .totalActionPoints(0)
                .build();

        when(profileStatisticsRepository.saveAndFlush(any(ProfileStatistics.class)))
                .thenReturn(profileStatistics);

        when(profileSettingsRepository.saveAndFlush(any(ProfileSettings.class)))
                .thenReturn(profileSettings);

        when(profileRepository.save(any(Profile.class)))
                .thenReturn(profile);

        when(profileMapper.profileToProfileResponseDTO(any(Profile.class)))
                .thenReturn(profileResponseDTO);

        ProfileResponseDTO savedProfile = profileService.createProfile(profileDTO);

        verify(profileRepository).save(any(Profile.class));

        assertThat(savedProfile).isNotNull();
        assertEquals(profileDTO.getFirstName(), savedProfile.getFirstName());
        assertEquals(profileDTO.getLastName(), savedProfile.getLastName());
        assertEquals(profileDTO.getPhoneNumber(), savedProfile.getPhoneNumber());
        assertEquals(profileDTO.getAvatarURL(), savedProfile.getAvatarURL());
        assertEquals(profileDTO.getBio(), savedProfile.getBio());
        assertEquals(profileDTO.getCountry(), savedProfile.getCountry());
        assertEquals(profileDTO.getState(), savedProfile.getState());
        assertEquals(profileDTO.getCity(), savedProfile.getCity());
        assertEquals(profileDTO.getBirthDate(), savedProfile.getBirthDate());
    }

    @Test
    void updateProfile() {
        Profile profile = Profile.builder()
                .id(UUID.randomUUID())
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("111000999")
                .avatarURL("someURL")
                .bio("My Bio")
                .country("Poland")
                .state("Pomorskie")
                .city("Gdynia")
                .birthDate(birthDate)
                .build();

        ProfileDTO profileDTO = ProfileDTO.builder()
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("111000999")
                .avatarURL("someURL")
                .bio("My Bio")
                .country("Poland")
                .state("Pomorskie")
                .city("Gdynia")
                .birthDate(birthDate)
                .build();

        when(profileRepository.findById(profile.getId()))
                .thenReturn(Optional.of(profile));

        profileService.updateProfile(profile.getId(), profileDTO);

        verify(profileRepository).save(profile);
    }

    @Test
    void updateProfile_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileService.updateProfile(UUID.randomUUID(),
                        ProfileDTO.builder().build()));
    }

    @Test
    void patchProfile() {
        Profile profile = Profile.builder()
                .id(UUID.randomUUID())
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("111000999")
                .avatarURL("someURL")
                .bio("My Bio")
                .country("Poland")
                .state("Pomorskie")
                .city("Gdynia")
                .birthDate(birthDate)
                .build();

        ProfilePatchDTO profilePatchDTO = ProfilePatchDTO.builder()
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("111000999")
                .avatarURL("someURL")
                .bio("My Bio")
                .country("Poland")
                .state("Pomorskie")
                .city("Gdynia")
                .birthDate(birthDate)
                .build();

        when(profileRepository.findById(profile.getId()))
                .thenReturn(Optional.of(profile));

        profileService.patchProfile(profile.getId(), profilePatchDTO);

        verify(profileRepository).save(profile);
    }

    @Test
    void patchProfile_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileService.patchProfile(UUID.randomUUID(),
                        ProfilePatchDTO.builder().build()));
    }

    @Test
    void deleteProfile() {
        UUID id = UUID.randomUUID();

        when(profileRepository.findById(id))
                .thenReturn(Optional.of(Profile.builder().build()));

        profileService.deleteProfile(id);

        verify(profileRepository).deleteById(id);
    }

    @Test
    void deleteProfile_ShouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () ->
                profileService.deleteProfile(UUID.randomUUID()));
    }
}