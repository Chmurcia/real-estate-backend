package cloud.uwu.realestatebackend.dtos.user.user;

import jakarta.validation.constraints.Email;
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
}
