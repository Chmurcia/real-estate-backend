package cloud.uwu.realestatebackend.dtos.profile.profileAsk;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileAskResponseDTO {
    private UUID id;

    private UUID profileId;

    private String askTitle;

    private String askDescription;

    private String askAnswer;
}
