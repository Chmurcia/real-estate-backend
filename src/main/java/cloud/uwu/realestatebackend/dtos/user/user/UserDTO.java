package cloud.uwu.realestatebackend.dtos.user;

import cloud.uwu.realestatebackend.entities.user.UserFlag;
import cloud.uwu.realestatebackend.entities.user.UserNotification;
import cloud.uwu.realestatebackend.entities.user.UserRole;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private UUID userFlag;

    @NotNull
    private UUID userNotification;

    @NotNull
    private UUID userRole;
}
