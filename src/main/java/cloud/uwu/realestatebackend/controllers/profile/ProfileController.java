package cloud.uwu.realestatebackend.controllers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profile.ProfileDTO;
import cloud.uwu.realestatebackend.dtos.profile.profile.ProfilePatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profile.ProfileResponseDTO;
import cloud.uwu.realestatebackend.services.profile.ProfileService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(ProfileController.ProfileURL)
@RequiredArgsConstructor
public class ProfileController {
    public static final String ProfileURL = "/api/v0/profile";

    private final ProfileService profileService;

    @GetMapping()
    public ResponseEntity<Page<ProfileResponseDTO>> getProfiles(
            @RequestParam String nickName,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDirection
    ) {
        Page<ProfileResponseDTO> foundProfiles = profileService
                    .getProfiles(nickName, country, state, city , page, size, sortBy, sortDirection);

        return ResponseEntity.ok(foundProfiles);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDTO> getProfileById(
            @PathVariable("profileId") @NotNull UUID id) {
        ProfileResponseDTO foundProfile = profileService.getProfileById(id);

        return ResponseEntity.ok(foundProfile);
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> createProfile(
            @RequestBody @Validated ProfileDTO profileDTO) {
        ProfileResponseDTO createdProfile = profileService
                .createProfile(profileDTO);

        URI url = URI.create(ProfileURL + "/" + createdProfile.getId());
        return ResponseEntity.created(url).body(createdProfile);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(
            @PathVariable("profileId") @NotNull UUID id,
            @RequestBody @Validated ProfileDTO profileDTO
    ) {
        profileService.updateProfile(id, profileDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDTO> patchProfile(
            @PathVariable("profileId") @NotNull UUID id,
            @RequestBody @Validated ProfilePatchDTO profilePatchDTO
    ) {
        profileService.patchProfile(id, profilePatchDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDTO> deleteProfile(
            @PathVariable("profileId") UUID id) {
        profileService.deleteProfile(id);



        return ResponseEntity.noContent().build();
    }
}
