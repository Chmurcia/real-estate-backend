package cloud.uwu.realestatebackend.dtos.profile.profileActivity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileActivityDTO {
    @NotNull(message = "Id of profile must be defined")
    @JsonProperty("profile_id")
    private UUID profileId;

    @NotBlank(message = "Title of an activity must be defined")
    @Size(min = 2, max = 50, message = "Title of an activity must contain between 2 and 50 character")
    @JsonProperty("activity_title")
    private String activityTitle;

    @NotBlank(message = "Description of an activity must be defined")
    @Size(min = 1, max = 255, message = "Description of an activity must contain between 1 and 255 character")
    @JsonProperty("activity_description")
    private String activityDescription;

    @NotNull(message = "Date of an activity must be defined")
    @Past(message = "Date of an activity must be in the past")
    @JsonProperty("activity_date")
    private ZonedDateTime activityDate;
}
