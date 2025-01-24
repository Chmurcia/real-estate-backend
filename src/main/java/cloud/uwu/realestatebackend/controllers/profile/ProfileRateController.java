package cloud.uwu.realestatebackend.controllers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRateDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRatePatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileRate.ProfileRateResponseDTO;
import cloud.uwu.realestatebackend.services.profile.ProfileRateService;
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
@RequestMapping(ProfileRateController.URL)
@RequiredArgsConstructor
public class ProfileRateController {
    public static final String URL = ProfileURL + "/rate";

    private final ProfileRateService profileRateService;

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<Page<ProfileRateResponseDTO>> getProfileRatesByProfileId(
            @PathVariable("profileId") @NotNull UUID id,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<ProfileRateResponseDTO> foundRates = profileRateService
                .getProfileRatesByProfileId(id, page, size);

        return ResponseEntity.ok(foundRates);
    }

    @GetMapping("/{profileRateId}")
    public ResponseEntity<ProfileRateResponseDTO> getProfileRateById(
            @PathVariable("profileRateId") @NotNull UUID id) {
        ProfileRateResponseDTO foundRate = profileRateService
                .getProfileRateById(id);

        return ResponseEntity.ok(foundRate);
    }

    @PostMapping
    public ResponseEntity<ProfileRateResponseDTO> createProfileRate(
            @RequestBody @Validated ProfileRateDTO profileRateDTO,
            @RequestParam UUID profileId) {
        ProfileRateResponseDTO createdProfileRate = profileRateService
                .createProfileRate(profileId, profileRateDTO);

        URI url = URI.create(URL + "/" + createdProfileRate.getId());
        return ResponseEntity.created(url).body(createdProfileRate);
    }

    @PutMapping("/{profileRateId}")
    public ResponseEntity<Object> updateProfileRate(
            @PathVariable("profileRateId") @NotNull UUID id,
            @RequestBody @Validated ProfileRateDTO profileRateDTO) {
        profileRateService.updateProfileRate(id, profileRateDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{profileRateId}")
    public ResponseEntity<Object> patchProfileRate(
            @PathVariable("profileRateId") @NotNull UUID id,
            @RequestBody @Validated ProfileRatePatchDTO profileRatePatchDTO) {
        profileRateService.patchProfileRate(id, profileRatePatchDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{profileRateId}")
    public ResponseEntity<Object> deleteProfileRate(
            @PathVariable("profileRateId") @NotNull UUID id) {
        profileRateService.deleteProfileRate(id);

        return ResponseEntity.noContent().build();
    }
}
