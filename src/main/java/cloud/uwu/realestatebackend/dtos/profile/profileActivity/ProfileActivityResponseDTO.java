package cloud.uwu.realestatebackend.dtos.profile.profileActivity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileActivityResponseDTO {
    private UUID id;

    @JsonProperty("activity_title")
    private String activityTitle;

    @JsonProperty("activity_description")
    private String activityDescription;

    @JsonProperty("activity_date")
    private ZonedDateTime activityDate;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
