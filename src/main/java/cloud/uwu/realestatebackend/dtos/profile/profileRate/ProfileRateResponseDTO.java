package cloud.uwu.realestatebackend.dtos.profile.profileRate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRateResponseDTO {
    private UUID id;

    @JsonProperty("profile_id")
    private UUID profileId;

    @JsonProperty("evaluator_id")
    private UUID evaluatorId;

    private String title;

    private String description;

    private Integer rate;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
