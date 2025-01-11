package cloud.uwu.realestatebackend.controllers.user;

import cloud.uwu.realestatebackend.dtos.user.userRole.UserRoleDTO;
import cloud.uwu.realestatebackend.dtos.user.userRole.UserRolePatchDTO;
import cloud.uwu.realestatebackend.dtos.user.userRole.UserRoleResponseDTO;
import cloud.uwu.realestatebackend.services.user.UserRoleService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

import static cloud.uwu.realestatebackend.controllers.user.UserController.UserURL;

@RestController
@RequestMapping(UserRoleController.URL)
@RequiredArgsConstructor
@Validated
public class UserRoleController {

    public static final String URL = UserURL + "/role";

    private final UserRoleService userRoleService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserRoleResponseDTO> getUserRoleByUserId(
            @PathVariable("userId") @NotNull UUID id) {
        UserRoleResponseDTO foundUserRole = userRoleService.getUserRoleByUserId(id);

        return ResponseEntity.ok(foundUserRole);
    }

    @GetMapping("/{userRoleId}")
    public ResponseEntity<UserRoleResponseDTO> getUserRoleById(
            @PathVariable("userRoleId") @NotNull UUID id) {
        UserRoleResponseDTO foundUserRole = userRoleService.getUserRoleById(id);

        return ResponseEntity.ok(foundUserRole);
    }

    @PutMapping("/{userRoleId}")
    public ResponseEntity<Object> updateUserRole(
            @PathVariable("userRoleId") @NotNull UUID id,
            @RequestBody UserRoleDTO userRoleDTO
    ) {
        userRoleService.updateUserRole(id, userRoleDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userRoleId}")
    public ResponseEntity<Object> patchUserRole(
            @PathVariable("userRoleId") @NotNull UUID id,
            @RequestBody UserRolePatchDTO userRolePatchDTO
    ) {
        userRoleService.patchUserRole(id, userRolePatchDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userRoleId}")
    public ResponseEntity<Object> deleteUserRole(
            @PathVariable("userRoleId") @NotNull UUID id) {
        userRoleService.deleteUserRole(id);

        return ResponseEntity.noContent().build();
    }

}
