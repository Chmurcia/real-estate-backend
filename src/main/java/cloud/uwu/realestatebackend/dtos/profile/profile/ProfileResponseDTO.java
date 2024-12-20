package cloud.uwu.realestatebackend.dtos.profile.profile;

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

    private UUID profileSettingsId;

    private UUID profileStatisticsId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String avatarURL;

    private String bio;

    private String country;

    private String state;

    private String city;

    private LocalDateTime birthDate;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
