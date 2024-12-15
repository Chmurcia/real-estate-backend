package cloud.uwu.realestatebackend.dtos.user;

import jakarta.persistence.Entity;
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
public class UserPatchDTO {

    @Email
    private String email;

    private String password;

    private UUID userFlag;

    private UUID userNotification;

    private UUID userRole;
}
