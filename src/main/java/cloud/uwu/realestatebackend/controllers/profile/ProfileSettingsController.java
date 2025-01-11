package cloud.uwu.realestatebackend.controllers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileSettings.ProfileSettingsDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileSettings.ProfileSettingsPatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileSettings.ProfileSettingsResponseDTO;
import cloud.uwu.realestatebackend.services.profile.ProfileSettingsService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.profile.ProfileController.ProfileURL;

@RestController
@RequestMapping(ProfileSettingsController.URL)
@RequiredArgsConstructor
public class ProfileSettingsController {
    public static final String URL = ProfileURL + "/settings";

    private final ProfileSettingsService profileSettingsService;

    @GetMapping("/{profileSettingsId}")
    public ResponseEntity<ProfileSettingsResponseDTO> getProfileSettingsById(
            @PathVariable("profileSettingsId") @NotNull UUID id) {
        ProfileSettingsResponseDTO foundProfileSettings = profileSettingsService
                .getProfileSettingsById(id);

        return ResponseEntity.ok(foundProfileSettings);
    }

    @PutMapping("/{profileSettingsId}")
    public ResponseEntity<Object> updateProfileSettings(
            @PathVariable("profileSettingsId") @NotNull UUID id,
            @RequestBody @Validated ProfileSettingsDTO profileSettingsDTO) {
        profileSettingsService.updateProfileSettings(id, profileSettingsDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{profileSettingsId}")
    public ResponseEntity<Object> patchProfileSettings(
            @PathVariable("profileSettingsId") @NotNull UUID id,
            @RequestBody @Validated ProfileSettingsPatchDTO profileSettingsPatchDTO) {
        profileSettingsService.patchProfileSettings(id, profileSettingsPatchDTO);

        return ResponseEntity.noContent().build();
    }

}
