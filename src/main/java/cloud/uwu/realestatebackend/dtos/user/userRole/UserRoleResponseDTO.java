package cloud.uwu.realestatebackend.dtos.user.userRole;


import cloud.uwu.realestatebackend.entities.user.userEnums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleResponseDTO {

    private UUID id;

    @Enumerated(EnumType.STRING)
    private Role role;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
