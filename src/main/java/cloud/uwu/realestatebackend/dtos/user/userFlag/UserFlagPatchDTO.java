package cloud.uwu.realestatebackend.dtos.user.userFlag;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFlagPatchDTO {

    private Boolean isVerified;

    private Boolean isBanned;

    private Boolean isMuted;

}
