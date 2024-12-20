package cloud.uwu.realestatebackend.dtos.user.userFlag;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("is_verified")
    private Boolean isVerified;

    @JsonProperty("is_muted")
    private Boolean isMuted;

    @JsonProperty("is_banned")
    private Boolean isBanned;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;

}
