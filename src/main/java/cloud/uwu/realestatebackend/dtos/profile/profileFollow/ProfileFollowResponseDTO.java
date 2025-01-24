package cloud.uwu.realestatebackend.dtos.profile.profileFollow;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
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
public class ProfileFollowResponseDTO {
    @Id
    @UuidGenerator
    private UUID id;

    @JsonProperty("follower_id")
    private UUID followerId;

    @JsonProperty("following_id")
    private UUID followingId;

    @CreationTimestamp
    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
