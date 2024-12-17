package cloud.uwu.realestatebackend.dtos.user.user;

import jakarta.validation.constraints.Email;
import lombok.*;

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
