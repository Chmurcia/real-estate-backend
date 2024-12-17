package cloud.uwu.realestatebackend.dtos.user.userRole;

import cloud.uwu.realestatebackend.entities.user.userEnums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRolePatchDTO {

    @Enumerated(EnumType.STRING)
    private Role role;

}
