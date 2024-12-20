package cloud.uwu.realestatebackend.dtos.user.userFlag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFlagPatchDTO {

    @JsonProperty("is_verified")
    private Boolean isVerified;

    @JsonProperty("is_muted")
    private Boolean isMuted;

    @JsonProperty("is_banned")
    private Boolean isBanned;
}
