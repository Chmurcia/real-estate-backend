package cloud.uwu.realestatebackend.controllers.profile;

import cloud.uwu.realestatebackend.dtos.profile.profileFollow.ProfileFollowDTO;
import cloud.uwu.realestatebackend.dtos.profile.profileFollow.ProfileFollowResponseDTO;
import cloud.uwu.realestatebackend.services.profile.ProfileFollowService;
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
@RequestMapping(ProfileFollowController.URL)
@RequiredArgsConstructor
public class ProfileFollowController {
    public static final String URL = ProfileURL + "/follow";

    private final ProfileFollowService profileFollowService;

    @GetMapping("/follower/{followerId}")
    public ResponseEntity<Page<ProfileFollowResponseDTO>> getProfileFollowsByFollowerId(
            @PathVariable("followerId") @NotNull UUID id,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<ProfileFollowResponseDTO> foundProfileFollows =
                profileFollowService.getProfileFollowsByFollowerId(id, page, size);

        return ResponseEntity.ok(foundProfileFollows);
    }

    @GetMapping("/following/{followingId}")
    public ResponseEntity<Page<ProfileFollowResponseDTO>> getProfileFollowsByFollowingId(
            @PathVariable("followingId") @NotNull UUID id,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<ProfileFollowResponseDTO> foundProfileFollows =
                profileFollowService.getProfileFollowsByFollowingId(id, page, size);

        return ResponseEntity.ok(foundProfileFollows);
    }

    @PostMapping
    public ResponseEntity<ProfileFollowResponseDTO> createProfileFollow(
            @RequestBody @Validated ProfileFollowDTO profileFollowDTO) {
        ProfileFollowResponseDTO createdProfileFollow = profileFollowService
                .createProfileFollow(profileFollowDTO);

        URI url = URI.create(URL + "/" + createdProfileFollow.getId());
        return ResponseEntity.created(url).body(createdProfileFollow);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteProfileFollow(
            @RequestParam UUID followerId,
            @RequestParam UUID followingId) {
        profileFollowService.deleteProfileFollow(followerId, followingId);

        return ResponseEntity.noContent().build();
    }
}
