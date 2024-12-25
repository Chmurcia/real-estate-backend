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

    @NotNull(message = "is_verified must be defined")
    @JsonProperty("is_verified")
    private Boolean isVerified;

    @NotNull(message = "is_muted must be defined")
    @JsonProperty("is_muted")
    private Boolean isMuted;

    @NotNull(message = "is_banned must be defined")
    @JsonProperty("is_banned")
    private Boolean isBanned;
}
