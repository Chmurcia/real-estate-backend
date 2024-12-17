package cloud.uwu.realestatebackend.dtos.user.userFlag;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFlagResponseDTO {

    private UUID id;

    private Boolean isVerified;

    private Boolean isBanned;

    private Boolean isMuted;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}
