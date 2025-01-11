package cloud.uwu.realestatebackend.dtos.user.userRole;

import cloud.uwu.realestatebackend.entities.user.userEnums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRolePatchDTO {

    @Enumerated(EnumType.STRING)
    private Role role;

}
