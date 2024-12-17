package cloud.uwu.realestatebackend.dtos.user.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "Email must be defined")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Email must be defined")
    private String password;

}
