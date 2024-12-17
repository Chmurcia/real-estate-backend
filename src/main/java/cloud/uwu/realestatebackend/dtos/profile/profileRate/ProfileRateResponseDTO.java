package cloud.uwu.realestatebackend.dtos.profile.profileRate;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRateResponseDTO {
    private UUID id;

    private UUID profileId;

    private UUID evaluatorId;

    private String title;

    private String description;

    private Integer rate;
}
