package cloud.uwu.realestatebackend.mappers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileSettings.ProfileSettingsDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileSettings.ProfileSettingsResponseDTO;
import cloud.uwu.realestatebackend.entities.profile.ProfileSettings;
import cloud.uwu.realestatebackend.entities.profile.profileEnums.Theme;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProfileSettingsMapperTest {

    ProfileSettingsMapper profileSettingsMapper = Mappers
            .getMapper(ProfileSettingsMapper.class);

    @Test
    void profileSettingsToProfileSettingsDTO() {
        ProfileSettings profileSettings = ProfileSettings.builder()
                .id(UUID.randomUUID())
                .profileVisibility(true)
                .theme(Theme.DARK)
                .build();

        ProfileSettingsDTO profileSettingsDTO = ProfileSettingsDTO.builder()
                .profileVisibility(true)
                .theme(Theme.DARK)
                .build();

        ProfileSettingsDTO mappedProfileSettings = profileSettingsMapper
                .profileSettingsToProfileSettingsDTO(profileSettings);

        assertThat(mappedProfileSettings).isNotNull();

        assertEquals(profileSettingsDTO.getProfileVisibility(),
                mappedProfileSettings.getProfileVisibility());

        assertEquals(profileSettingsDTO.getTheme(),
                mappedProfileSettings.getTheme());
    }

    @Test
    void profileSettingsToProfileSettingsResponseDTO() {
        UUID id = UUID.randomUUID();

        ProfileSettings profileSettings = ProfileSettings.builder()
                .id(id)
                .profileVisibility(true)
                .theme(Theme.DARK)
                .build();

        ProfileSettingsResponseDTO profileSettingsResponseDTO = ProfileSettingsResponseDTO.builder()
                .id(id)
                .profileVisibility(true)
                .theme(Theme.DARK)
                .build();

        ProfileSettingsResponseDTO mappedProfileSettingsResponse = profileSettingsMapper
                .profileSettingsToProfileSettingsResponseDTO(profileSettings);

        assertThat(mappedProfileSettingsResponse).isNotNull();

        assertEquals(profileSettingsResponseDTO.getId(),
                mappedProfileSettingsResponse.getId());

        assertEquals(profileSettingsResponseDTO.getProfileVisibility(),
                mappedProfileSettingsResponse.getProfileVisibility());

        assertEquals(profileSettingsResponseDTO.getTheme(),
                mappedProfileSettingsResponse.getTheme());
    }
}