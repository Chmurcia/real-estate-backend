package cloud.uwu.realestatebackend.controllers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileNotification.ProfileNotificationDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileNotification.ProfileNotificationResponseDTO;
import cloud.uwu.realestatebackend.services.profile.ProfileNotificationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.profile.ProfileController.ProfileURL;

@RestController
@RequestMapping(ProfileNotificationController.URL)
@RequiredArgsConstructor
public class ProfileNotificationController {
    public static final String URL = ProfileURL + "/notification";

    private final ProfileNotificationService profileNotificationService;

    @GetMapping("profile/{profileId}")
    public ResponseEntity<List<ProfileNotificationResponseDTO>> getProfileNotificationsByProfileId(
            @PathVariable("profileId") @NotNull UUID profileId) {
        List<ProfileNotificationResponseDTO> foundProfileNotifications =
                profileNotificationService
                        .getProfileNotificationsByProfileId(profileId);

        return ResponseEntity.ok(foundProfileNotifications);
    }

    @GetMapping("/{profileNotificationId}")
    public ResponseEntity<ProfileNotificationResponseDTO> getProfileNotificationById(
            @PathVariable("profileNotificationId") @NotNull UUID profileNotificationId) {
        ProfileNotificationResponseDTO foundProfileNotification =
                profileNotificationService
                        .getProfileNotificationById(profileNotificationId);

        return ResponseEntity.ok(foundProfileNotification);
    }

    @PostMapping
    public ResponseEntity<ProfileNotificationResponseDTO> createProfileNotification(
            @RequestParam UUID profileId,
            @RequestBody @Validated ProfileNotificationDTO profileNotificationDTO) {
        ProfileNotificationResponseDTO createdProfileNotification = profileNotificationService
                .createProfileNotification(profileId, profileNotificationDTO);

        URI url = URI.create(URL + "/" + createdProfileNotification.getId());
        return ResponseEntity.created(url).body(createdProfileNotification);
    }

    @DeleteMapping("/{profileNotificationId}")
    public ResponseEntity<Object> deleteProfileNotification(
            @PathVariable("profileNotificationId") @NotNull UUID profileNotificationId) {
        profileNotificationService.deleteProfileNotification(profileNotificationId);

        return ResponseEntity.noContent().build();
    }
}
