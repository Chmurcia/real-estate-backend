package cloud.uwu.realestatebackend.controllers.user;

import cloud.uwu.realestatebackend.dtos.user.auth.IsSuccessResponseDTO;
import cloud.uwu.realestatebackend.dtos.user.auth.TokenResponseDTO;
import cloud.uwu.realestatebackend.dtos.user.user.UserDTO;
import cloud.uwu.realestatebackend.services.user.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<IsSuccessResponseDTO> registerUser(
            @RequestBody @Validated UserDTO userDTO) {
        IsSuccessResponseDTO success = IsSuccessResponseDTO.builder()
                .success(authService.registerUser(userDTO))
                .build();

        return ResponseEntity.ok(success);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(
            @RequestBody @Validated UserDTO userDTO) {

        TokenResponseDTO token = TokenResponseDTO.builder()
                .token(authService.loginUser(userDTO))
                .build();

        return ResponseEntity.ok(token);
    }

}
