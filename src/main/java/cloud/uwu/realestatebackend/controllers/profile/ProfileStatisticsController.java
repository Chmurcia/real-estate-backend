package cloud.uwu.realestatebackend.controllers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileStatistics.ProfileStatisticsDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileStatistics.ProfileStatisticsPatchDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileStatistics.ProfileStatisticsResponseDTO;
import cloud.uwu.realestatebackend.services.profile.ProfileStatisticsService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.profile.ProfileController.ProfileURL;

@RestController
@RequestMapping(ProfileStatisticsController.URL)
@RequiredArgsConstructor
public class ProfileStatisticsController {
    public static final String URL = ProfileURL + "/statistics";

    private final ProfileStatisticsService profileStatisticsService;

    @GetMapping("/{profileStatisticsId}")
    public ResponseEntity<ProfileStatisticsResponseDTO> getProfileStatisticsById(
            @PathVariable("profileStatisticsId") @NotNull UUID id) {
        ProfileStatisticsResponseDTO foundProfileStatistics = profileStatisticsService
                .getProfileStatisticsById(id);

        return ResponseEntity.ok(foundProfileStatistics);
    }

    @PutMapping("/{profileStatisticsId}")
    public ResponseEntity<Object> updateProfileStatistics(
            @PathVariable("profileStatisticsId") @NotNull UUID id,
            @RequestBody @Validated ProfileStatisticsDTO profileStatisticsDTO) {
        profileStatisticsService.updateProfileStatistics(id, profileStatisticsDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{profileStatisticsId}")
    public ResponseEntity<Object> patchProfileStatistics(
            @PathVariable("profileStatisticsId") @NotNull UUID id,
            @RequestBody @Validated ProfileStatisticsPatchDTO profileStatisticsPatchDTO) {
        profileStatisticsService.patchProfileStatistics(id, profileStatisticsPatchDTO);

        return ResponseEntity.noContent().build();
    }
}
