package cloud.uwu.realestatebackend.dtos.user.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    //    @Pattern(regexp = "^[\\w-]+@([\\w-]+\\.)+[\\w-]{2,}$", message = "Invalid email format")
    @NotBlank(message = "email must be defined")
    private String email;

    @NotBlank(message = "password must be defined")
    private String password;
}
