package cloud.uwu.realestatebackend.services.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileSettings.ProfileSettingsDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileSettings.ProfileSettingsPatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileSettings.ProfileSettingsResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileSettings;
import cloud.uwu.realestatebackend.exceptions.NotFoundException;
import cloud.uwu.realestatebackend.exceptions.NullException;
import cloud.uwu.realestatebackend.mappers.profile.ProfileSettingsMapper;
import cloud.uwu.realestatebackend.repositories.profile.ProfileRepository;
import cloud.uwu.realestatebackend.repositories.profile.ProfileSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileSettingsService {
    private final ProfileSettingsRepository profileSettingsRepository;
    private final ProfileSettingsMapper profileSettingsMapper;

    public ProfileSettingsResponseDTO getProfileSettingsById(UUID id) {
        ProfileSettings profileSettings = getProfileSettings(id);

        return profileSettingsMapper.profileSettingsToProfileSettingsResponseDTO(profileSettings);
    }

    public void updateProfileSettings(UUID id, ProfileSettingsDTO profileSettingsDTO) {
        ProfileSettings profileSettings = getProfileSettings(id);

        if (profileSettingsDTO.getProfileVisibility() != null) {
            profileSettings.setProfileVisibility(profileSettingsDTO.getProfileVisibility());
        } else {
            throw new NullException("profileVisibility is null");
        }

        if (profileSettingsDTO.getTheme() != null) {
            profileSettings.setTheme(profileSettingsDTO.getTheme());
        } else {
            throw new NullException("Theme is null");
        }

        profileSettingsRepository.save(profileSettings);
    }

    public void patchProfileSettings(UUID id, ProfileSettingsPatchDTO profileSettingsPatchDTO) {
        ProfileSettings profileSettings = getProfileSettings(id);

        if (profileSettingsPatchDTO.getProfileVisibility() != null) {
            profileSettings.setProfileVisibility(profileSettingsPatchDTO.getProfileVisibility());
        }

        if (profileSettingsPatchDTO.getTheme() != null) {
            profileSettings.setTheme(profileSettingsPatchDTO.getTheme());
        }

        profileSettingsRepository.save(profileSettings);
    }

    public void deleteProfileSettings(UUID id) {
        getProfileSettings(id);

        profileSettingsRepository.deleteById(id);
    }

    private ProfileSettings getProfileSettings(UUID id) {
        return profileSettingsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ProfileSettings not found"));
    }
}
