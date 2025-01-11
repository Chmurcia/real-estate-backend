package cloud.uwu.realestatebackend.controllers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileAsk.ProfileAskDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileAsk.ProfileAskPatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileAsk.ProfileAskResponseDTO;
import cloud.uwu.realestatebackend.services.profile.ProfileAskService;
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
@RequestMapping(ProfileAskController.URL)
@RequiredArgsConstructor
public class ProfileAskController {
    public static final String URL = ProfileURL + "/ask";

    private final ProfileAskService profileAskService;

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<Page<ProfileAskResponseDTO>> getProfileAsksByProfileId(
            @PathVariable("profileId") @NotNull UUID id,
            @RequestParam(required = false) Integer page,
            @RequestParam (required = false) Integer size) {
        Page<ProfileAskResponseDTO> foundProfileAsks = profileAskService
                .getProfileAsksByProfileId(id, page, size);

        return ResponseEntity.ok(foundProfileAsks);
    }

    @GetMapping("/{profileAskId}")
    public ResponseEntity<ProfileAskResponseDTO> getProfileAskById(
            @PathVariable("profileAskId") @NotNull UUID id) {
        ProfileAskResponseDTO foundProfileAsk = profileAskService
                .getProfileAskById(id);

        return ResponseEntity.ok(foundProfileAsk);
    }

    @PostMapping
    public ResponseEntity<ProfileAskResponseDTO> createProfileAsk(
            @RequestParam UUID profileId,
            @RequestBody @Validated ProfileAskDTO profileAskDTO) {
        ProfileAskResponseDTO createdProfileAsk = profileAskService
                .createProfileAsk(profileId, profileAskDTO);

        URI url = URI.create(URL + "/" + createdProfileAsk.getId());
        return ResponseEntity.created(url).body(createdProfileAsk);
    }

    @PutMapping("/{profileAskId}")
    public ResponseEntity<Object> updateProfileAsk(
            @PathVariable("profileAskId") @NotNull UUID id,
            @RequestBody @Validated ProfileAskDTO profileAskDTO) {
        profileAskService.updateProfileAsk(id, profileAskDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{profileAskId}")
    public ResponseEntity<Object> patchProfileAsk(
            @PathVariable("profileAskId") @NotNull UUID id,
            @RequestBody @Validated ProfileAskPatchDTO profileAskPatchDTO) {
        profileAskService.patchProfileAsk(id, profileAskPatchDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{profileAskId}")
    public ResponseEntity<Object> deleteProfileAsk(
            @PathVariable("profileAskId") @NotNull UUID id) {
        profileAskService.deleteProfileAsk(id);

        return ResponseEntity.noContent().build();
    }

}
