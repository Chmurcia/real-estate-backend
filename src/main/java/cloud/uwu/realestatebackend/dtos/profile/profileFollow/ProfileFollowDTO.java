package cloud.uwu.realestatebackend.dtos.profile.profileFollow;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileFollowDTO {

    @NotNull(message = "follower_id must be defined")
    @JsonProperty("follower_id")
    private UUID followerId;

    @NotNull(message = "following_id must be defined")
    @JsonProperty("following_id")
    private UUID followingId;
}
