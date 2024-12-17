package cloud.uwu.realestatebackend.dtos.user.user;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private UUID id;

    private String email;

    private UUID userFlagId;

    private UUID userRoleId;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
