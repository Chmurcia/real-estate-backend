package cloud.uwu.realestatebackend.controllers.user;

import cloud.uwu.realestatebackend.dtos.user.user.UserDTO;
import cloud.uwu.realestatebackend.dtos.user.user.UserPatchDTO;
import cloud.uwu.realestatebackend.dtos.user.user.UserResponseDTO;
import cloud.uwu.realestatebackend.services.user.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping(UserController.UserURL)
@RequiredArgsConstructor
public class UserController {
    public static final String UserURL = "/api/v0/user";

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @PathVariable("userId") @NotNull UUID id) {
        UserResponseDTO foundUser = userService.getUserById(id);

        return ResponseEntity.ok(foundUser);
    }

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(
            @PathVariable("userEmail") @NotNull String email) {
        UserResponseDTO foundUser = userService.getUserByEmail(email);

        return ResponseEntity.ok(foundUser);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestBody @Validated UserDTO userDTO) {
        UserResponseDTO createdUser = userService.createUser(userDTO);

        URI url = URI.create(UserURL + "/" + createdUser.getId());
        return ResponseEntity.created(url).body(createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(
            @PathVariable("userId") @NotNull UUID id,
            @RequestBody @Validated UserDTO userDTO) {
        userService.updateUser(id, userDTO);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> patchUser(
            @PathVariable("userId") @NotNull UUID id,
            @RequestBody @Validated UserPatchDTO userPatchDTO) {
        userService.patchUser(id, userPatchDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(
            @PathVariable("userId") @NotNull UUID id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

}
