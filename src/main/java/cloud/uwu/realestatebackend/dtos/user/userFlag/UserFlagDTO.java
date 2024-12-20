package cloud.uwu.realestatebackend.dtos.user.userFlag;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFlagDTO {

    @NotNull(message = "isVerified must be defined")
    @JsonProperty("is_verified")
    private Boolean isVerified;

    @NotNull(message = "isMuted must be defined")
    @JsonProperty("is_muted")
    private Boolean isMuted;

    @NotNull(message = "isBanned must be defined")
    @JsonProperty("is_banned")
    private Boolean isBanned;
}
