package cloud.uwu.realestatebackend.dtos.profile.profileFollow;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileFollowDTO {
    @Id
    @UuidGenerator
    private UUID id;

    @Version
    private int version;

    @NotNull(message = "follower_id must be defined")
    @JsonProperty("follower_id")
    private UUID followerId;

    @NotNull(message = "following_id must be defined")
    @JsonProperty("following_id")
    private UUID followingId;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
