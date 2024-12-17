package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileSettings.ProfileSettingsDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileSettings.ProfileSettingsPatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileSettings.ProfileSettingsResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileSettings;
import cloud.uwu.realestatebackend.entities.profile.profileEnums.Theme;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileSettingsMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileSettingsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileSettingsServiceUnitTests {

    @Mock
    private ProfileSettingsRepository profileSettingsRepository;

    @Mock
    private ProfileSettingsMapper profileSettingsMapper;

    @InjectMocks
    private ProfileSettingsService profileSettingsService;

    @Test
    void getProfileSettingsById() {
        ProfileSettings profileSettings = ProfileSettings.builder()
                .id(UUID.randomUUID())
                .build();
        ProfileSettingsResponseDTO profileSettingsResponseDTO = ProfileSettingsResponseDTO
                .builder().id(profileSettings.getId()).build();

        when(profileSettingsRepository.findById(profileSettings.getId()))
                .thenReturn(Optional.of(profileSettings));

        when(profileSettingsMapper
                .profileSettingsToProfileSettingsResponseDTO(profileSettings))
                .thenReturn(profileSettingsResponseDTO);

        ProfileSettingsResponseDTO foundProfileSettings = profileSettingsService
                .getProfileSettingsById(profileSettings.getId());

        assertThat(foundProfileSettings).isNotNull();
        assertEquals(profileSettings.getId(), foundProfileSettings.getId());
    }

    @Test
    void getProfileSettingsByIdNotFound() {
        assertThrows(NotFoundException.class, () ->
                profileSettingsService.getProfileSettingsById(UUID.randomUUID()));
    }

    @Test
    void updateProfileSettings() {
        ProfileSettings profileSettings = ProfileSettings.builder()
                .id(UUID.randomUUID())
                .profileVisibility(true)
                .theme(Theme.LIGHT)
                .build();

        ProfileSettingsDTO profileSettingsDTO = ProfileSettingsDTO.builder()
                .profileVisibility(true)
                .theme(Theme.LIGHT)
                .build();

        when(profileSettingsRepository.findById(profileSettings.getId()))
                .thenReturn(Optional.of(profileSettings));

        profileSettingsService.updateProfileSettings(profileSettings.getId(),
                profileSettingsDTO);

        verify(profileSettingsRepository).save(profileSettings);
    }

    @Test
    void updateProfileSettingsNotFound() {
        assertThrows(NotFoundException.class, () ->
                profileSettingsService.updateProfileSettings(UUID.randomUUID(),
                        ProfileSettingsDTO.builder().build()));
    }

    @Test
    void patchProfileSettings() {
        ProfileSettings profileSettings = ProfileSettings.builder()
                .id(UUID.randomUUID())
                .profileVisibility(true)
                .theme(Theme.LIGHT)
                .build();

        ProfileSettingsPatchDTO profileSettingsPatchDTO = ProfileSettingsPatchDTO.builder()
                .profileVisibility(true)
                .theme(Theme.LIGHT)
                .build();

        when(profileSettingsRepository.findById(profileSettings.getId()))
                .thenReturn(Optional.of(profileSettings));

        profileSettingsService.patchProfileSettings(profileSettings.getId(),
                profileSettingsPatchDTO);

        verify(profileSettingsRepository).save(profileSettings);
    }

    @Test
    void patchProfileSettingsNotFound() {
        assertThrows(NotFoundException.class, () ->
                profileSettingsService.patchProfileSettings(UUID.randomUUID(),
                        ProfileSettingsPatchDTO.builder().build()));
    }

    @Test
    void deleteProfileSettings() {
        UUID profileSettingsId = UUID.randomUUID();

        when(profileSettingsRepository.findById(profileSettingsId))
                .thenReturn(Optional.of(ProfileSettings.builder().build()));

        profileSettingsService.deleteProfileSettings(profileSettingsId);

        verify(profileSettingsRepository).deleteById(profileSettingsId);
    }


    @Test
    void deleteProfileSettingsNotFound() {
        assertThrows(NotFoundException.class, () ->
                profileSettingsService.deleteProfileSettings(UUID.randomUUID()));
    }
}