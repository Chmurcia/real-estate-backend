package cloud.uwu.realestatebackend.controllers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityPatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileActivity.ProfileActivityResponseDTO;
import cloud.uwu.realestatebackend.services.profile.ProfileActivityService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.profile.ProfileController.ProfileURL;

@RestController
@RequestMapping(ProfileActivityController.URL)
@RequiredArgsConstructor
@Validated
public class ProfileActivityController {
    public static final String URL = ProfileURL + "/activity";

    private final ProfileActivityService profileActivityService;

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<Page<ProfileActivityResponseDTO>> getProfileActivitiesByProfileId(
            @PathVariable("profileId") UUID id,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<ProfileActivityResponseDTO> foundProfileActivities = profileActivityService
                .getProfileActivitiesByProfileId(id, page, size);

        return ResponseEntity.ok(foundProfileActivities);
    }

    @GetMapping("/{profileActivityId}")
    public ResponseEntity<ProfileActivityResponseDTO> getProfileActivityById(
            @PathVariable("profileActivityId") UUID id) {
        ProfileActivityResponseDTO foundProfileActivity = profileActivityService
                .getProfileActivityById(id);

        return ResponseEntity.ok(foundProfileActivity);
    }

    @PostMapping
    public ResponseEntity<ProfileActivityResponseDTO> createProfileActivity(
            @RequestParam UUID profileId,
            @RequestBody ProfileActivityDTO profileActivityDTO) {
        ProfileActivityResponseDTO createdProfileActivity = profileActivityService
                .createProfileActivity(profileId, profileActivityDTO);

        URI url = URI.create(URL + "/" + createdProfileActivity.getId());
        return ResponseEntity.created(url).body(createdProfileActivity);
    }

    @PutMapping("/{profileActivityId}")
    public ResponseEntity<Object> updateProfileActivity(
            @PathVariable("profileActivityId") UUID id,
            @RequestBody ProfileActivityDTO profileActivityDTO) {
        profileActivityService.updateProfileActivity(id, profileActivityDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{profileActivityId}")
    public ResponseEntity<Object> patchProfileActivity(
            @PathVariable("profileActivityId") UUID id,
            @RequestBody ProfileActivityPatchDTO profileActivityPatchDTO) {
        profileActivityService.patchProfileActivity(id, profileActivityPatchDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{profileActivityId}")
    public ResponseEntity<Object> patchProfileActivity(
            @PathVariable("profileActivityId") UUID id) {
        profileActivityService.deleteProfileActivity(id);

        return ResponseEntity.noContent().build();
    }
}

