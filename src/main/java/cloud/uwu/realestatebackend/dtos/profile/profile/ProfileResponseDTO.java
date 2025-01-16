package cloud.uwu.realestatebackend.dtos.profile.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponseDTO {
    private UUID id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("nick_name")
    private String nickName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("avatar_url")
    private String avatarURL;

    private String bio;

    private String country;

    private String state;

    private String city;

    @JsonProperty("profile_settings_id")
    private UUID profileSettingsId;

    @JsonProperty("profile_statistics_id")
    private UUID profileStatisticsId;

    @JsonProperty("birth_date")
    private LocalDateTime birthDate;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}
