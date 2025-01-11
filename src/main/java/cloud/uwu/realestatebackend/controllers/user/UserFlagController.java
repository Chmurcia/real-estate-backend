package cloud.uwu.realestatebackend.controllers.user;

import cloud.uwu.realestatebackend.dtos.user.userFlag.UserFlagDTO;
import cloud.uwu.realestatebackend.dtos.user.userFlag.UserFlagPatchDTO;
import cloud.uwu.realestatebackend.dtos.user.userFlag.UserFlagResponseDTO;
import cloud.uwu.realestatebackend.services.user.UserFlagService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.user.UserController.UserURL;

@RestController
@RequestMapping(UserFlagController.URL)
@RequiredArgsConstructor
@Validated
public class UserFlagController {
    public static final String URL = UserURL + "/flag";

    private final UserFlagService userFlagService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserFlagResponseDTO> getUserFlagByUserId(
            @PathVariable("userId") @NotNull UUID id) {
        UserFlagResponseDTO foundUserFlag = userFlagService.getUserFlagByUserId(id);

        return ResponseEntity.ok(foundUserFlag);
    }

    @GetMapping("/{userFlagId}")
    public ResponseEntity<UserFlagResponseDTO> getUserFlagById(
            @PathVariable("userFlagId") @NotNull UUID id) {
        UserFlagResponseDTO foundUserFlag = userFlagService.getUserFlagById(id);

        return ResponseEntity.ok(foundUserFlag);
    }

    @PutMapping("/{userFlagId}")
    public ResponseEntity<Object> updateUserFlag(
            @PathVariable("userFlagId") @NotNull UUID id,
            @RequestBody UserFlagDTO userFlagDTO) {
        userFlagService.updateUserFlag(id, userFlagDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userFlagId}")
    public ResponseEntity<Object> patchUserFlag(
            @PathVariable("userFlagId") @NotNull UUID id,
            @RequestBody UserFlagPatchDTO userFlagPatchDTO) {
        userFlagService.patchUserFlag(id, userFlagPatchDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userFlagId}")
    public ResponseEntity<Object> deleteUserFlag(
            @PathVariable("userFlagId") @NotNull UUID id) {
        userFlagService.deleteUserFlag(id);

        return ResponseEntity.noContent().build();
    }
}
