package cloud.uwu.realestatebackend.dtos.user.userRole;

import cloud.uwu.realestatebackend.entities.user.userEnums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleDTO {

    @NotBlank(message = "Role must be defined")
    @Enumerated(EnumType.STRING)
    private Role role;
}
