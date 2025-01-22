package cloud.uwu.realestatebackend.controllers.user;

import cloud.uwu.realestatebackend.dtos.user.user.UserDTO;
import cloud.uwu.realestatebackend.services.user.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(
            @RequestBody @Validated UserDTO userDTO) {
        Boolean success = authService.registerUser(userDTO);

        return ResponseEntity.ok(success);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody @Validated UserDTO userDTO) {
        String success = authService.loginUser(userDTO);

        return ResponseEntity.ok(success);
    }

}
